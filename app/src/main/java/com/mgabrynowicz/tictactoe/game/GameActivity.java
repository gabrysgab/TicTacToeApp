package com.mgabrynowicz.tictactoe.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.game.async.LeaveGameAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.GamelistActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-19.
 */

public class GameActivity extends AppCompatActivity implements LeaveGameAsyncTask.OnLeaveGameListener {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.leave_button)
    public void leaveGame() {

        new LeaveGameAsyncTask(this).execute();

    }

    @Override
    public void onGameLeft() {

        startActivity(new Intent(this, GamelistActivity.class));

    }

    @Override
    public void failed() {

        Toast.makeText(this, "Failed to left game", Toast.LENGTH_SHORT).show();
    }

}
