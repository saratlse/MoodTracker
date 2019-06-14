package sara.openclassrooms.moodtracker.Controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sara.openclassrooms.moodtracker.Model.DifferentMoods;
import sara.openclassrooms.moodtracker.R;

import static sara.openclassrooms.moodtracker.Controller.MainActivity.PREF_KEY_COMMENT;

public class HistoryActivity extends AppCompatActivity {


    private SharedPreferences mSharedPref;
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    private Calendar mCalendar;
    private Date mDate;
    private String mComment;





    private SimpleDateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mSharedPref = getSharedPreferences("PREF_KEY_COMMENT",MODE_PRIVATE);
        mComment= mSharedPref.getString("REF_KEY_COMMENT","");



        ArrayList<RelativeLayout>listLayout = new ArrayList<>();
        ArrayList<Button>listButton = new ArrayList<>();
        ArrayList<TextView>listTextView = new ArrayList<>();




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




        listLayout.add(mJour7);
        listButton.add(mButton7);
        listTextView.add(mTextView7);


        listLayout.add(mJour6);
        listButton.add(mButton6);
        listTextView.add(mTextView6);

        listLayout.add(mJour5);
        listButton.add(mButton5);
        listTextView.add(mTextView5);

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





        //Comment J-2
//       mCalendar.add(Calendar.DATE,-1);
        //String mC

      layoutConstructor(listLayout,listButton,listTextView);




        }

        //constructor of different Layout (layout,button,textView)

    public void layoutConstructor(ArrayList<RelativeLayout>listLayout,ArrayList<Button>listButton,ArrayList<TextView>listTextView){
        for (int i = 1; 1<=listLayout.size(); i++){

            SharedPreferences mSharedPref = getApplicationContext().getSharedPreferences("PREF_KEY_COMMENT",0);

            //recuperer le commentaire  mainActivity
            String commentaire = mSharedPref.getString("COMMENT_"+i,null);

            if (commentaire == null || commentaire.isEmpty()){

                listLayout.get(i-1).setBackgroundColor(Color.RED);

                listButton.get(i-1).setVisibility(View.INVISIBLE);
            }else{
                listTextView.get(i-1).setText(mSharedPref.getString("COMMENT_"+i,null));
                listLayout.get(i-1).setBackgroundColor(Color.TRANSPARENT);
            }

        }
    }




    private void displayMood(Date date, RelativeLayout relativeLayout) {
        //Change the width of the layout
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        try {
            display.getRealSize(size);
        } catch (NoSuchMethodError err) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;

        // Get a mood from a past day
        DifferentMoods mood = null;

        // If there is no mood, there is nothing to display
        if (mood == null) {
            relativeLayout.setBackgroundColor(0);
        } else {
            // Display mood width and color
            switch (mood) {
                case Sad:

                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.sad_red));
                    break;
                case Disappointed:
                    //relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 2,
                            //LinearLayout.LayoutParams.MATCH_PARENT, 1));
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.disappointed_grey));
                    break;
                case Normal:
                    //relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 3,
                            //LinearLayout.LayoutParams.MATCH_PARENT, 1));
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.normal_blue));
                    break;
                case Happy:
                    //relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 4,
                            //LinearLayout.LayoutParams.MATCH_PARENT, 1));
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.happy_green));
                    break;
                case SuperHappy:
                    //relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(width,
                            //LinearLayout.LayoutParams.MATCH_PARENT, 1));
                    relativeLayout.setBackgroundColor(getResources().getColor(R.color.super_happy_yellow));
                    break;
            }

        }
    }



    private void displayComment(Date date, Button button) {



        // Get the comment of the chosen day
        final String mNote = mSharedPref.getString(PREF_KEY_COMMENT, null);

        // If there is a comment, display the button
        if (mNote != null && !mNote.isEmpty()) {
            button.setVisibility(View.VISIBLE);
            // Display the comment in a toast message when button clicked
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mNote, Toast.LENGTH_SHORT).show();
                }
            });
            // If there is no comment, don't display the button
        } else {
            button.setVisibility(View.GONE);
        }
    }
    
}

