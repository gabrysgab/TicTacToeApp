package com.mgabrynowicz.tictactoe.gamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.User.activity.LoginActivity;
import com.mgabrynowicz.tictactoe.User.async.GamelistRefresher;
import com.mgabrynowicz.tictactoe.User.async.LogoutAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.adapter.GamelistAdapter;
import com.mgabrynowicz.tictactoe.gamelist.async.DownloadGamelistAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class GamelistActivity extends AppCompatActivity implements LogoutAsyncTask.onLogoutListener, DownloadGamelistAsyncTask.OnGamelistDownloadListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private GamelistRefresher gamelistRefresher;


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

    @OnClick(R.id.new_game_button)
    public void newGame() {

        startActivity(new Intent(this, CreateGameActivity.class));
    }



    @Override
    public void onLogoutCompleted() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void gamesDownloaded(List<Game> games) {

        GamelistAdapter gamelistAdapter = new GamelistAdapter(this,games);
        recyclerView.setAdapter(gamelistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        gamelistRefresher = new GamelistRefresher();
        gamelistRefresher.startRefreshing(this);
        }


    @Override
    protected void onPause() {
        super.onPause();
        gamelistRefresher.close();

    }
}
