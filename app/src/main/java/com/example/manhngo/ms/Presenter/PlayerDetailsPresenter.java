package com.example.manhngo.ms.Presenter;

import android.content.Context;

import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.models.PlayerDatabaseHelper;

import java.util.List;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class PlayerDetailsPresenter {

    private PlayerDatabaseHelper playerDatabaseHelper;

    public PlayerDetailsPresenter(Context context) {
        playerDatabaseHelper = PlayerDatabaseHelper.getInstance(context);
    }


    public List<Integer> getPlayersByFunction(String function) {
        return playerDatabaseHelper.getPlayersByFunction(function);
    }

    public long updateFunctionPlayerDetails(long id, Function function) {
        return playerDatabaseHelper.updateFunctionPlayerById(id, function);
    }

}
