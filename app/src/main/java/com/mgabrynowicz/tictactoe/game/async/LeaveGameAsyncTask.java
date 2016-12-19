package com.mgabrynowicz.tictactoe.game.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.game.service.GameService;

/**
 * Created by RENT on 2016-12-19.
 */

public class LeaveGameAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private final OnLeaveGameListener onLeaveGameListener;
    private final GameService gameService;

    public LeaveGameAsyncTask(OnLeaveGameListener onLeaveGameListener) {
        this.onLeaveGameListener = onLeaveGameListener;
        gameService = new GameService();
    }


    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            gameService.leaveGame();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean gameLeft) {
        if(gameLeft) {
            onLeaveGameListener.onGameLeft();
            return;
        }
        onLeaveGameListener.failed();
    }

    public interface OnLeaveGameListener {

        void onGameLeft();
        void failed();


    }
}
