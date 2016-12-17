package com.mgabrynowicz.tictactoe.gamelist.model;

/**
 * Created by RENT on 2016-12-17.
 */

public class Game {

    private final String id;
    private final String name;
    private final String creator;
    private final String player;

    public Game(String id, String name, String creator, String player) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.player = player;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getPlayer() {
        return player;
    }
}
