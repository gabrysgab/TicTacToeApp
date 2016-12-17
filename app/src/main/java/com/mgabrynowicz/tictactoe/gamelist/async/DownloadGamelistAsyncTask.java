package com.mgabrynowicz.tictactoe.gamelist.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.service.GameListService;

import java.util.List;

/**
 * Created by RENT on 2016-12-17.
 */

public class DownloadGamelistAsyncTask extends AsyncTask<Void, Void, List<Game>> {

    private final GameListService gameListService;
    private final OnGamelistDownloadListener onGamelistDownloadListener;

    public DownloadGamelistAsyncTask(OnGamelistDownloadListener onGamelistDownloadListener) {
        this.onGamelistDownloadListener = onGamelistDownloadListener;
        gameListService = new GameListService();

    }


    @Override
    protected List<Game> doInBackground(Void... voids) {
        try{

            return gameListService.getGames();


        }catch(Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        onGamelistDownloadListener.gamesDownloaded(games);
    }

    public interface OnGamelistDownloadListener {

        void gamesDownloaded(List<Game> games);
    }
}
