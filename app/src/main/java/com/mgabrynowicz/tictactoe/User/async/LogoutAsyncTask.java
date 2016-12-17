package com.mgabrynowicz.tictactoe.User.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.User.service.UserService;

/**
 * Created by RENT on 2016-12-17.
 */

public class LogoutAsyncTask extends AsyncTask<Void, Void,Void  > {

    private final onLogoutListener onLogoutListener;
    private final UserService userService;

    public LogoutAsyncTask(onLogoutListener logoutListener) {
        this.onLogoutListener = logoutListener;
        this.userService = UserService.getInstance();

    }

    @Override
    protected Void doInBackground(Void... voids) {

        userService.logout();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.onLogoutListener.onLogoutCompleted();
    }

    public interface onLogoutListener {
        void onLogoutCompleted();
    }
}
