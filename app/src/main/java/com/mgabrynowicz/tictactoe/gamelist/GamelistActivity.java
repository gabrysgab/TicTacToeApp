package com.mgabrynowicz.tictactoe.gamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.User.activity.LoginActivity;
import com.mgabrynowicz.tictactoe.User.async.LogoutAsyncTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class GamelistActivity extends AppCompatActivity implements LogoutAsyncTask.onLogoutListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.logout_button)
    public void logout() {

        new LogoutAsyncTask(this).execute();
    }


    @Override
    public void onLogoutCompleted() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
