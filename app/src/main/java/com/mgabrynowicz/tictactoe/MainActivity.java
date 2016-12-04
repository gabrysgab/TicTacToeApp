package com.mgabrynowicz.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private Button gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8;
    private TicTacToe game = new TicTacToe();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gameGrid0 = (Button) findViewById(R.id.gameGrid0);
        gameGrid1 = (Button) findViewById(R.id.gameGrid1);
        gameGrid2 = (Button) findViewById(R.id.gameGrid2);
        gameGrid3 = (Button) findViewById(R.id.gameGrid3);
        gameGrid4 = (Button) findViewById(R.id.gameGrid4);
        gameGrid5 = (Button) findViewById(R.id.gameGrid5);
        gameGrid6 = (Button) findViewById(R.id.gameGrid6);
        gameGrid7 = (Button) findViewById(R.id.gameGrid7);
        gameGrid8 = (Button) findViewById(R.id.gameGrid8);

        game.newGame();


    }

    @OnClick({R.id.gameGrid0, R.id.gameGrid1, R.id.gameGrid2, R.id.gameGrid3, R.id.gameGrid4, R.id.gameGrid5, R.id.gameGrid6, R.id.gameGrid7, R.id.gameGrid8})

    public void onGridClick(View view) {

        String tag = view.getTag().toString();
        int gridNumber = Integer.parseInt(tag);
        game.placeSignOnTheBoard(gridNumber, (Button) view, this);


    }

    @OnClick(R.id.newGameButton)
    public void newGameClick(View view) {
        game.newGame();
        Button[] gameGridArray = new Button[]{gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8};
        for (Button tmpButton : gameGridArray) {

            tmpButton.setText("Button");
        }


    }
}
