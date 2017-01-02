package com.mgabrynowicz.tictactoe;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.game.async.GameRefresher;
import com.mgabrynowicz.tictactoe.game.async.LeaveGameAsyncTask;
import com.mgabrynowicz.tictactoe.game.async.MakeMoveAsyncTask;
import com.mgabrynowicz.tictactoe.game.model.MoveRequest;
import com.mgabrynowicz.tictactoe.gamelist.GamelistActivity;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;
import com.mgabrynowicz.tictactoe.gamelist.model.Move;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements LeaveGameAsyncTask.OnLeaveGameListener, GameRefresher.GameRefreshed, MakeMoveAsyncTask.MoveMadeListener {


    private Button gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8;
    private TicTacToe game = new TicTacToe();
    private List<Button> gameGridArray;
    private GameRefresher gameRefresher;
    private Game gameState;
    private Move moveToMake;
    private int myMoveToMake;
    private boolean isOwner = false;
    private boolean isPlayer = false;
    private boolean isSpectator = false;
    private String gameId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        isOwner = getIntent().getBooleanExtra("isOwner", false);
        isPlayer = getIntent().getBooleanExtra("isPlayer", false);
        isSpectator = getIntent().getBooleanExtra("isSpectator", false);
        gameId = getIntent().getStringExtra("gameId");

        if (gameId == null || gameId.isEmpty()) {
            finish();
            return;
        }

        gameGrid0 = (Button) findViewById(R.id.gameGrid0);
        gameGrid1 = (Button) findViewById(R.id.gameGrid1);
        gameGrid2 = (Button) findViewById(R.id.gameGrid2);
        gameGrid3 = (Button) findViewById(R.id.gameGrid3);
        gameGrid4 = (Button) findViewById(R.id.gameGrid4);
        gameGrid5 = (Button) findViewById(R.id.gameGrid5);
        gameGrid6 = (Button) findViewById(R.id.gameGrid6);
        gameGrid7 = (Button) findViewById(R.id.gameGrid7);
        gameGrid8 = (Button) findViewById(R.id.gameGrid8);
        gameGridArray = Arrays.asList(gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8);


        for (int i = 0; i < gameGridArray.size(); i++) {
            Button grid = gameGridArray.get(i);
            setMoveClickListener(grid, i);
        }

        game.newGame(isOwner);
    }


    private void setMoveClickListener(Button grid, final int i) {
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeMove(i);
            }
        });
    }


//    @OnClick(R.id.newGameButton)
//    public void newGameClick(View view) {
//        game.newGame(isOwner);
//
//        for (Button tmpButton : gameGridArray) {
//
//            tmpButton.setText("");
//            tmpButton.getBackground().clearColorFilter();
//        }
//
//
//    }

    @OnClick(R.id.leaveGameButton)
    public void leaveGameClick(View view) {

        new LeaveGameAsyncTask(this).execute();
    }

    public void higlightWinningButtons() {


        if (game.isGameOver()) {

            for (Button tmpButton : gameGridArray) {
                if (game.getWinnerButtons() != null) {
                    for (int winnerButtonTag : game.getWinnerButtons()) {
                        if (Integer.valueOf(tmpButton.getTag().toString()) == winnerButtonTag) {

                            tmpButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);


                        }
                    }
                }
            }
        }
    }

    @Override
    public void onGameLeft() {

        Intent intent = new Intent(GameActivity.this, GamelistActivity.class);
        startActivity(intent);

    }

    @Override
    public void failed() {

        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void gameRefreshed(Game game) {
        if (game == null) {
            Toast.makeText(this, "other player left game", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        gameState = game;
        printGameData(game, this.game);
    }

    private void printGameData(Game game, TicTacToe gameBoard) {

        //Todo implement this function to print move on the board

        for (Move move : game.getMovesOwner()) {
            gameBoard.placeSignOnTheBoard(gameGridArray.get(move.toBoardFieldNumber()-1),this, true);

        }
        for (Move move : game.getMovesPlayer()) {
            gameBoard.placeSignOnTheBoard(gameGridArray.get(move.toBoardFieldNumber()-1),this, false);
        }
        higlightWinningButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameRefresher = new GameRefresher();
        gameRefresher.startRefreshing(this, gameId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameRefresher.close();
    }


    private void makeMove(int i) {
        if (isSpectator) {
            return;
        }

        int x = i % 3;
        int y = (i - x) / 3;
        moveToMake = new Move(x, y);
        myMoveToMake = i;

        MoveRequest moveRequest = new MoveRequest(x, y, gameId);

        new MakeMoveAsyncTask(this).execute(moveRequest);

    }

    private void printSingleMove(int i) {

        game.placeSignOnTheBoard(gameGridArray.get(i), this, isOwner);

    }

    @Override
    public void moveMade() {

        if (gameGridArray.get(myMoveToMake).getText().length() == 0) {
            printSingleMove(myMoveToMake);
        }
        higlightWinningButtons();
        ObjectAnimator anim = ObjectAnimator.ofFloat(gameGridArray.get(myMoveToMake), "rotationY", 180f, 0f);
        anim.start();


    }

    @Override
    public void failed(String error) {
        Toast.makeText(this, "failed: " + error, Toast.LENGTH_LONG).show();

    }


}
