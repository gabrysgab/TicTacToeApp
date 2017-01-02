package com.mgabrynowicz.tictactoe.gamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.GameActivity;
import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.gamelist.async.CreateGameAsyncTask;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameRequest;
import com.mgabrynowicz.tictactoe.gamelist.model.CreateGameResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class CreateGameActivity extends AppCompatActivity implements CreateGameAsyncTask.CreateGameListener {

    @BindView(R.id.gameName)
    EditText gameName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.newGameButton)
    public void newGame() {

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName.getText().toString());
        new CreateGameAsyncTask(this).execute(createGameRequest);





    }

    @Override
    public void onResult(CreateGameResult createGameResult) {
        if(createGameResult.getId() != null) {
            Intent intent = new Intent(CreateGameActivity.this, GameActivity.class);
            intent.putExtra("isOwner", true);
            intent.putExtra("gameId", createGameResult.getId());
            startActivity(intent);
            return;
        }
        Toast.makeText(this,createGameResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
}
