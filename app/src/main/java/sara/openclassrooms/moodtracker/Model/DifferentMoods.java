package sara.openclassrooms.moodtracker.Model;

import android.content.SharedPreferences;

public enum DifferentMoods  {
    Sad,
    Disappointed,
    Normal,
    Happy,
    SuperHappy;

    private static DifferentMoods[]list = DifferentMoods.values();
    private int moodBackgroundColor;
    private String mComment;
    private int mPosition;

    public static DifferentMoods getMood(int i){
        return list[i];
    }
    public static int listGetLastIndex(){
        return list.length;
    }


    public static Object get(int position) {
        return position;

    }
    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }
    public String getComment() {
        return mComment;
    }


    public int getPosition() {
        return mPosition;
    }
}


