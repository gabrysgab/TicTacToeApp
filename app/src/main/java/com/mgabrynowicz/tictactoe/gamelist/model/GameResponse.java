package com.mgabrynowicz.tictactoe.gamelist.model;

import java.util.List;

/**
 * Created by RENT on 2016-12-17.
 */

public class GameResponse {


    private final List<Game> data;


    public GameResponse(List<Game> data) {
        this.data = data;
    }

    public List<Game> getData() {
        return data;
    }
}
