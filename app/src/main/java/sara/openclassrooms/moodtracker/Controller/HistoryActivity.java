package sara.openclassrooms.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Utils.Storage;
import sara.openclassrooms.moodtracker.Model.Mood;
import sara.openclassrooms.moodtracker.Model.MoodData;
import sara.openclassrooms.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {


    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    final Context context = this;
    ListView mHistoryListView;
    TextView mHistoryTextView;


    ArrayList<RelativeLayout> listLayout = new ArrayList<>();
    ArrayList<Button> listButton = new ArrayList<>();
    ArrayList<TextView> listTextView = new ArrayList<>();
    ArrayList<MoodData> historyList = new ArrayList<>();
    ArrayList<ImageView> imageList = new ArrayList<>();
    String mCurrentDate = "dd-MM-yyyy";
    SimpleDateFormat Sdf = new SimpleDateFormat();


    private int width;
    private int height;
    private SharedPreferences mSharedPref;
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
    private Object DifferentsMoods;
    private DatabaseManager databaseManager;
    private String userInputValue;

    //removed one day at the date and set at 00:00
    public static Date removeOneDay(Date d) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(d);
        dateStart.add(Calendar.DAY_OF_YEAR, -1);
        return dateStart.getTime();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        databaseManager = new DatabaseManager(this);
        historyList = databaseManager.getLast7Mood();


        mSharedPref = getSharedPreferences("PREF_KEY_COMMENT", MODE_PRIVATE);
        mComment = mSharedPref.getString("REF_KEY_COMMENT", "");


        ArrayList<RelativeLayout> listLayout = new ArrayList<>();
        ArrayList<Button> listButton = new ArrayList<>();
        ArrayList<TextView> listTextView = new ArrayList<>();
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


        sdf = new SimpleDateFormat("ddMMyyyy");//transforme la date en string
        mCalendar = Calendar.getInstance();


        ///DAY 7
        listLayout.add(mJour7);
        listButton.add(mButton7);
        listTextView.add(mTextView7);
        mCalendar.add(Calendar.DATE, -1); //modifie un element de la date
        String mSevenDaysP = sdf.format(mCalendar.getTime());
        Log.i("my activity ", mSevenDaysP);
        mSevenDaysAgo = Storage.load(this, mSevenDaysP);


        //DAY 6
        listLayout.add(mJour6);
        listButton.add(mButton6);
        listTextView.add(mTextView6);
        mCalendar.add(Calendar.DATE, -1);
        String mSixDaysP = sdf.format(mCalendar.getTime());
        mSixDaysAgo = Storage.load(this, mSixDaysP);

        //DAY 5
        listLayout.add(mJour5);
        listButton.add(mButton5);
        listTextView.add(mTextView5);
        mCalendar.add(Calendar.DATE, -1);
        String mFiveDaysP = sdf.format(mCalendar.getTime());
        mFiveDaysAgo = Storage.load(this, mFiveDaysP);

        //DAY 4
        listLayout.add(mJour4);
        listButton.add(mButton4);
        listTextView.add(mTextView4);
        mCalendar.add(Calendar.DATE, -1);
        String mFourDaysP = sdf.format(mCalendar.getTime());
        mFourDaysAgo = Storage.load(this, mFourDaysP);

        //DAY 3
        listLayout.add(mJour3);
        listButton.add(mButton3);
        listTextView.add(mTextView3);
        mCalendar.add(Calendar.DATE, -1);
        String mThreeDaysP = sdf.format(mCalendar.getTime());
        mThreeDaysAgo = Storage.load(this, mThreeDaysP);

        //DAY 2
        listLayout.add(mJour2);
        listButton.add(mButton2);
        listTextView.add(mTextView2);
        mCalendar.add(Calendar.DATE, -1);
        final String mTwoDaysP = sdf.format(mCalendar.getTime());
        mTwoDaysAgo = Storage.load(this, mTwoDaysP);


        //YESTERDAY
        listLayout.add(mJour1);
        listButton.add(mButton1);
        listTextView.add(mTextView1);
        mCalendar.add(Calendar.DATE, -1);
        final String mOneDayP = sdf.format(mCalendar.getTime());
        mYesterday = Storage.load(this, mOneDayP);


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


        Storage.load(context, "MOOD");///creer un load pour recuperer la clef

        MoodData mood1 = null;
        MoodData mood2 = null;
        MoodData mood3 = null;
        MoodData mood4 = null;
        MoodData mood5 = null;
        MoodData mood6 = null;
        MoodData mood7 = null;

        if (historyList.size() == 1) {
            mood1 = historyList.get(0);
        }
        if (historyList.size() == 2) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
        }
        if (historyList.size() == 3) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
            mood3 = historyList.get(2);
        }
        if (historyList.size() == 4) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
            mood3 = historyList.get(2);
            mood4 = historyList.get(3);
        }
        if (historyList.size() == 5) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
            mood3 = historyList.get(2);
            mood4 = historyList.get(3);
            mood5 = historyList.get(4);
        }
        if (historyList.size() == 6) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
            mood3 = historyList.get(2);
            mood4 = historyList.get(3);
            mood5 = historyList.get(4);
            mood6 = historyList.get(5);
        }
        if (historyList.size() == 7) {
            mood1 = historyList.get(0);
            mood2 = historyList.get(1);
            mood3 = historyList.get(2);
            mood4 = historyList.get(3);
            mood5 = historyList.get(4);
            mood6 = historyList.get(5);
            mood7 = historyList.get(6);
        }

        ////MOOD1
        if (mood1 == null) {
            mButton1.setVisibility(View.INVISIBLE);
        } else {
            if (mood1.getComment().isEmpty()) {
                mButton1.setVisibility(View.INVISIBLE);
            } else {
                mButton1.setVisibility(View.VISIBLE);
            }
            displayMood(mood1.getMood(), mJour1, mCurrentDate);
            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText (getApplicationContext (), historyList.get (0).getCOMMENT()+":"+mYesterday.getMood (),Toast.LENGTH_SHORT).show ();
                    Toast.makeText(getApplicationContext(), historyList.get(0).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        /////MOOD 2/////
        if (mood2 == null) {
            mButton2.setVisibility(View.INVISIBLE);
            //Toast.makeText(this, "Vous n'avez pas d'historique :(", Toast.LENGTH_SHORT).show();
        } else {
            if (mood2.getComment().isEmpty()) {
                mButton2.setVisibility(View.INVISIBLE);
            } else {
                mButton2.setVisibility(View.VISIBLE);
            }
            displayMood(mood2.getMood(), mJour2, mCurrentDate);
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), historyList.get(1).getComment(), Toast.LENGTH_SHORT).show();

                }
            });
        }

        //////MOOD 3///////|
        if (mood3 == null) {
            mButton3.setVisibility(View.INVISIBLE);
            //Toast.makeText (this, "Vous n'avez pas  d'historique :(",Toast.LENGTH_SHORT).show ();
        } else {
            if (mood3.getComment().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton3.setVisibility(View.VISIBLE);
            }
            displayMood(mood3.getMood(), mJour3, mCurrentDate);
            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(2).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        //////MOOD 4//////////
        if (mood4 == null) {
            mButton4.setVisibility(View.INVISIBLE);
            //Toast.makeText (this, "Vous n'avez pas d'historique :(",Toast.LENGTH_SHORT).show ();
        } else {
            if (mood4.getComment().isEmpty()) {
                mButton4.setVisibility(View.INVISIBLE);
            } else {
                mButton4.setVisibility(View.VISIBLE);
            }
            displayMood(mood4.getMood(), mJour4, mCurrentDate);
            mButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(3).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        ///MOOD 5//////////////////
        if (mood5 == null) {
            mButton5.setVisibility(View.INVISIBLE);
            //Toast.makeText (this, "Vous n'avez pas d'historique :(", Toast.LENGTH_SHORT).show ();
        } else {
            if (mood5.getComment().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton5.setVisibility(View.VISIBLE);
            }
            displayMood(mood5.getMood(), mJour5, mCurrentDate);
            mButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(4).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        /////MOOD 6 ///////////////////
        if (mood6 == null) {
            mButton6.setVisibility(View.INVISIBLE);
            //Toast.makeText (this, "Vous n'avez pas d'historique :(", Toast.LENGTH_SHORT).show ();
        } else {
            if (mood6.getComment().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton6.setVisibility(View.VISIBLE);
            }
            displayMood(mood6.getMood(), mJour6, mCurrentDate);
            mButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(5).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        //////MOOD 7////////////////
        if (mood7 == null) {
            mButton7.setVisibility(View.INVISIBLE);
            //Toast.makeText (this, "Vous n'avez pas  d'historique :(", Toast.LENGTH_SHORT).show ();
        } else {
            if (mood7.getComment().isEmpty()) {
                mButton7.setVisibility(View.INVISIBLE);
            } else {
                mButton7.setVisibility(View.VISIBLE);
            }
            displayMood(mood7.getMood(), mJour7, mCurrentDate);
            mButton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), historyList.get(6).getComment(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


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
            comment = getPreferences(MODE_PRIVATE).getString("COMMENT" + s, null);
            list.add(comment);
            d = stringToDate(s);
            d = removeOneDay(d);
        }
        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void displayMood(int position, RelativeLayout relativeLayout, String date) {

        ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (databaseManager.getLast7Mood() == null) {
            relativeLayout.setBackgroundColor(0);
            //relativeLayout.setVisibility(View.INVISIBLE);

        } else {
            switch (position) {
                case 0:
                    width = (display.getWidth() / 5);
                    relativeLayout.setBackgroundColor(getColor(R.color.sad_red));
                    break;

                case 1:
                    relativeLayout.setBackgroundColor(getColor(R.color.disappointed_grey));
                    width = (display.getWidth() * 4) / 5;
                    break;

                case 2:
                    relativeLayout.setBackgroundColor(getColor(R.color.normal_blue));
                    width = (display.getWidth() * 3) / 5;
                    break;

                case 3:
                    relativeLayout.setBackgroundColor(getColor(R.color.happy_green));
                    width = (display.getWidth() * 2) / 5;
                    break;

                case 4:
                    relativeLayout.setBackgroundColor(getColor(R.color.super_happy_yellow));
                    width = display.getWidth();
                    break;

            }
            LinearLayout.LayoutParams parms = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            parms.width = width;

        }

    }
}








