package com.mgabrynowicz.tictactoe.game.model;

/**
 * Created by RENT on 2016-12-19.
 */

public class JoinGameRequest {

    private final String game;


    public JoinGameRequest(String game) {
        this.game = game;
    }

    public String getGame() {
        return game;
    }
}
