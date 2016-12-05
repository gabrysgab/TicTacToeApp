package com.mgabrynowicz.tictactoe;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by RENT on 2016-12-03.
 */

public class TicTacToe {

    private Player currentPlayer;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameGrid gameGrid;
    private Boolean winner = null;
    private int[] winnerButtons;
    private boolean gameOver = false;

    public TicTacToe() {
        this.firstPlayer = new Player(true);
        this.secondPlayer = new Player(false);

    }


    private void chooseStartingPlayer() {
        Random random = new Random();
        int randomPlayer = random.nextInt(1);
        if (randomPlayer == 0) {
            currentPlayer = firstPlayer;
        } else {
            currentPlayer = secondPlayer;
        }


    }

    private void changeTurn() {

        if (currentPlayer.equals(firstPlayer)) {

            setCurrentPlayer(secondPlayer);
        } else {
            setCurrentPlayer(firstPlayer);
        }
    }

    public void placeSignOnTheBoard(Button button, Context context) {

        String tag = button.getTag().toString();
        int position = Integer.parseInt(tag);



        if (!isGameOver()) {

            if (!(this.gameGrid.getGameGrid()[position] == null)) {
                return;
            }

            this.gameGrid.getGameGrid()[position] = currentPlayer.getSign();
            setGridSign(button);
            endOfGame();
            changeTurn();
        }
        if (isGameOver()) {
            if (this.winner == null) {

                Toast.makeText(context, "Draw", Toast.LENGTH_SHORT).show();
            } else {
                announceWinner(this.winner, context);

            }

        }


    }


    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private void checkIfSomeoneWon() {

        Boolean winnerSign = null;
        if (gameGrid.getGameGrid()[0] != null && gameGrid.getGameGrid()[1] != null && gameGrid.getGameGrid()[2] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[1]) && gameGrid.getGameGrid()[1].equals(gameGrid.getGameGrid()[2])) {

                winnerSign = gameGrid.getGameGrid()[0];
                this.winnerButtons = new int[]{0, 1, 2};

            }
        }
        if (gameGrid.getGameGrid()[3] != null && gameGrid.getGameGrid()[4] != null && gameGrid.getGameGrid()[5] != null) {
            if (gameGrid.getGameGrid()[3].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[5])) {

                winnerSign = gameGrid.getGameGrid()[3];
                this.winnerButtons = new int[]{3, 4, 5};
            }
        }
        if (gameGrid.getGameGrid()[6] != null && gameGrid.getGameGrid()[7] != null && gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[6].equals(gameGrid.getGameGrid()[7]) && gameGrid.getGameGrid()[7].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = gameGrid.getGameGrid()[6];
                this.winnerButtons = new int[]{6, 7, 8};

            }
        }

        if (gameGrid.getGameGrid()[0] != null && gameGrid.getGameGrid()[3] != null && gameGrid.getGameGrid()[6] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[3]) && gameGrid.getGameGrid()[3].equals(gameGrid.getGameGrid()[6])) {

                winnerSign = gameGrid.getGameGrid()[6];
                this.winnerButtons = new int[]{0, 3, 6};

            }
        }

        if (gameGrid.getGameGrid()[1] != null && gameGrid.getGameGrid()[4] != null && gameGrid.getGameGrid()[7] != null) {
            if (gameGrid.getGameGrid()[1].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[7])) {

                winnerSign = gameGrid.getGameGrid()[1];
                this.winnerButtons = new int[]{1, 4, 7};

            }
        }

        if (gameGrid.getGameGrid()[2] != null && gameGrid.getGameGrid()[5] != null && gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[2].equals(gameGrid.getGameGrid()[5]) && gameGrid.getGameGrid()[5].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = gameGrid.getGameGrid()[2];
                this.winnerButtons = new int[]{2, 5, 8};

            }
        }

        if (gameGrid.getGameGrid()[0] != null && gameGrid.getGameGrid()[4] != null && gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = gameGrid.getGameGrid()[0];
                this.winnerButtons = new int[]{0, 4, 8};

            }
        }
        if (gameGrid.getGameGrid()[2] != null && gameGrid.getGameGrid()[4] != null && gameGrid.getGameGrid()[6] != null) {
            if (gameGrid.getGameGrid()[2].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[6])) {

                winnerSign = gameGrid.getGameGrid()[2];
                this.winnerButtons = new int[]{2, 4, 6};

            }
        }

        this.winner = winnerSign;


    }

    private void announceWinner(Boolean sign, Context context) {

        if (sign.equals(Boolean.TRUE)) {


            Toast.makeText(context, "O Won", Toast.LENGTH_SHORT).show();


        } else {

            Toast.makeText(context, "X Won", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean endOfGame() {

        boolean boardFull = true;
        checkIfSomeoneWon();

        if (this.winner != null) {
            return this.gameOver = true;


        }
        for (Boolean tmpBoolean : gameGrid.getGameGrid()) {


            if (tmpBoolean == null) {

                boardFull = false;
            }
        }

        if (boardFull) {
            return this.gameOver = true;
        }

        return this.gameOver;


    }

    public void newGame() {

        chooseStartingPlayer();
        this.gameGrid = new GameGrid();
        this.winner = null;
        this.winnerButtons = null;
        this.gameOver = false;

    }

    private void setGridSign(Button button) {
        String sign = "";

        if (currentPlayer.getSign().equals(true)) {
            sign = "O";
        } else {
            sign = "X";
        }

        button.setText(sign);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int[] getWinnerButtons() {
        return winnerButtons;


    }
}
