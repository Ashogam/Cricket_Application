package com.sqlite.cric_grap;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ANDROID on 22-11-2015.
 */
public class Player_Score_Information {

    private Context context;
    DBHelper dbHelper = null;
    SQLiteDatabase sqLiteDatabase = null;

    public Player_Score_Information(Context context) {
        this.context = context;

    }

    public void open() {
        dbHelper = new DBHelper(context);
        try {
            sqLiteDatabase = dbHelper.getWritableDatabase();
            Log.d("DataBase opened", "Open");
        } catch (Exception e) {
            Log.d("DataBase opened", "Failed exception");
            e.printStackTrace();
        }

    }

    public void close() {

        try {
            if (dbHelper != null) {
                Log.d("DataBase closed", "close");
                dbHelper.close();
            }
        } catch (Exception e) {
            Log.d("closed Exception", "exception");
            e.printStackTrace();
        }

    }

    public void delete() {
        try {

            sqLiteDatabase.delete(DBHelper.TABLE_NAME, null, null);
            System.out.println("DeleteTable Gets Called");
        } catch (Exception exception) {
            System.out.println("DeleteTable one Gets Exception");
        }

    }



    private static class DBHelper extends SQLiteOpenHelper {
        private final String TAG="Player_DB";
        private static File dir = new File(Environment.getExternalStorageDirectory() + "");
        private static String DATABASE_NAME = dir + "/Cric_Grab/Player.db";// dir+"/
        private static final int DATABASE_VERSION = 7;
        private static final String TABLE_NAME = "PLAYER_SCORE_INF";
        private static final String TABLE_EXTRA_NAME = "PLAYER_SCORE_EXTRAS";
        private static final String SIX = "SIX";
        private static final String FOUR = "FOUR";
        private static final String THREE = "THREE";
        private static final String TWO = "TWO";
        private static final String ONE = "ONE";
        private static final String OUT = "OUT";
        private static final String OVER = "OVER";
        private static final String EXTRA = "EXTRA_SCORE";
        private static final String PLAYER_NAME = "PLAYER_NAME";
        private static final String DATE = "CREATED_DATE";
        private Context context;
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + PLAYER_NAME + " VARCHAR2(255)," + OVER + " VARCHAR2(255)," + SIX + " VARCHAR2(255)," + FOUR + " VARCHAR2(255)," + THREE + " VARCHAR2(255)" +
                "," + TWO + " VARCHAR2(255)," + ONE + " VARCHAR2(255)," + OUT + " VARCHAR2(255)," + DATE + " DATETIME,PRIMARY KEY(" +OVER+","+DATE+"))";

        private static final String CREATE_EXTRA_TABLE = "CREATE TABLE " + TABLE_EXTRA_NAME + " (" + PLAYER_NAME + " VARCHAR2(255)," + OVER + " VARCHAR2(255)," + EXTRA + " VARCHAR2(255)," + DATE + " DATETIME,PRIMARY KEY("+OVER+","+DATE+"))";
        private static final String Drop_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private static final String Drop_EXTRA_TABLE = "DROP TABLE IF EXISTS " + TABLE_EXTRA_NAME;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_EXTRA_TABLE);
                Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Table Created");

            } catch (SQLException e
                    ) {
                e.printStackTrace();
                Log.i(TAG, "Table Created Exception");

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(Drop_TABLE);
                db.execSQL(Drop_EXTRA_TABLE);
                Toast.makeText(context, "Table Upgraded", Toast.LENGTH_SHORT).show();
                onCreate(db);
                Log.i(TAG, "Table upgraded");

            } catch (SQLException e) {
                e.printStackTrace();
                Log.i(TAG, "Table Upgraded Exception");

            }

        }
    }
}
