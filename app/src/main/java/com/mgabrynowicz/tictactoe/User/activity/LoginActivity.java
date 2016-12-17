package com.mgabrynowicz.tictactoe.User.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.User.async.LoginAsyncTask;
import com.mgabrynowicz.tictactoe.User.model.LoginRequest;
import com.mgabrynowicz.tictactoe.gamelist.GamelistActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class LoginActivity extends AppCompatActivity implements LoginAsyncTask.OnLoginListener {

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.register_button)
    public void openRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));

    }

    @OnClick(R.id.login_button)
    public void login() {
        String login = this.login.getText().toString();
        String password = this.password.getText().toString();
        LoginRequest loginRequest = new LoginRequest(login, password);
        new LoginAsyncTask(this).execute(loginRequest);
    }

    @Override
    public void onLoginCompleted() {

        startActivity(new Intent(this, GamelistActivity.class));

    }

    @Override
    public void failed() {

        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();

    }
}
