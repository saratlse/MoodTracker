package sara.openclassrooms.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sara.openclassrooms.moodtracker.Fragment.MoodFragment1;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment2;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment3;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment4;
import sara.openclassrooms.moodtracker.Fragment.MoodFragment5;
import sara.openclassrooms.moodtracker.Model.DifferentMoods;
import sara.openclassrooms.moodtracker.Model.VerticalPagerAdapter;
import sara.openclassrooms.moodtracker.Model.VerticalViewPager;
import sara.openclassrooms.moodtracker.R;


public class MainActivity extends AppCompatActivity {


    //ArrayList contains all the moods
    private ArrayList<DifferentMoods> mDifferentMoodsArrayList;

    private static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String CURRENT_DAY = "CURRENT_DAY";
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    private static final String PREVIOUS_RECORDED_DAY = "PREVIOUS_RECORDED_DAY";
    private static final String PREF_BACKGROUND_COLOR = "PREF_BACKGROUND_COLOR";
    public static final String PREFERENCES_KEY_MOOD = "mood";

    final Context context = this;
    public EditText edResult;
    private VerticalViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private FloatingActionButton mBtnComment;
    private int moodBackgroundColor;


    /*save the comment variable*/
    private Calendar mCalendar;
    private FloatingActionButton mBtnHistoric;

    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor editor;
    private String mComment;


    private int mPosition;
    private int mCurrentDay;
    private int mDayChecker;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //permet  d'enlever la top bar ou bar de navigation
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        List<Fragment> list = new ArrayList<>();
        list.add(new MoodFragment1());
        list.add(new MoodFragment2());
        list.add(new MoodFragment3());
        list.add(new MoodFragment4());
        list.add(new MoodFragment5());


        mSharedPref = getPreferences(MODE_PRIVATE);
        mComment = mSharedPref.getString(PREF_KEY_COMMENT, "");


        mBtnHistoric = (FloatingActionButton) findViewById(R.id.historyButton);
        mBtnComment = (FloatingActionButton) findViewById(R.id.commentButton);
        edResult = (EditText) findViewById(R.id.editTextComment);


        /////////////////////////////////
        //   BUTTON COMMENT  ////////////
        /////////////////////////////////
        mBtnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //get dialog_box.xml to alertdialog builder
                LayoutInflater li = LayoutInflater.from(context);
                View dialogBoxView = li.inflate(R.layout.dialog_box, null);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //set dialog_box.xml to alerdialog builder
                alertDialogBuilder.setView(dialogBoxView);
                edResult = (EditText) findViewById(R.id.editTextComment);
                final EditText textInput = (EditText) dialogBoxView.findViewById(R.id.editTextComment);


                String text = mSharedPref.getString(PREF_KEY_COMMENT, null);
                textInput.setText(text);


                //set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("VALIDER",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mComment = textInput.getText().toString();
                                        mSharedPref = getSharedPreferences("COMMENT", MODE_PRIVATE);
                                        mSharedPref.edit().putString(PREF_KEY_COMMENT, mComment).apply();




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

        //////////////////////////////
        /// BUTTON HISTORY //////////
        /////////////////////////////
        mBtnHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);

                /*Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivity);*/

            }

        });

        //Enregistrer un commentaire
        mSharedPref = getSharedPreferences("COMMENT" + dateToString(new Date()), MODE_PRIVATE);
        mSharedPref.edit().putString(PREF_KEY_COMMENT, mComment).apply();



        //////////////////////////////
        //////VERTICAL VIEW PAGER//////////////
        /////////////////////////////
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
        //final Intent sendIntent = new Intent(Intent.ACTION_SEND);
        viewPager.setCurrentItem(mSharedPref.getInt(PREFERENCES_KEY_MOOD, 0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }


            @Override
            public void onPageSelected(int i) {

                mSharedPref = getSharedPreferences("SHARED_PREFS",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPref.edit();
                editor.putString("PREF_KEY_COMMENT",mComment);
                editor.apply();
                editor.commit();

                switch (i) {



                    case 0:


                        Toast.makeText(MainActivity.this, DifferentMoods.Sad.name(), Toast.LENGTH_SHORT).show();
                        break;

                    case 1:

                        Toast.makeText(MainActivity.this, DifferentMoods.Disappointed.name(), Toast.LENGTH_SHORT).show();
                        break;

                    case 2:

                        Toast.makeText(MainActivity.this, DifferentMoods.Normal.name(), Toast.LENGTH_SHORT).show();
                        break;

                    case 3:

                        
                        Toast.makeText(MainActivity.this, DifferentMoods.Happy.name(), Toast.LENGTH_SHORT).show();
                        break;

                    case 4:

                        Toast.makeText(MainActivity.this, DifferentMoods.SuperHappy.name(), Toast.LENGTH_SHORT).show();
                        break;

                    default:

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        /*public static void saveColor (Context context, int backgroundColor) {
            mSharedPref(context);
            editor.putInt(PREF_BACKGROUND_COLOR, backgroundColor);
            editor.apply();
        }*/
    }

    private void mSharedPref(Context context) {
        mSharedPref = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = mSharedPref.edit();
    }


    //////////////////////////////////////
    ///GET TIME AND DATE
    /////////////////////////////////////
    public String dateToString(Date d) {
        //@SuppressLint("SimpleDateFormat")
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        return f.format(d);
    }

    //CONVERT STRING IN DATE
    public Date stringToDate(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date();
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    //recovers seven last comment in arrayList
    public ArrayList<String> sevenLastComment() {
        String comment;
        ArrayList<String> list = new ArrayList<>();

        Date d = new Date();
        String s;
        while (list.size() < 8) {
            s = dateToString(d);
            comment = getPreferences(MODE_PRIVATE).getString("Comment" + s, null);
            list.add(comment);
            d = stringToDate(s);
            d = removeOneDay(d);

        }
        return list;
    }

    //removed one day at the date and set at 00:00
    public static Date removeOneDay(Date d) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(d);
        dateStart.add(Calendar.DAY_OF_YEAR, -1);

        return dateStart.getTime();
    }

    //public int getMoodIcon() {
       // return moodIcon;
    //}

    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }


    public int getPosition() {
        return mPosition;
    }

    public String getComment() {
        return mComment;
    }
}