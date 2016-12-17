package com.mgabrynowicz.tictactoe.User.service;

import android.util.Log;

import com.mgabrynowicz.tictactoe.User.model.LoginRequest;
import com.mgabrynowicz.tictactoe.User.model.LoginResponse;
import com.mgabrynowicz.tictactoe.User.model.RegisterRequest;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClient;
import com.mgabrynowicz.tictactoe.apiclient.TicTacToeApiClientFactory;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by RENT on 2016-12-17.
 */

public class UserService {

    private static UserService instance;
    private final TicTacToeApiClient ticTacToeApiClient;
    private String token;


    private UserService() {
        TicTacToeApiClientFactory ticTacToeApiClientFactory = new TicTacToeApiClientFactory();
        ticTacToeApiClient = ticTacToeApiClientFactory.createTicTacToeApiClient();
    }

    public static UserService getInstance() {

        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void register(RegisterRequest registerRequest) throws IOException {
        Response<LoginResponse> response = ticTacToeApiClient.register(registerRequest).execute();

        if (!response.isSuccessful()) {

            throw new RuntimeException("failed");
        }
        LoginResponse loginResponse = response.body();
        token = loginResponse.getData().getToken();
    }

    public void logout() {
        try {
            ticTacToeApiClient.logout(token).execute();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("LOGOUT_ERROR", "Logout error");
        }
        token = null;
    }

    public void login(LoginRequest loginRequest) throws IOException {

        Response<LoginResponse> response = ticTacToeApiClient.login(loginRequest).execute();

        if (!response.isSuccessful()) {

            throw new RuntimeException("failed");
        }
        LoginResponse loginResponse = response.body();
        token = loginResponse.getData().getToken();
    }

    public String getToken() {
        return token;
    }
}
