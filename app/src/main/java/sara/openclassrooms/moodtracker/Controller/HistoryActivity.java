package sara.openclassrooms.moodtracker.Controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import sara.openclassrooms.moodtracker.Fragment.MoodFragment1;
import sara.openclassrooms.moodtracker.Model.DifferentsMoods;
import sara.openclassrooms.moodtracker.Model.Mood;
import sara.openclassrooms.moodtracker.R;

import static sara.openclassrooms.moodtracker.Model.DifferentsMoods.DISAPPOINTED;
import static sara.openclassrooms.moodtracker.Model.DifferentsMoods.HAPPY;
import static sara.openclassrooms.moodtracker.Model.DifferentsMoods.NORMAL;
import static sara.openclassrooms.moodtracker.Model.DifferentsMoods.SAD;
import static sara.openclassrooms.moodtracker.Model.DifferentsMoods.SUPER_HAPPY;

public class HistoryActivity extends AppCompatActivity {




    private int width;
    private int height;


    private SharedPreferences mSharedPref;
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    private Calendar mCalendar;
    private Date mDate;
    private String mComment;
    private SimpleDateFormat sdf;


    private Mood mYesterday;
    private Mood mTwoDaysAgo;
    private Mood mThreeDaysAgo;
    private Mood mFourDaysAgo;
    private Mood mFiveDaysAgo;
    private Mood mSixDaysAgo;
    private Mood mSevenDaysAgo;




    private int mActivityYesterdayColor, mActivity2DaysAgoColor, mActivity3DaysAgoColor, mActivity4DaysAgoColor, mActivity5DaysAgoColor, mActivity6DaysAgoColor, mActivity7DaysAgoColor;
    private String mActivityYesterdayComment, mActivity2DaysAgoComment, mActivity3DaysAgoComment, mActivity4DaysAgoComment, mActivity5DaysAgoComment, mActivity6DaysAgoComment, mActivity7DaysAgoComment;
    ListView mHistoryListView;
    TextView mHistoryTextView;
    private Object DifferentsMoods;

    ArrayList<RelativeLayout> listLayout = new ArrayList<>();
    ArrayList<Button> listButton = new ArrayList<>();
    ArrayList<TextView> listTextView = new ArrayList<>();
    ArrayList<Mood> historyList = new ArrayList<>();
    ArrayList<ImageView> imageList = new ArrayList<>();


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



        mSharedPref = getSharedPreferences("PREF_KEY_COMMENT", MODE_PRIVATE);
        mComment = mSharedPref.getString("REF_KEY_COMMENT", "");


        ArrayList<RelativeLayout> listLayout = new ArrayList<>();
        ArrayList<Button> listButton = new ArrayList<>();
        ArrayList<TextView> listTextView = new ArrayList<>();
        ArrayList<Mood> historyList = new ArrayList<>();
        ArrayList<ImageView> imageList = new ArrayList<>();


        RelativeLayout mJour1 = this.findViewById(R.id.activity_history_1_jour_layout);
        RelativeLayout mJour2 = this.findViewById(R.id.activity_history_2_jours_layout);
        RelativeLayout mJour3 = this.findViewById(R.id.activity_history_3_jours_layout);
        RelativeLayout mJour4 = this.findViewById(R.id.activity_history_4_jours_layout);
        RelativeLayout mJour5 = this.findViewById(R.id.activity_history_5_jours_layout);
        RelativeLayout mJour6 = this.findViewById(R.id.activity_history_6_jours_layout);
        RelativeLayout mJour7 = this.findViewById(R.id.activity_history_7_jours_layout);


        Button mButton1 = findViewById(R.id.activity_history_1_jour_btn);
        Button mButton2 = findViewById(R.id.activity_history_2_jours_btn);
        Button mButton3 = findViewById(R.id.activity_history_3_jours_btn);
        Button mButton4 = findViewById(R.id.activity_history_4_jours_btn);
        Button mButton5 = findViewById(R.id.activity_history_5_jours_btn);
        Button mButton6 = findViewById(R.id.activity_history_6_jours_btn);
        Button mButton7 = findViewById(R.id.activity_history_7_jours_btn);


        ///////////////////TEXTVIEW//////////////////////////////////////////
        TextView mTextView1 = findViewById(R.id.activity_history_text_jour_1);
        TextView mTextView2 = findViewById(R.id.activity_history_text_jour_2);
        TextView mTextView3 = findViewById(R.id.activity_history_text_jour_3);
        TextView mTextView4 = findViewById(R.id.activity_history_text_jour_4);
        TextView mTextView5 = findViewById(R.id.activity_history_text_jour_5);
        TextView mTextView6 = findViewById(R.id.activity_history_text_jour_6);
        TextView mTextView7 = findViewById(R.id.activity_history_text_jour_7);


        mTextView1.setText(getString(R.string.jour_1));
        mTextView2.setText(getString(R.string.jour_2));
        mTextView3.setText(getString(R.string.jour_3));
        mTextView4.setText(getString(R.string.jour_4));
        mTextView5.setText(getString(R.string.jour_5));
        mTextView6.setText(getString(R.string.jour_6));
        mTextView7.setText(getString(R.string.jour_7));



        ///DAY 7
        listLayout.add(mJour7);
        listButton.add(mButton7);
        listTextView.add(mTextView7);
        mCalendar.add(Calendar.DATE,-7);
        String mSevenDaysP = sdf.format(mCalendar.getTime());
        mSevenDaysAgo = Storage.load(this,mSevenDaysP);


