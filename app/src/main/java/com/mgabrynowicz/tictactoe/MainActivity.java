package com.mgabrynowicz.tictactoe;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private Button gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8;
    private TicTacToe game = new TicTacToe();
    private Button[] gameGridArray;
    private AnimatorSet set;


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
        gameGridArray = new Button[]{gameGrid0, gameGrid1, gameGrid2, gameGrid3, gameGrid4, gameGrid5, gameGrid6, gameGrid7, gameGrid8};





        game.newGame();


    }

    @OnClick({R.id.gameGrid0, R.id.gameGrid1, R.id.gameGrid2, R.id.gameGrid3, R.id.gameGrid4, R.id.gameGrid5, R.id.gameGrid6, R.id.gameGrid7, R.id.gameGrid8})

    public void onGridClick(View view) {


//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotating_around_central_point);
//        view.startAnimation(animation);
        Button clickedButton = (Button) view;


        if(clickedButton.getText().length() == 0) {
            game.placeSignOnTheBoard((Button) view, this);
            game.placeSignOnBoardAI(gameGridArray, this);
        }
        higlightWinningButtons();

//        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(200);
// włączenie wibracji na 1 sekundę



        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotationY", 180f, 0f );
        anim.start();


    }

    @OnClick(R.id.newGameButton)
    public void newGameClick(View view) {
        game.newGame();

        for (Button tmpButton : gameGridArray) {

            tmpButton.setText("");
            tmpButton.getBackground().clearColorFilter();
        }


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
}
