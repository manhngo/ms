package com.example.manhngo.ms.Presenter;

import android.content.Context;
import android.database.Cursor;

import com.example.manhngo.ms.Util.DBUtitls;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.models.Player;
import com.example.manhngo.ms.models.PlayerDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhngo on 10/5/17.
 */

public class PlayerPresenter {

    private PlayerDatabaseHelper databaseHelper;
    private List<Player> playerList;

    public PlayerPresenter(Context context) {
        databaseHelper = PlayerDatabaseHelper.getInstance(context);
        playerList = new ArrayList<>();
    }

    public long addPlayer(Player player) {
        return databaseHelper.addPlayer(player);
    }

    public List<Player> getPlayerList() {
        syncArrayList();
        return playerList;
    }

    public long updateNameById(Player player) {

        return databaseHelper.updatePlayerById(player);
    }

    private void syncArrayList() {
        playerList = databaseHelper.getAllPlayers();
    }

    public Cursor fetchAllPlayers() {
        return databaseHelper.fetchAllPlayers();
    }

    public Player getPlayerFromCursor(Cursor cursor) {
        Player player = new Player("UNKNOWN_NAME");
        player.setId(cursor.getLong(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_ID)));
        player.setName(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME)));
        if (cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_FUNCTION)) != null) {
            player.setFunction(Function.valueOf(cursor.getString(cursor.getColumnIndex(
                    DBUtitls.PLAYERS_COLUMN_FUNCTION))));
        }
        return player;
    }
}
