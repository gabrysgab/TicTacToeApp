package com.mgabrynowicz.tictactoe.gamelist.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RENT on 2016-12-17.
 */

public class CreateGameResponse {

    @SerializedName("data")
    private String id;


    public CreateGameResponse(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }
}
