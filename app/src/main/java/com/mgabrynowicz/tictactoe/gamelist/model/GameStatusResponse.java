package com.mgabrynowicz.tictactoe.gamelist.model;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class GameStatusResponse {
    private final GameStatus data;


    public GameStatusResponse(GameStatus data) {
        this.data = data;
    }

    public GameStatus getData() {
        return data;
    }
}
