package sara.openclassrooms.moodtracker.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DatabaseManager extends SQLiteOpenHelper {

    //Name of my dataBase constant
    private static final String DATA_BASE_NAME = "Commentaries.db";

    //DataBAse version constant
    private static final int DATABASE_VERSION = 1;

    public static final String COMMENT = "comment";
    public static final String MOOD = "mood";


    public DatabaseManager(Context context) {//constructeur context en parametre c'est la classe de base
        super (context, DATA_BASE_NAME, null, DATABASE_VERSION);//4 parametres
    }


    //premiere fois que l'on ouvre la base de donnee on l'a cree ici
    //parametre SQLiteDatabase db objet  de donnee
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table T_Mood ("
                + "     _id INTEGER primary key autoincrement,"
                + "     comment TEXT not null,"
                + "     mood INTEGER not null,"
                + "     when_ TEXT not null"
                + ")";

        db.execSQL (strSql);//j'excute ma ligne de code
        Log.i ("DATABASE", "onCreate invoked");//on verifie que la methode soit utiliser 1 fois

    }

    //methode qui met a jour ma base de donnee changement de version
    //on met a jour ici lanouvelle based
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL = "drop table T_Mood";
        db.execSQL (strSQL);
        this.onCreate (db);
        Log.i ("DATABASE", "onUpgrade invoked");

    }

    public void insertMood(int moodValue, String userInputValue, String mCurrentDate) {

        //passer la date en string
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


        mCurrentDate = simpleDateFormat.format (new Date ());
        //String mCurrentDateIsmCurrentDate = mCurrentDateIsmCurrentDate();


//       userInputValue = userInputValue.replace ("'", "''");
        String strSql = "insert into T_Mood (mood,comment,when_) values (" + moodValue + ",'" + userInputValue + "','" + simpleDateFormat.format (new Date ()) + "')";

        //j'envoie mon ordre sql a la base this=dataBaseManager
        this.getWritableDatabase ().execSQL (strSql);
        Log.i ("DATABASE", "insertScore invoked");

    }


    //creation du cursor
    //on remplit la liste
    public ArrayList<MoodData> getLast7Mood(){

        String[] columns = {"mood","comment","when"};
        String[] selectArgs ={};
        Cursor cursor = getReadableDatabase ().query ("T_Mood",columns,"",selectArgs,"","","");
                ArrayList<MoodData> moodDataArrayList = new ArrayList<> ();
        while (cursor.moveToNext ())   {
            MoodData moodData = new MoodData (cursor.getInt (0),cursor.getInt (1),cursor.getString (2),cursor.getString (3));

            String COMMENT = cursor.getString (cursor.getColumnIndex ("comment"));
            String WHEN_ = cursor.getString (cursor.getColumnIndex ("when_"));
            int MOOD = cursor.getInt (cursor.getColumnIndex ("mood"));
            moodData.setMOOD (MOOD);
            moodData.setCOMMENT (COMMENT);
            moodData.setWHEN_ (WHEN_);
            moodDataArrayList.add (moodData);
        }
return moodDataArrayList;
    }


}

