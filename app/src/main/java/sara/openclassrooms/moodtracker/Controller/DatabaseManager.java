package sara.openclassrooms.moodtracker.Controller;

import android.content.ContentValues;
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
    private int IdMoodExist ;



    //constructeur context en parametre c'est la classe de base
    public DatabaseManager(Context context) {
        super(context, DATA_BASE_NAME, null, DATABASE_VERSION);//4 parametres
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

        db.execSQL(strSql);//j'excute ma ligne de code
        Log.i("DATABASE", "onCreate invoked");//on verifie que la methode soit utiliser 1 fois

    }

    //methode qui met a jour ma base de donnee changement de version
    //on met a jour ici la nouvelle base de donnee
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSQL = "drop table T_Mood";
        db.execSQL(strSQL);
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");

    }

    public void insertMood(int moodValue, String userInputValue, String mCurrentDate) {
        //passer la date en string
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        mCurrentDate = simpleDateFormat.format(new Date());


        boolean result;

        //on update le mood si la date exist
        if (dateExistInDatabase()) {
            updateRow(moodValue, userInputValue, mCurrentDate);
        } else {
            String strSql = "insert into T_Mood (mood,comment,when_) values (" + moodValue + ",'" + userInputValue + "','" + simpleDateFormat.format(new Date()) + "')";

            //j'envoie mon ordre sql a la base this=dataBaseManager
            this.getWritableDatabase().execSQL(strSql);
            Log.i("DATABASE", "insertScore invoked");
        }
    }


    //creation du cursor
    //on remplit la liste
    public ArrayList<MoodData> getLast7Mood() {
        String[] columns = {"mood", "comment", "when_ ","_id"};
        String[] selectArgs = {};
        Cursor cursor = getReadableDatabase().query("T_Mood", columns, "", selectArgs, "", "", "");
        ArrayList<MoodData> moodDataArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            MoodData moodData = new MoodData(cursor.getInt(cursor.getColumnIndex("mood")), cursor.getString(cursor.getColumnIndex("comment")), cursor.getString(cursor.getColumnIndex("when_")));
            //MoodData moodData = new MoodData(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3));
            String COMMENT = cursor.getString(cursor.getColumnIndex("comment"));
            String WHEN_ = cursor.getString(cursor.getColumnIndex("when_"));
            IdMoodExist = cursor.getInt(cursor.getColumnIndex("_id"));
            int MOOD = cursor.getInt(cursor.getColumnIndex("mood"));
            moodData.setMOOD(MOOD);
            moodData.setCOMMENT(COMMENT);
            moodData.setWHEN_(WHEN_);

            moodDataArrayList.add(moodData);
            cursor.moveToNext();
        }
        cursor.close();

        return moodDataArrayList;
    }

    public boolean dateExistInDatabase() {
        String dateExistInDatabase = "SELECT when_, _id FROM T_Mood";
        Cursor cursor = this.getReadableDatabase().rawQuery(dateExistInDatabase,null);
        cursor.moveToFirst();
        String dateInDataBase = null;
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String todayDate = simpleDateFormat.format(new Date());
        boolean dateExist = false;

        while (!cursor.isAfterLast()) {
            dateInDataBase = cursor.getString(cursor.getColumnIndex("when_"));
            if (dateInDataBase.equals(todayDate)) {
                dateExist = true;
                IdMoodExist = cursor.getInt(cursor.getColumnIndex("_id"));
                break;
            } else {
                cursor.moveToNext();
            }
        }
        cursor.close();
        return dateExist;
    }

    public boolean updateRow(int moodValue, String userInputValue, String mCurrentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mood", moodValue);
        contentValues.put("comment", userInputValue);
        db.update("T_mood", contentValues, "when_ = '" + mCurrentDate + "'", null);
        //IdMoodExist = Integer.parseInt("SELECT _id FROM T_Mood");
        return true;
    }

    public Cursor getMoodComment() {
        Cursor c = this.getReadableDatabase().query("T_mood", new String[]{"_id", "mood", "comment"}, null, null, null, null, "_id" + " DESC", "7");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}


