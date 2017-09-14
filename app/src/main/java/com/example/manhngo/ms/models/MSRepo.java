package com.example.manhngo.ms.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manhngo.ms.Player;
import com.example.manhngo.ms.Util.DBUtitls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhngo on 9/14/17.
 */

public class MSRepo {
    private static MSRepo ourInstance;
    private MSSqLiteHelper msSqLiteHelper;

    private MSRepo(Context context) {
        msSqLiteHelper = new MSSqLiteHelper(context);
    }

    public static MSRepo getInstance(Context context) {
        return (ourInstance == null) ? new MSRepo(context) : ourInstance;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        SQLiteDatabase database = msSqLiteHelper.getReadableDatabase();
        String[] columns = {
                DBUtitls.PLAYER_COLUMN_ID,
                DBUtitls.PLAYER_COLUMN_NAME
        };
        Cursor cursor = database.query(DBUtitls.PLAYER_TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String playerId = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.PLAYER_COLUMN_ID));
                String playerName = cursor.getString(cursor.getColumnIndexOrThrow(DBUtitls.PLAYER_COLUMN_NAME));

                players.add(new Player(playerId, playerName));
            }
        }
        return players;
    }

    public void insertPlayer(Player player) {
        SQLiteDatabase database = msSqLiteHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBUtitls.PLAYER_COLUMN_ID, player.getId());
        contentValues.put(DBUtitls.PLAYER_COLUMN_NAME, player.getName());
        String selections = DBUtitls.PLAYER_COLUMN_ID + " = ?";
        String[] selectionArgs = {player.getId()};
        database.update(DBUtitls.PLAYER_TABLE_NAME, contentValues, selections, selectionArgs);
    }


}
