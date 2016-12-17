package com.mgabrynowicz.tictactoe.gamelist.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResult;
import com.mgabrynowicz.tictactoe.gamelist.service.GameListService;

/**
 * Created by RENT on 2016-12-17.
 */

public class CreateGameAsyncTask extends AsyncTask<CreateGameRequest, Void, CreateGameResult> {

    private final GameListService gameListService;
    private final CreateGameListener createGameListener;

    public CreateGameAsyncTask(CreateGameListener createGameListener) {
        this.createGameListener = createGameListener;
        gameListService = new GameListService();

    }

    @Override
    protected CreateGameResult doInBackground(CreateGameRequest... createGameRequests) {

        try {
            return gameListService.createGame(createGameRequests[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CreateGameResult(null, "Creating game failed");
    }

    @Override
    protected void onPostExecute(CreateGameResult createGameResult) {
        createGameListener.onResult(createGameResult);
    }

    public interface CreateGameListener {

        void onResult(CreateGameResult createGameResult);

    }
}
