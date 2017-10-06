package com.example.manhngo.ms.Util;


import com.example.manhngo.ms.models.Player;

import java.util.List;

/**
 * Created by NgoXuanManh on 7/23/2017.
 */

public class MyUtils {

    public static void prepareRecyclerViewData(final List<Player> nguoiChois) {
        Player player = new Player("Manh");
        nguoiChois.add(player);
        player = new Player("Trang");
        nguoiChois.add(player);
        player = new Player("Ba Duc");
        nguoiChois.add(player);
        player = new Player("Me Huong");
        nguoiChois.add(player);
    }
}
