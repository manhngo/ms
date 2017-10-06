package com.example.manhngo.ms.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.manhngo.ms.Player;
import com.example.manhngo.ms.Util.DBUtitls;
import com.example.manhngo.ms.Util.Function;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by manhngo on 10/5/17.
 */

public class PlayerDatabaseHelper extends SQLiteOpenHelper {

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
    // Database Info
    private static final String DATABASE_NAME = "MaSoi";
    private static final int DATABASE_VERSION = 1;
    // Table Names
    private static final String TABLE_PLAYERS = "players";
    // Post Table Columns
    private static final String KEY_PLAYER_ID = "id";
    private static final String KEY_PLAYER_NAME = "name";
    private static final String KEY_PLAYER_FUNCTION = "function";
    // User Table Columns
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";
    private static PlayerDatabaseHelper sInstance;

    private PlayerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized PlayerDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new PlayerDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PLAYERS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)",
                TABLE_PLAYERS, KEY_PLAYER_ID, KEY_PLAYER_NAME, KEY_PLAYER_FUNCTION);
        sqLiteDatabase.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        onCreate(sqLiteDatabase);
    }

    // Insert a post into the database
    public void addPlayer(Player player) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            Log.d(TAG, "addPlayer: " + player.getName());
            values.put(KEY_PLAYER_NAME, player.getName());
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            long id = db.insertOrThrow(TABLE_PLAYERS, null, values);
            player.setId(id);
            Log.d(TAG, "addPlayer: " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add player to database");
        } finally {
            db.endTransaction();
        }
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        String PLAYERS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        TABLE_PLAYERS);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(PLAYERS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Player player = new Player("UNKNOWN_NAME");
                    player.setName(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_NAME)));
                    player.setFunction(Function.valueOf(cursor.getString(cursor.getColumnIndex(KEY_PLAYER_FUNCTION))));
                    players.add(player);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return players;
    }

    public int updateFunctionByName(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_FUNCTION, player.getName());

        // Updating profile picture url for user with that userName
        return db.update(TABLE_PLAYERS, values, KEY_PLAYER_NAME + " = ?",
                new String[]{String.valueOf(player.getName())});
    }

    public void deleteAllPlayers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(TABLE_PLAYERS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }
    }

    public void deletePlayerById(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.delete(TABLE_PLAYERS, KEY_PLAYER_ID + " = " + id, null);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete player");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
}
