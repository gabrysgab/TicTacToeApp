package com.mgabrynowicz.tictactoe.gamelist.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.game.model.JoinGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.service.GameListService;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class JoinGameAsyncTask extends AsyncTask<JoinGameRequest, Void, Boolean> {

    private final GameListService gameListService;
    private final JoinGameListener joinGameListener;

    public JoinGameAsyncTask(JoinGameListener joinGameListener) {
        this.joinGameListener = joinGameListener;
        gameListService = new GameListService();

    }


    @Override
    protected Boolean doInBackground(JoinGameRequest... joinGameRequests) {
        try {
            gameListService.joinGame(joinGameRequests[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            joinGameListener.joinedGame();
        } else {
            joinGameListener.failed();
        }
    }


    public interface JoinGameListener {
        void joinedGame();

        void failed();
    }
}
