package com.mgabrynowicz.tictactoe.apiclient;

import com.mgabrynowicz.tictactoe.User.model.LoginRequest;
import com.mgabrynowicz.tictactoe.User.model.LoginResponse;
import com.mgabrynowicz.tictactoe.User.model.RegisterRequest;
import com.mgabrynowicz.tictactoe.game.model.JoinGameRequest;
import com.mgabrynowicz.tictactoe.game.model.MoveRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResponse;
import com.mgabrynowicz.tictactoe.gamelist.model.GameResponse;
import com.mgabrynowicz.tictactoe.gamelist.model.GameStatusResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @DELETE("/plugin/ttt.leaveGame")
    Call<ResponseBody> leaveGame(@Header("X-BB-Session") String token );

    @POST("plugin/ttt.joinGame")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> joinGame(@Header("X-BB-Session") String token, @Body JoinGameRequest joinGameRequest);

    @GET("/plugin/ttt.myGame")
    Call<GameStatusResponse> getMyGameStatus(@Header("X-BB-Session") String token);


    @GET("/plugin/ttt.gameData")
    Call<GameResponse> getGameData(@Header("X-BB-Session") String token, @Query("gameId") String gameId);


    @POST("/plugin/ttt.makeMove")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> makeMove(@Body MoveRequest moveRequest, @Header("X-BB-SESSION") String token);








}
