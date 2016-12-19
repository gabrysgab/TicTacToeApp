package com.mgabrynowicz.tictactoe.User.async;

import android.os.Handler;
import android.os.Looper;

import com.mgabrynowicz.tictactoe.gamelist.async.DownloadGamelistAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.service.GameListService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by RENT on 2016-12-19.
 */

public class GamelistRefresher {

    private final ScheduledExecutorService executorService;
    private final GameListService gameListService;

    public GamelistRefresher() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        gameListService = new GameListService();
    }

    public void startRefreshing(final DownloadGamelistAsyncTask.OnGamelistDownloadListener gamelistDownloadListener) {

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                downloadGames(gamelistDownloadListener);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private void downloadGames(DownloadGamelistAsyncTask.OnGamelistDownloadListener gamelistDownloadListener) {
    //odpowiednik doInBackground
        try {
            List<Game> games = gameListService.getGames();
            postGamesDownloaded(games, gamelistDownloadListener);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void postGamesDownloaded(final List<Game> games, final DownloadGamelistAsyncTask.OnGamelistDownloadListener gamelistDownloadListener) {
        //odpowiednik onPostExecute

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                gamelistDownloadListener.gamesDownloaded(games);

            }
        });
    }

    public void close() {

        executorService.shutdown();

    }

}
