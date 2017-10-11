package com.example.manhngo.ms.models;

import android.database.Cursor;

import com.example.manhngo.ms.Util.DBUtitls;
import com.example.manhngo.ms.Util.Function;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class Player {
    private long id;
    private String name;
    private Function function;

    public Player(String name) {
        this.name = name;
        this.function = Function.NOTHING;
    }

    public static Player fromCursor(Cursor cursor) {
        Player player = new Player("UNKNOWN_NAME");
        player.setId(cursor.getLong(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_ID)));
        player.setName(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME)));
        if (cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION)) != null) {
            player.setFunction(Function.valueOf(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION))));
        }
        return player;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "{id: " + getId() + ", name: " + getName() + ", function: " + getFunction() + "}";
    }
}
