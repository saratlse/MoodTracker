package sara.openclassrooms.moodtracker.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import sara.openclassrooms.moodtracker.Model.Mood;

import static android.content.Context.MODE_PRIVATE;

public class Storage {

            public static void store(Context context, Mood mood, String moodKey){

                SharedPreferences mSharedPref = context.getSharedPreferences(moodKey, MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mSharedPref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mood);
                prefsEditor.putString(moodKey,json);
                prefsEditor.apply();
            }

            public static Mood load(Context context, String moodKey){
                SharedPreferences mSharedPref = context.getSharedPreferences(moodKey, MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mSharedPref.getString(moodKey, "");
                Mood mood = gson.fromJson(json, Mood.class);
                return mood;
            }

}





