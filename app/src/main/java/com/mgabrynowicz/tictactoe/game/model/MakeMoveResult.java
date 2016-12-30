package com.mgabrynowicz.tictactoe.game.model;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class MakeMoveResult {


    private final String errorMessage;

    public MakeMoveResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MakeMoveResult() {
        this.errorMessage = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}

