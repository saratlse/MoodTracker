package sara.openclassrooms.moodtracker.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sara.openclassrooms.moodtracker.Fragment.MoodFragment1;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment2;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment3;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment4;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment5;
import sara.openclassrooms.moodtracker.Model.VerticalPagerAdapter;
import sara.openclassrooms.moodtracker.Model.VerticalViewPager;
import sara.openclassrooms.moodtracker.R;


public class MainActivity extends AppCompatActivity {
    public static int moodValue = 0;
    final Context context = this;
    public EditText edResult;
    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    MediaPlayer mPlayer;
    private VerticalViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ImageButton mBtnComment;
    private ImageButton mBtnHistoric;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        List<Fragment> list = new ArrayList<>();
        list.add(new MoodFragment1());
        list.add(new MoodFragment2());
        list.add(new MoodFragment3());
        list.add(new MoodFragment4());
        list.add(new MoodFragment5());

        mBtnHistoric = findViewById(R.id.historyButton);
        mBtnComment = findViewById(R.id.commentButton);
        edResult = findViewById(R.id.editTextComment);

        databaseManager = new DatabaseManager (getBaseContext ());

        mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get dialog_box.xml to alertdialog builder
                LayoutInflater li = LayoutInflater.from(context);
                View dialogBoxView = li.inflate(R.layout.dialog_box, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set dialog_box.xml to alerdialog builder
                alertDialogBuilder.setView(dialogBoxView);
                edResult = findViewById(R.id.editTextComment);
                final EditText textInput = dialogBoxView.findViewById(R.id.editTextComment);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("VALIDER",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        String userInputValue = textInput.getText().toString().trim();
                                        String mCurrentDate = simpleDateFormat.format (new Date ());
                                        databaseManager.insertMood(moodValue, userInputValue,mCurrentDate);
                                        Log.i("DATABASE", "insertCommand invoked");
                                    }
                                })

                        .setNegativeButton("ANNULER",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });

        mBtnHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);
            }
        });

        databaseManager = new DatabaseManager (this);
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                moodValue = position;

                switch (position) {
                    case 0:
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.accomplished);
                        mPlayer.start();
                        break;

                    case 1:
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.eventually);
                        mPlayer.start();
                        break;

                    case 2:
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.falling);
                        mPlayer.start();
                        break;

                    case 3:
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.solemn);
                        mPlayer.start();
                        break;

                    case 4:
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.relentless);
                        mPlayer.start();
                        break;

                    default:
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }
}
