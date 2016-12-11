package com.mgabrynowicz.tictactoe;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by RENT on 2016-12-03.
 */

public class TicTacToe {

    private Player currentPlayer;
    private Player firstPlayer;
    private Player secondPlayer;
    private ComputerPlayer computerPlayer;
    private GameGrid gameGrid;
    private Boolean winner = null;
    private int[] winnerButtons;
    private boolean gameOver = false;
    private List<Integer> emptyGrids;

    private int[][] winStates = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
    };

    public TicTacToe() {
        this.firstPlayer = new Player(true);
        this.secondPlayer = new Player(false);


    }


    private void chooseStartingPlayer() {

        currentPlayer = firstPlayer;


    }

    private void changeTurn() {

        if (currentPlayer.equals(firstPlayer)) {

            setCurrentPlayer(secondPlayer);
        } else {
            setCurrentPlayer(firstPlayer);
        }
    }

    public void placeSignOnTheBoard(Button button, Context context) {

        int position = 0;


        String tag = button.getTag().toString();
        position = Integer.parseInt(tag);

        if (!isGameOver()) {

            if (!(this.gameGrid.getGameGrid()[position] == null)) {
                return;
            }

            this.gameGrid.getGameGrid()[position] = currentPlayer.getSign();
            this.emptyGrids.remove(this.emptyGrids.indexOf(position));
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


    public void placeSignOnBoardAI(Button[] gameGridArray, Context context) {
        int position = 0;

        if(checkForWinningMove() != null) {

            position = checkForWinningMove();
        }
        else {
            Random randomGenerator = new Random();


            if (emptyGrids.size() == 1) {

                position = emptyGrids.get(0);
            } else if (emptyGrids.isEmpty()) {
                //do nothing

            } else {
                int index = randomGenerator.nextInt(emptyGrids.size());
                position = emptyGrids.get(index);
            }
        }
        if (!isGameOver()) {

            if (!(this.gameGrid.getGameGrid()[position] == null)) {
                return;
            }

            this.gameGrid.getGameGrid()[position] = currentPlayer.getSign();
            setGridSign(gameGridArray[position]);
            this.emptyGrids.remove(this.emptyGrids.indexOf(position));
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


        for (int[] win : winStates) {

            if (gameGrid.getGameGrid()[win[0]] == null || gameGrid.getGameGrid()[win[1]] == null || gameGrid.getGameGrid()[win[2]] == null) {
                continue;
            }
            if (gameGrid.getGameGrid()[win[0]].equals(gameGrid.getGameGrid()[win[1]]) && gameGrid.getGameGrid()[win[1]].equals(gameGrid.getGameGrid()[win[2]])) {

                this.winner = gameGrid.getGameGrid()[win[0]];
                this.winnerButtons = new int[]{win[0], win[1], win[2]};
            }
        }
    }
    private Integer checkForWinningMove() {
        Integer winningMove = null;

        for(Integer tmpIndex : emptyGrids) {

            gameGrid.getGameGrid()[tmpIndex] = true;




            for (int[] win : winStates) {

                if (gameGrid.getGameGrid()[win[0]] == null || gameGrid.getGameGrid()[win[1]] == null || gameGrid.getGameGrid()[win[2]] == null) {
                    continue;
                }
                if (gameGrid.getGameGrid()[win[0]].equals(gameGrid.getGameGrid()[win[1]]) && gameGrid.getGameGrid()[win[1]].equals(gameGrid.getGameGrid()[win[2]])) {

                    winningMove = tmpIndex;
                    gameGrid.getGameGrid()[tmpIndex] = null;
                    return winningMove;

                }
            }
            gameGrid.getGameGrid()[tmpIndex] = null;
        }
        return winningMove;
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
        this.emptyGrids = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
