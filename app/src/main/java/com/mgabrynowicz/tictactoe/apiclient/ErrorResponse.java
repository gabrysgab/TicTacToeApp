package com.mgabrynowicz.tictactoe.apiclient;

/**
 * Created by RENT on 2016-12-19.
 */

public class ErrorResponse {

    private final String message;


    public String getMessage() {
        return message;
    }

    public ErrorResponse(String message) {

        this.message = message;
    }


}
