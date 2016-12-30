package com.mgabrynowicz.tictactoe.gamelist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.GameActivity;
import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.User.activity.LoginActivity;
import com.mgabrynowicz.tictactoe.User.async.GamelistRefresher;
import com.mgabrynowicz.tictactoe.User.async.LogoutAsyncTask;
import com.mgabrynowicz.tictactoe.game.model.JoinGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.adapter.GamelistAdapter;
import com.mgabrynowicz.tictactoe.gamelist.async.DownloadGamelistAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.async.GetMyGameStatusAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.async.JoinGameAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.model.GameStatus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class GamelistActivity extends AppCompatActivity implements LogoutAsyncTask.onLogoutListener, DownloadGamelistAsyncTask.OnGamelistDownloadListener, GamelistAdapter.ItemClickListener, JoinGameAsyncTask.JoinGameListener, GetMyGameStatusAsyncTask.GameStatusDownloadedListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private GamelistRefresher gamelistRefresher;
    private Game gameToJoin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        ButterKnife.bind(this);

        checkBeingInsideGameAlready();

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

        GamelistAdapter gamelistAdapter = new GamelistAdapter(this,games, this);
        recyclerView.setAdapter(gamelistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        gamelistRefresher = new GamelistRefresher();
        gamelistRefresher.startRefreshing(this);
        checkBeingInsideGameAlready();
        }


    @Override
    protected void onPause() {
        super.onPause();
        gamelistRefresher.close();

    }

    @Override
    public void itemClicked(final Game game) {

        new AlertDialog.Builder(this)
                .setMessage("Join game as player or spectator?")
                .setPositiveButton("Player", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        joinGame(game);

                    }
                })
                .setNegativeButton("Spectator", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(GamelistActivity.this, GameActivity.class);
                        intent.putExtra("isSpectator", true);
                        intent.putExtra("gameId", game.getId());
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void joinGame(Game game) {
        gameToJoin = game;
        JoinGameRequest joinGameRequest = new JoinGameRequest(game.getName());

        new JoinGameAsyncTask(this).execute(joinGameRequest);

    }


    @Override
    public void joinedGame() {
        Intent intent = new Intent(GamelistActivity.this, GameActivity.class);
        intent.putExtra("isPlayer", true);
        intent.putExtra("gameId", gameToJoin.getId());
        startActivity(intent);

        gameToJoin = null;

    }

    @Override
    public void failed() {
        Toast.makeText(this, "failed to join game!", Toast.LENGTH_SHORT).show();
    }


    private void checkBeingInsideGameAlready() {
        new GetMyGameStatusAsyncTask(this).execute();
    }

    @Override
    public void gameStatusDownloaded(GameStatus gameStatus) {
        if(gameStatus == null || gameStatus.getGameId() == null || gameStatus.getGameId().isEmpty()) {
            return;
        }

        //we are inside game already - > start GameActivity
        Intent intent = new Intent(GamelistActivity.this, GameActivity.class);

        if("OWNER".equals(gameStatus.getType())) {
            intent.putExtra("isOwner", true);
        }else {
            intent.putExtra("isPlayer", true);
        }
        intent.putExtra("gameId", gameStatus.getGameId());

        startActivity(intent);

        Toast.makeText(this, "Already in game! Game: " +gameStatus.getName() + " as " + gameStatus.getType(), Toast.LENGTH_SHORT).show();



    }
}
