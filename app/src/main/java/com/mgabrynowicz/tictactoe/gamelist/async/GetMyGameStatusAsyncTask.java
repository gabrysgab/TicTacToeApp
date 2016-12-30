package com.mgabrynowicz.tictactoe.gamelist.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.gamelist.model.GameStatus;
import com.mgabrynowicz.tictactoe.gamelist.service.GameListService;

import java.io.IOException;

/**
 * Created by Mateusz on 2016-12-30.
 */

public class GetMyGameStatusAsyncTask extends AsyncTask<Void, Void, GameStatus> {

    private final GameListService gameListService;
    private final GameStatusDownloadedListener gameStatusDownloadedListener;

    public GetMyGameStatusAsyncTask(GameStatusDownloadedListener gameStatusDownloadedListener) {
        this.gameStatusDownloadedListener = gameStatusDownloadedListener;
        gameListService = new GameListService();

    }

    @Override
    protected GameStatus doInBackground(Void... params) {
        try {
            return gameListService.getMyGameStatus();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(GameStatus gameStatus) {

        this.gameStatusDownloadedListener.gameStatusDownloaded(gameStatus);


    }


    public interface GameStatusDownloadedListener {
        void gameStatusDownloaded(GameStatus gameStatus);



    }
}
