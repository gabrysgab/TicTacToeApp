package com.mgabrynowicz.tictactoe;

/**
 * Created by RENT on 2016-12-03.
 */

public class GameGrid {

    private Boolean[] gameGrid;

    public GameGrid() {
        this.gameGrid = new Boolean[9];
    }

    public Boolean[] getGameGrid() {
        return gameGrid;
    }
}