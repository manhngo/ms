package com.example.manhngo.ms.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.manhngo.ms.Util.DBUtitls;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class PlayerDetailsDatabaseHelper extends SQLiteOpenHelper {

    private static PlayerDetailsDatabaseHelper sInstance;

    private PlayerDetailsDatabaseHelper(Context context) {
        super(context, DBUtitls.DATABASE_NAME, null, DBUtitls.DATABASE_VERSION);
    }

    public static synchronized PlayerDetailsDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PlayerDetailsDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PLAYERS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s INTEGER UNIQUE, %s TEXT, %s BLOB DEFAULT TRUE)",
                DBUtitls.PLAYER_DETAILS_TABLE,
                DBUtitls.PLAYER_DETAILS_COLUMN_ID, DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER,
                DBUtitls.PLAYER_DETAILS_COLUMN_FUNCTION, DBUtitls.PLAYER_DETAILS_COLUMN_SURVIVE);
        sqLiteDatabase.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBUtitls.PLAYER_DETAILS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addPlayerDetails(Player player, String function) {
        SQLiteDatabase db = getWritableDatabase();
        long id;
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER, player.getId());
            values.put(DBUtitls.PLAYER_DETAILS_COLUMN_FUNCTION, function);
            id = db.insertOrThrow(DBUtitls.PLAYER_DETAILS_TABLE, null, values);

        } catch (Exception e) {
            id = -1;
            Log.d(TAG, "addPlayerDetails: " + e);
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public long updateSurviveByPlayer(Player player, boolean value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBUtitls.PLAYER_DETAILS_COLUMN_SURVIVE, value);
        return db.update(DBUtitls.PLAYER_DETAILS_TABLE, values,
                DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER + " = ?",
                new String[]{String.valueOf(player.getId())});
    }

    public List<Integer> getPlayersByFunction(String function) {
        List<Integer> listId = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DBUtitls.PLAYER_DETAILS_TABLE, null, DBUtitls.PLAYER_DETAILS_COLUMN_FUNCTION + " = ?", new String[]{function}, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    listId.add((int) cursor.getLong(cursor.getColumnIndex(DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get 'id player' from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        if (cursor != null)
            cursor.moveToFirst();
        return listId;
    }
}
