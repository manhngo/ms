package com.example.manhngo.ms.Presenter;

import android.content.Context;

import com.example.manhngo.ms.models.PlayerDetailsDatabaseHelper;

import java.util.List;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class PlayerDetailsPresenter {

    PlayerDetailsDatabaseHelper playerDetailsDatabaseHelper;

    public PlayerDetailsPresenter(Context context) {
        playerDetailsDatabaseHelper = PlayerDetailsDatabaseHelper.getInstance(context);
    }

    public List<Integer> getPlayersByFunction(String function) {
        return playerDetailsDatabaseHelper.getPlayersByFunction(function);
    }

}
