package com.example.manhngo.ms.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.manhngo.ms.Util.DBUtitls;

/**
 * Created by manhngo on 9/12/17.
 */

public class SoiDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_PLAYERD_TABLE_SQL =
            "CREATE TABLE " + DBUtitls.PLAYER_TABLE_NAME + " ("
                    + DBUtitls.PLAYER_COLUMN_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY + ", "
                    + DBUtitls.PLAYER_COLUMN_NAME + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ")";
    public static final String CREATE_BAOVE_TABLE_SQL =
            "CREATE TABLE " + DBUtitls.BAOVE_TABLE_NAME + " ("
                    + DBUtitls.BAOVE_COLUMN_ID + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.PRIMARY_KEY + ", "
                    + DBUtitls.BAOVE_COLUMN_PLAYER + " " + DBUtitls.TEXT_DATA_TYPE + " " + DBUtitls.NOT_NULL + ")";
    public static final String INSERT_PLAYED_TABLE_SQL =
            "INSERT INTO " + DBUtitls.PLAYER_TABLE_NAME + " (" + DBUtitls.PLAYER_COLUMN_ID + ", " + DBUtitls.PLAYER_COLUMN_NAME + ")"
                    + " VALUES "
                    + "('1', 'Manh'), " + "('2', 'Trang')";
    private static final String DATABASE_NAME = "MaSoi";
    private static final int DATABASE_VERSION = 1;

    SoiDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BAOVE_TABLE_SQL);
        sqLiteDatabase.execSQL(CREATE_PLAYERD_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
