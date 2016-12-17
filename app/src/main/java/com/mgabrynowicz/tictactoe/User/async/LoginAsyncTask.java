package com.mgabrynowicz.tictactoe.User.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.User.model.LoginRequest;
import com.mgabrynowicz.tictactoe.User.service.UserService;

/**
 * Created by RENT on 2016-12-17.
 */

public class LoginAsyncTask extends AsyncTask<LoginRequest, Void, Boolean> {

    private final OnLoginListener onLoginListener;
    private final UserService userService;

    public LoginAsyncTask(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
        userService = UserService.getInstance();
    }

    @Override
    protected Boolean doInBackground(LoginRequest... loginRequests) {

        try{
            userService.login(loginRequests[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            onLoginListener.onLoginCompleted();
        } else {
            onLoginListener.failed();
        }
    }

    public interface OnLoginListener {

        void onLoginCompleted();
        void failed();

    }
}
