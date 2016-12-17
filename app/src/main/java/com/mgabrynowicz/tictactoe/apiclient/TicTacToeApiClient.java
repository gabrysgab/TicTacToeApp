package com.mgabrynowicz.tictactoe.apiclient;

import com.mgabrynowicz.tictactoe.User.model.LoginRequest;
import com.mgabrynowicz.tictactoe.User.model.LoginResponse;
import com.mgabrynowicz.tictactoe.User.model.RegisterRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResponse;
import com.mgabrynowicz.tictactoe.gamelist.model.GameResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by RENT on 2016-12-17.
 */

public interface TicTacToeApiClient {

    @POST("/user")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890",
            "Content-Type: application/json"
    })
    Call<LoginResponse> register(@Body RegisterRequest registerRequest);

    @POST("/logout")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890"})

    Call<ResponseBody> logout(@Header("X-BB-SESSION") String token);

    @POST("/login")
    @Headers({
            "X-BAASBOX-APPCODE: 1234567890",
            "Content-Type: application/json"
    })
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/plugin/ttt.game")
    Call<GameResponse> getGames(@Header("X-BB-SESSION") String token);


    @POST("/plugin/ttt.game")
    @Headers("Content-Type: application/json")
    Call<CreateGameResponse> createGame(@Header("X-BB-Session") String token, @Body CreateGameRequest createGameRequest);




}
