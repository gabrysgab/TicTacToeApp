package com.mgabrynowicz.tictactoe.game.service;

import com.mgabrynowicz.tictactoe.User.service.UserService;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClient;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClientFactory;
import com.mgabrynowicz.tictactoe.game.model.JoinGameRequest;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by RENT on 2016-12-19.
 */

public class GameService {

    private final TicTacToeApiClient ticTacToeApiClient;
    private final UserService userService;

    public GameService() {
        TicTacToeApiClientFactory ticTacToeApiClientFactory = new TicTacToeApiClientFactory();
        ticTacToeApiClient = ticTacToeApiClientFactory.createTicTacToeApiClient();
        userService = UserService.getInstance();
    }

    public void leaveGame() throws IOException {

        Response<ResponseBody> response = ticTacToeApiClient.leaveGame(userService.getToken()).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException();
        }
    }

    public void joinGame(JoinGameRequest joinGameRequest) throws IOException {

        Response<ResponseBody> response = ticTacToeApiClient.joinGame(userService.getToken(), joinGameRequest).execute();
        if(!response.isSuccessful()) {
            throw new RuntimeException();
        }


    }

}

