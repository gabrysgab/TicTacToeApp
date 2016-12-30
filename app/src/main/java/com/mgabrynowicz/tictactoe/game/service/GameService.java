package com.mgabrynowicz.tictactoe.game.service;

import com.google.gson.Gson;
import com.mgabrynowicz.tictactoe.User.service.UserService;
import com.mgabrynowicz.tictactoe.apiclient.ErrorResponse;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClient;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClientFactory;
import com.mgabrynowicz.tictactoe.game.model.MakeMoveResult;
import com.mgabrynowicz.tictactoe.game.model.MoveRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.model.GameResponse;

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

    public Game getGameData(String gameId) throws IOException {
        Response<GameResponse> response = ticTacToeApiClient.getGameData(userService.getToken(), gameId).execute();

        if (!response.isSuccessful()) {

            throw new RuntimeException();
        }

        return response.body().getData().get(0);
    }

    public MakeMoveResult makeMove(MoveRequest moveRequest) throws IOException {
        Response<ResponseBody> response = ticTacToeApiClient.makeMove(moveRequest, userService.getToken()).execute();

        if(!response.isSuccessful()) {
            String errorResponseBody = response.errorBody().string();
            ErrorResponse errorResponse = new Gson().fromJson(errorResponseBody, ErrorResponse.class);
            return new MakeMoveResult(errorResponse.getMessage());
        }

        return new MakeMoveResult();

    }


}

