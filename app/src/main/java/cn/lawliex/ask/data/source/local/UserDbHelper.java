package cn.lawliex.ask.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Terence on 2016/12/27.
 */

public class UserDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ask.db";
    private static final String TEXT_TYPES = " TEXT";
    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + " user(" +
                     "id INTEGER PRIMARY KEY," +
                    "name" + TEXT_TYPES +COMMA_SEP +
                    "password" + TEXT_TYPES + COMMA_SEP +
                    "head_url" + TEXT_TYPES + COMMA_SEP +
                    "email" + TEXT_TYPES + COMMA_SEP +
                    "salt" + TEXT_TYPES + COMMA_SEP+")";

    public UserDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
