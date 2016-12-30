package com.mgabrynowicz.tictactoe.game.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.game.model.MakeMoveResult;
import com.mgabrynowicz.tictactoe.game.model.MoveRequest;
import com.mgabrynowicz.tictactoe.game.service.GameService;

import java.io.IOException;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class MakeMoveAsyncTask extends AsyncTask<MoveRequest, Void, MakeMoveResult> {
    private final MoveMadeListener moveMadeListener;
    private final GameService gameService;

    public MakeMoveAsyncTask(MoveMadeListener moveMadeListener) {
        this.moveMadeListener = moveMadeListener;
        gameService = new GameService();
    }

    @Override
    protected MakeMoveResult doInBackground(MoveRequest... moveRequests) {
        try {
            return gameService.makeMove(moveRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MakeMoveResult("failed");
    }

    @Override
    protected void onPostExecute(MakeMoveResult makeMoveResult) {
        if (makeMoveResult.getErrorMessage() == null) {
            moveMadeListener.moveMade();
        } else {
            moveMadeListener.failed(makeMoveResult.getErrorMessage());
        }
    }

    public interface MoveMadeListener {
        void moveMade();
        void failed(String error);
    }

}