        //DAY 6
        listLayout.add(mJour6);
        listButton.add(mButton6);
        listTextView.add(mTextView6);
        mCalendar.add(Calendar.DATE,-6);
        String mSixDaysP = sdf.format(mCalendar.getTime());
        mSixDaysAgo = Storage.load(this,mSixDaysP);

        //DAY 5
        listLayout.add(mJour5);
        listButton.add(mButton5);
        listTextView.add(mTextView5);

        //DAY 4
        listLayout.add(mJour4);
        listButton.add(mButton4);
        listTextView.add(mTextView4);


        listLayout.add(mJour3);
        listButton.add(mButton3);
        listTextView.add(mTextView3);

        listLayout.add(mJour2);
        listButton.add(mButton2);
        listTextView.add(mTextView2);


        listLayout.add(mJour1);
        listButton.add(mButton1);
        listTextView.add(mTextView1);


        sdf = new SimpleDateFormat("ddMMyyyy");
        mCalendar = Calendar.getInstance();


        mActivityYesterdayColor = getIntent().getIntExtra("YESTERDAY_COLOR", 0);
        mActivityYesterdayComment = getIntent().getStringExtra("YESTERDAY_COMMENT");

        mActivity2DaysAgoColor = getIntent().getIntExtra("TWO_DAYS_AGO_COLOR", 0);
        mActivity2DaysAgoComment = getIntent().getStringExtra("TWO_DAYS_AGO_COMMENT");

        mActivity3DaysAgoColor = getIntent().getIntExtra("THREE_DAYS_AGO_COLOR", 0);
        mActivity3DaysAgoComment = getIntent().getStringExtra("THREE_DAYS_AGO_COMMENT");

        mActivity4DaysAgoColor = getIntent().getIntExtra("FOUR_DAYS_AGO_COLOR", 0);
        mActivity4DaysAgoComment = getIntent().getStringExtra("FOUR_DAYS_AGO_COMMENT");

        mActivity5DaysAgoColor = getIntent().getIntExtra("FIVE_DAYS_AGO_COLOR", 0);
        mActivity5DaysAgoComment = getIntent().getStringExtra("FIVE_DAYS_AGO_COMMENT");

        mActivity6DaysAgoColor = getIntent().getIntExtra("SIX_DAYS_AGO_COLOR", 0);
        mActivity6DaysAgoComment = getIntent().getStringExtra("SIX_DAYS_AGO_COMMENT");

        mActivity7DaysAgoColor = getIntent().getIntExtra("SEVEN_DAYS_AGO_COLOR", 0);
        mActivity7DaysAgoComment = getIntent().getStringExtra("SEVEN_DAYS_AGO_COMMENT");

        Mood mood1 = new Mood();
        Mood mood2 = new Mood();
        Mood mood3 = new Mood();
        Mood mood4 = new Mood();
        Mood mood5 = new Mood();





       mood1.setDifferentsMoods(SAD);
       mood2.setDifferentsMoods(DISAPPOINTED);
       mood3.setDifferentsMoods(NORMAL);
       mood4.setDifferentsMoods(HAPPY);
       mood5.setDifferentsMoods(SUPER_HAPPY);



       displayMood(mood1.getDifferentsMoods(),mJour1);
       displayMood(mood2.getDifferentsMoods(),mJour2);
       displayMood(mood3.getDifferentsMoods(),mJour3);
       displayMood(mood4.getDifferentsMoods(), mJour4);
       displayMood(mood5.getDifferentsMoods(),mJour5);



        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //      Toast.makeText(, Toast.LENGTH_LONG).show();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }





    //layoutConstructor(listLayout, listButton, listTextView);




    /*private void layoutConstructor(ArrayList<RelativeLayout> listLayout, ArrayList<Button> listButton, ArrayList<TextView> listTextView) {
        RelativeLayout relativeLayout = findViewById(R.id.historyButton);

        for (int i = 0; 1 <= listLayout.size(); i++) {
            SharedPreferences mSharedPref = getApplicationContext().getSharedPreferences("PREF_KEY_COMMENT", 0);

            String commentaire = mSharedPref.getString("COMMENT_" + i, null);
            mSharedPref.getAll();

            if (commentaire == null || commentaire.isEmpty()) {

                listLayout.get(i ).setBackgroundColor(Color.RED);

                listButton.get(i ).setVisibility(View.INVISIBLE);

            } else {

                listTextView.get(i - 1).setText(mSharedPref.getString("COMMENT_" + i, null));
                listLayout.get(i - 1).setBackgroundColor(Color.TRANSPARENT);
            }
        }

    }*/


    /*private void disPlayComment{
        for (int i = 0; i<historyList.size();i++){
            ImageView imageView = imageList.get(i);

            String comment = historyList.get(i).getComment();
            if(!commen)
        }
    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void displayMood (DifferentsMoods differentsMoods, RelativeLayout layout){
        Display display = getWindowManager().getDefaultDisplay();

        int width =0;


        switch (differentsMoods){
            case SAD :

                layout.setBackgroundColor(getColor(R.color.sad_red));

                break;

            case DISAPPOINTED:
                layout.setBackgroundColor(getColor(R.color.disappointed_grey));
                break;

            case NORMAL:
                layout.setBackgroundColor(getColor(R.color.normal_blue));
                break;

            case HAPPY:
                layout.setBackgroundColor(getColor(R.color.happy_green));
                break;

            case SUPER_HAPPY:
                layout.setBackgroundColor(getColor(R.color.super_happy_yellow));
                break;
        }

    }

}



