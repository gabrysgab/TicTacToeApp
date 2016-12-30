package com.mgabrynowicz.tictactoe.gamelist.model;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class GameStatus {
    private final String gameId;
    private final String name;
    private final String type;

    public GameStatus(String gameId, String name, String type) {
        this.gameId = gameId;
        this.name = name;
        this.type = type;
    }

    public String getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
