package sara.openclassrooms.moodtracker.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DatabaseManager extends SQLiteOpenHelper {

    //Name of my dataBase constant
    private static final String DATA_BASE_NAME = "Commentaries.db";

    //DataBAse version constant
    private static final int DATABASE_VERSION = 1;
    private int IdMoodExist;


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

        //mCurrentDate is the date of the day
        mCurrentDate = simpleDateFormat.format(new Date());

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

    // Update of the Row, we do change only the MoodValue and userInputValue WHERE when_ = mCurrent
    public boolean updateRow(int moodValue, String userInputValue, String mCurrentDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mood", moodValue);
        contentValues.put("comment", userInputValue);
        db.update("T_mood", contentValues, "when_ = '" + mCurrentDate + "'", null);
        return true;
    }


    public boolean dateExistInDatabase() {
        String dateExistInDatabase = "SELECT when_, _id FROM T_Mood ";
        Cursor cursor = this.getReadableDatabase().rawQuery(dateExistInDatabase, null);
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


    //creation du cursor
    //on remplit la liste
    public ArrayList<MoodData> getLast7Mood() {
        String[] columns = {"mood", "comment", "when_ ", "_id"};
        String[] selectArgs = {};
        Cursor cursor = getReadableDatabase().query("T_Mood", columns, "", selectArgs, "", "", " _id desc limit 7");
        ArrayList<MoodData> moodDataArrayList = new ArrayList<>();
        String dateInDatabase = "";
        while (cursor.moveToNext()) {
            MoodData moodData = new MoodData(cursor.getInt(cursor.getColumnIndex("mood")), cursor.getString(cursor.getColumnIndex("comment")), cursor.getString(cursor.getColumnIndex("when_")));
            String COMMENT = cursor.getString(cursor.getColumnIndex("comment"));
            String WHEN_ = cursor.getString(cursor.getColumnIndex("when_"));
            IdMoodExist = cursor.getInt(cursor.getColumnIndex("_id"));
            int MOOD = cursor.getInt(cursor.getColumnIndex("mood"));
            moodData.setMOOD(MOOD);
            moodData.setCOMMENT(COMMENT);
            moodData.setWHEN_(WHEN_);
            moodDataArrayList.add(moodData);
            dateInDatabase = cursor.getString(cursor.getColumnIndex("when_"));
            cursor.moveToNext();

            //cancel the data if the date is
            try {
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                SQLiteDatabase db = this.getWritableDatabase();

                Date today = new Date();//date de reference
                Date dateFromDatabase = simpleDateFormat.parse(dateInDatabase);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);//I get the today's date

                Calendar databaseCalendar = Calendar.getInstance();
                databaseCalendar.setTime(dateFromDatabase);

                //we compare if the date of today less the date of the db are greater than 7  we delete
                if (calendar.get(Calendar.DAY_OF_MONTH) - databaseCalendar.get(Calendar.DAY_OF_MONTH) > 7) {
                    db.delete("T_Mood", "_id = " + IdMoodExist + "", null);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return moodDataArrayList;

    }
}






