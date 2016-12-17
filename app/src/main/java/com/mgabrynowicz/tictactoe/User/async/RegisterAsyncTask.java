package com.mgabrynowicz.tictactoe.User.async;

import android.os.AsyncTask;

import com.mgabrynowicz.tictactoe.User.model.RegisterRequest;
import com.mgabrynowicz.tictactoe.User.service.UserService;

/**
 * Created by RENT on 2016-12-17.
 */

public class RegisterAsyncTask extends AsyncTask<RegisterRequest, Void, Boolean> {

    private final UserService userService;
    private final OnRegisterListener onRegisterListener;

    public RegisterAsyncTask(OnRegisterListener onRegisterListener) {

        userService = UserService.getInstance();
        this.onRegisterListener = onRegisterListener;
    }

    @Override
    protected Boolean doInBackground(RegisterRequest... registerRequests) {

        try {
            userService.register(registerRequests[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            onRegisterListener.completed();
        } else {
            onRegisterListener.failed();
        }
    }

    public interface OnRegisterListener {

        void completed();
        void failed();


    }
}
