package DataLayer.DataAccessLayer;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Hendrik on 14-Dec-17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "2Lib_Data.db";

    /* When an object of SQLiteHelper is created, this constructor creates the Database */
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* This method is called when the Database is first created */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /* This method is called when the database version has changed */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
