package com.mgabrynowicz.tictactoe.User.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.User.async.RegisterAsyncTask;
import com.mgabrynowicz.tictactoe.User.model.RegisterRequest;
import com.mgabrynowicz.tictactoe.gamelist.GamelistActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterAsyncTask.OnRegisterListener {


    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.password_repeat)
    EditText passwordRepeat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.register_button)
    public void register() {

        String login = this.login.getText().toString();
        String password = this.password.getText().toString();
        String passwordRepeat = this.passwordRepeat.getText().toString();

        if(!password.equals(passwordRepeat)) {

            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest registerRequest = new RegisterRequest(login, password);

        new RegisterAsyncTask(this).execute(registerRequest);



    }


    @Override
    public void completed() {

        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, GamelistActivity.class));

    }

    @Override
    public void failed() {

        Toast.makeText(this, "FAILED", Toast.LENGTH_SHORT).show();

    }
}
