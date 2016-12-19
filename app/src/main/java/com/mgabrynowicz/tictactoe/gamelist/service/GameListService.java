package com.mgabrynowicz.tictactoe.gamelist.service;

import com.google.gson.Gson;
import com.mgabrynowicz.tictactoe.User.service.UserService;
import com.mgabrynowicz.tictactoe.apiclient.ErrorResponse;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClient;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClientFactory;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResponse;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResult;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.model.GameResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by RENT on 2016-12-17.
 */

public class GameListService {

    private final TicTacToeApiClient ticTacToeApiClient;
    private final UserService userService;

    public GameListService() {
        TicTacToeApiClientFactory ticTacToeApiClientFactory = new TicTacToeApiClientFactory();
        ticTacToeApiClient = ticTacToeApiClientFactory.createTicTacToeApiClient();
        userService = UserService.getInstance();
    }


    public List<Game> getGames() throws IOException {
        Response<GameResponse> response = ticTacToeApiClient.getGames(userService.getToken()).execute();
        List<Game> games = response.body().getData();
        return games;
    }


    public CreateGameResult createGame(CreateGameRequest createGameRequest) throws IOException {
        Response<CreateGameResponse> response = ticTacToeApiClient.createGame(userService.getToken(), createGameRequest).execute();
        String id = response.body().getId();


        if (!response.isSuccessful()) {

            String responseBodyString = response.errorBody().string();
            ErrorResponse errorResponse = new Gson().fromJson(responseBodyString, ErrorResponse.class);

            return new CreateGameResult(null, errorResponse.getMessage());

        }
        return new CreateGameResult(id, null);
    }

}
