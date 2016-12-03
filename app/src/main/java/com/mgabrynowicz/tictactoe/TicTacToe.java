package com.mgabrynowicz.tictactoe;

import android.widget.Button;

import java.util.Random;

/**
 * Created by RENT on 2016-12-03.
 */

public class TicTacToe {

    private Player currentPlayer;
    private Player firstPlayer;
    private Player secondPlayer;
    private GameGrid gameGrid;

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

    public void placeSignOnTheBoard(int position, Button button) {

        if(!endOfGame()) {

            if (!(this.gameGrid.getGameGrid()[position] == null)) {
                return;
            }

            this.gameGrid.getGameGrid()[position] = currentPlayer.getSign();
            setGridSign(button);
            changeTurn();
        }

    }


    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private Boolean checkIfSomeoneWon() {

        Boolean winnerSign = null;
        if(gameGrid.getGameGrid()[0] != null || gameGrid.getGameGrid()[1] != null || gameGrid.getGameGrid()[2] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[1]) && gameGrid.getGameGrid()[1].equals(gameGrid.getGameGrid()[2])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[0]);

            }
        }
        if(gameGrid.getGameGrid()[3] != null || gameGrid.getGameGrid()[4] != null || gameGrid.getGameGrid()[5] != null) {
            if (gameGrid.getGameGrid()[3].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[5])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[3]);
            }
        }
        if(gameGrid.getGameGrid()[6] != null || gameGrid.getGameGrid()[7] != null || gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[6].equals(gameGrid.getGameGrid()[7]) && gameGrid.getGameGrid()[7].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[6]);

            }
        }

        if(gameGrid.getGameGrid()[0] != null || gameGrid.getGameGrid()[3] != null || gameGrid.getGameGrid()[6] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[3]) && gameGrid.getGameGrid()[3].equals(gameGrid.getGameGrid()[6])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[6]);

            }
        }

        if(gameGrid.getGameGrid()[1] != null || gameGrid.getGameGrid()[4] != null || gameGrid.getGameGrid()[7] != null) {
            if (gameGrid.getGameGrid()[1].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[7])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[1]);

            }
        }

        if(gameGrid.getGameGrid()[2] != null || gameGrid.getGameGrid()[5] != null || gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[2].equals(gameGrid.getGameGrid()[5]) && gameGrid.getGameGrid()[5].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[2]);

            }
        }

        if(gameGrid.getGameGrid()[0] != null || gameGrid.getGameGrid()[4] != null || gameGrid.getGameGrid()[8] != null) {
            if (gameGrid.getGameGrid()[0].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[8])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[0]);

            }
        }
        if(gameGrid.getGameGrid()[2] != null || gameGrid.getGameGrid()[4] != null || gameGrid.getGameGrid()[6] != null) {
            if (gameGrid.getGameGrid()[2].equals(gameGrid.getGameGrid()[4]) && gameGrid.getGameGrid()[4].equals(gameGrid.getGameGrid()[6])) {

                winnerSign = whoWhon(gameGrid.getGameGrid()[2]);

            }
        }

        return winnerSign;


    }

    private Boolean whoWhon(Boolean sign) {

        if (sign.equals(firstPlayer.getSign())) {

            return firstPlayer.getSign();
        } else {

            return secondPlayer.getSign();
        }

    }

    private boolean endOfGame() {



        Boolean winner = checkIfSomeoneWon();
        boolean boardFull = true;

        if(winner != null) {

            if (winner.equals(firstPlayer.getSign()) || winner.equals(secondPlayer.getSign())) {

                return true;
            }
        }
        for (Boolean tmpBoolean : gameGrid.getGameGrid()) {


            if (tmpBoolean == null) {

                boardFull = false;
            }
        }

        if (boardFull) {
            return true;
        } else {
            return false;
        }


    }

    public void newGame() {

        chooseStartingPlayer();
        this.gameGrid = new GameGrid();

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


}
