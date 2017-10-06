package com.example.manhngo.ms;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class Player {
    private String id;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player(String playerId, String playerName) {
        id = playerId;
        name = playerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }
}
