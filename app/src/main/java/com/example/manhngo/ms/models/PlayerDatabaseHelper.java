package com.example.manhngo.ms.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.manhngo.ms.Util.DBUtitls;
import com.example.manhngo.ms.Util.Function;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by manhngo on 10/5/17.
 */

public class PlayerDatabaseHelper extends SQLiteOpenHelper {
    // Database Info

    private static PlayerDatabaseHelper sInstance;

    private PlayerDatabaseHelper(Context context) {
        super(context, DBUtitls.DATABASE_NAME, null, DBUtitls.DATABASE_VERSION);
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
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PLAYERS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s TEXT UNIQUE, %s TEXT)",
                DBUtitls.PLAYERS_TABLE, DBUtitls.PLAYERS_COLUMN_ID, DBUtitls.PLAYERS_COLUMN_NAME, DBUtitls.PLAYERS_COLUMN_FUNCTION);

        String CREATE_PLAYER_DETAILS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY, %s INTEGER UNIQUE REFERENCES %s, %s TEXT, %s BLOB DEFAULT TRUE)",
                DBUtitls.PLAYER_DETAILS_TABLE,
                DBUtitls.PLAYER_DETAILS_COLUMN_ID, DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER,
                DBUtitls.PLAYERS_TABLE,
                DBUtitls.PLAYER_DETAILS_COLUMN_FUNCTION, DBUtitls.PLAYER_DETAILS_COLUMN_SURVIVE);

        sqLiteDatabase.execSQL(CREATE_PLAYERS_TABLE);
//        sqLiteDatabase.execSQL(CREATE_PLAYER_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBUtitls.PLAYERS_TABLE);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBUtitls.PLAYER_DETAILS_TABLE);
        onCreate(sqLiteDatabase);
    }

    // Insert a post into the database
    public long addPlayer(Player player) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();
        long id;
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            Log.d(TAG, "addPlayer: " + player.getName());
            values.put(DBUtitls.PLAYERS_COLUMN_NAME, player.getName());
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            id = db.insertOrThrow(DBUtitls.PLAYERS_TABLE, null, values);
            player.setId(id);
            Log.d(TAG, "addPlayer: " + id);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            id = -1;
            Log.d(TAG, "Error while trying to add player to database " + e);
        } finally {
            db.endTransaction();
        }
        return id;
    }

    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        String PLAYERS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        DBUtitls.PLAYERS_TABLE);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(PLAYERS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Player player = new Player("UNKNOWN_NAME");
                    player.setId(cursor.getLong(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_ID)));
                    player.setName(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME)));
                    if (cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION)) != null) {
                        player.setFunction(Function.valueOf(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION))));
                    }
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

    public Cursor fetchAllPlayers() {

        String PLAYERS_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        DBUtitls.PLAYERS_TABLE);

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(PLAYERS_SELECT_QUERY, null);
    }

    public long updateFunctionByName(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBUtitls.PLAYERS_COLUMN_FUNCTION, player.getName());

        // Updating profile picture url for user with that userName
        return db.update(DBUtitls.PLAYERS_TABLE, values, DBUtitls.PLAYERS_COLUMN_NAME + " = ?",
                new String[]{String.valueOf(player.getName())});
    }

    public long updatePlayerById(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBUtitls.PLAYERS_COLUMN_NAME, player.getName());
        if (player.getFunction() != null) {
            values.put(DBUtitls.PLAYERS_COLUMN_FUNCTION, player.getFunction().toString());
        }
        // Updating profile picture url for user with that userName
        return db.update(DBUtitls.PLAYERS_TABLE, values, DBUtitls.PLAYERS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(player.getId())});
    }

    public long updateFunctionPlayerById(long id, Function function) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBUtitls.PLAYERS_COLUMN_FUNCTION, String.valueOf(function));
        // Updating profile picture url for user with that userName
        return db.update(DBUtitls.PLAYERS_TABLE, values, DBUtitls.PLAYERS_COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Player getPlayerById(String id) {
        SQLiteDatabase db = getReadableDatabase();
        String where = DBUtitls.PLAYERS_COLUMN_ID + " = ?";
        String[] whereArgs = {id};
        Cursor cursor = db.query(DBUtitls.PLAYERS_TABLE, null, where, whereArgs, null, null, null);
        Player player = null;
        try {
            if (cursor.moveToFirst()) {

                player = new Player("UNKNOWN_NAME");
                player.setId(cursor.getLong(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_ID)));
                player.setName(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME)));
                if (cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION)) != null) {
                    player.setFunction(Function.valueOf(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION))));
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get player from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return player;
    }
    public void deleteAllPlayers() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(DBUtitls.PLAYERS_TABLE, null, null);
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
            sqLiteDatabase.delete(DBUtitls.PLAYERS_TABLE, DBUtitls.PLAYERS_COLUMN_ID + " = " + id, null);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete player");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

//    public long updateFunctionPlayerDetails(long id, String function) {
//        SQLiteDatabase writableDatabase = getWritableDatabase();
//        writableDatabase.beginTransaction();
//        try {
//            ContentValues values = new ContentValues();
//            values.put(DBUtitls.PLAYER_DETAILS_COLUMN_PLAYER, player.getId());
//            values.put(DBUtitls.PLAYER_DETAILS_COLUMN_FUNCTION, function);
//            id = writableDatabase.insertOrThrow(DBUtitls.PLAYER_DETAILS_TABLE, null, values);
//
//        } catch (Exception e) {
//            id = -1;
//            Log.d(TAG, "updateFunctionPlayerDetails: " + e);
//        } finally {
//            writableDatabase.endTransaction();
//        }
//        return id;
//    }

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
