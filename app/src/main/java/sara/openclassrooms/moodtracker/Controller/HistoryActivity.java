package sara.openclassrooms.moodtracker.Controller;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import sara.openclassrooms.moodtracker.R;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<MoodData> historyList = new ArrayList<>();
    String mCurrentDate = "dd-MM-yyyy";
    private Calendar mCalendar;
    private DatabaseManager databaseManager;


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        databaseManager = new DatabaseManager(this);
        historyList = databaseManager.getLast7Mood();

        ArrayList<RelativeLayout> listLayout = new ArrayList<>();
        ArrayList<Button> listButton = new ArrayList<>();
        ArrayList<TextView> listTextView = new ArrayList<>();

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

        mCalendar = Calendar.getInstance();

        ///DAY 7
        listLayout.add(mJour7);
        listButton.add(mButton7);
        listTextView.add(mTextView7);
        mCalendar.add(Calendar.DATE, -1);

        //DAY 6
        listLayout.add(mJour6);
        listButton.add(mButton6);
        listTextView.add(mTextView6);
        mCalendar.add(Calendar.DATE, -1);

        //DAY 5
        listLayout.add(mJour5);
        listButton.add(mButton5);
        listTextView.add(mTextView5);
        mCalendar.add(Calendar.DATE, -1);

        //DAY 4
        listLayout.add(mJour4);
        listButton.add(mButton4);
        listTextView.add(mTextView4);
        mCalendar.add(Calendar.DATE, -1);

        //DAY 3
        listLayout.add(mJour3);
        listButton.add(mButton3);
        listTextView.add(mTextView3);
        mCalendar.add(Calendar.DATE, -1);

        //YESTERDAY
        listLayout.add(mJour2);
        listButton.add(mButton2);
        listTextView.add(mTextView2);
        mCalendar.add(Calendar.DATE, -1);

        //TODAY
        listLayout.add(mJour1);
        listButton.add(mButton1);
        listTextView.add(mTextView1);
        mCalendar.add(Calendar.DATE, -1);

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

        ///////////////// MOOD1 /////////////////
        if (mood1 == null) {
            mButton1.setVisibility(View.INVISIBLE);
        } else {
            if (mood1.getCOMMENT().isEmpty()) {
                mButton1.setVisibility(View.INVISIBLE);
            } else {
                mButton1.setVisibility(View.VISIBLE);
            }
            displayMood(mood1.getMOOD(), mJour1, mCurrentDate);
            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), historyList.get(0).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        ///////////////// MOOD 2 /////////////////
        if (mood2 == null) {
            mButton2.setVisibility(View.INVISIBLE);
        } else {
            if (mood2.getCOMMENT().isEmpty()) {
                mButton2.setVisibility(View.INVISIBLE);
            } else {
                mButton2.setVisibility(View.VISIBLE);
            }
            displayMood(mood2.getMOOD(), mJour2, mCurrentDate);
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), historyList.get(1).getCOMMENT(), Toast.LENGTH_SHORT).show();

                }
            });
        }

        ///////////////// MOOD 3 /////////////////
        if (mood3 == null) {
            mButton3.setVisibility(View.INVISIBLE);
        } else {
            if (mood3.getCOMMENT().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton3.setVisibility(View.VISIBLE);
            }
            displayMood(mood3.getMOOD(), mJour3, mCurrentDate);
            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(2).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        ///////////////// MOOD 4 //////////////////
        if (mood4 == null) {
            mButton4.setVisibility(View.INVISIBLE);
        } else {
            if (mood4.getCOMMENT().isEmpty()) {
                mButton4.setVisibility(View.INVISIBLE);
            } else {
                mButton4.setVisibility(View.VISIBLE);
            }
            displayMood(mood4.getMOOD(), mJour4, mCurrentDate);
            mButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(3).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        ///////////////// MOOD 5 /////////////////
        if (mood5 == null) {
            mButton5.setVisibility(View.INVISIBLE);
        } else {
            if (mood5.getCOMMENT().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton5.setVisibility(View.VISIBLE);
            }
            displayMood(mood5.getMOOD(), mJour5, mCurrentDate);
            mButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(4).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        ///////////////// MOOD 6 /////////////////
        if (mood6 == null) {
            mButton6.setVisibility(View.INVISIBLE);
        } else {
            if (mood6.getCOMMENT().isEmpty()) {
                mButton6.setVisibility(View.INVISIBLE);
            } else {
                mButton6.setVisibility(View.VISIBLE);
            }
            displayMood(mood6.getMOOD(), mJour6, mCurrentDate);
            mButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), historyList.get(5).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        ///////////////// MOOD 7 /////////////////
        if (mood7 == null) {
            mButton7.setVisibility(View.INVISIBLE);
        } else {
            if (mood7.getCOMMENT().isEmpty()) {
                mButton7.setVisibility(View.INVISIBLE);
            } else {
                mButton7.setVisibility(View.VISIBLE);
            }
            displayMood(mood7.getMOOD(), mJour7, mCurrentDate);
            mButton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), historyList.get(6).getCOMMENT(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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

        } else {
            switch (position) {
                case 0:
                    width = (display.getWidth());
                    relativeLayout.setBackgroundColor(getColor(R.color.sad_red));
                    break;

                case 1:
                    relativeLayout.setBackgroundColor(getColor(R.color.disappointed_grey));
                    width = (display.getWidth() * 2) / 5;
                    break;

                case 2:
                    relativeLayout.setBackgroundColor(getColor(R.color.normal_blue));
                    width = (display.getWidth() * 3) / 5;
                    break;

                case 3:
                    relativeLayout.setBackgroundColor(getColor(R.color.happy_green));
                    width = (display.getWidth() * 4) / 5;
                    break;

                case 4:
                    relativeLayout.setBackgroundColor(getColor(R.color.super_happy_yellow));
                    width = display.getWidth() / 5;
                    break;
            }
            LinearLayout.LayoutParams parms = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();
            parms.width = width;
        }
    }
}







