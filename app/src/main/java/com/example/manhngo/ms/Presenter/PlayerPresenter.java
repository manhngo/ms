package com.example.manhngo.ms.Presenter;

import com.example.manhngo.ms.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhngo on 10/5/17.
 */

public class PlayerPresenter {

    private List<Player> playerList;

    public PlayerPresenter() {
        playerList = new ArrayList<>();
    }

    public void addPlayer(Player player) {

    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
