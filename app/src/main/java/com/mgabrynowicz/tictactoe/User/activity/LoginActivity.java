package com.mgabrynowicz.tictactoe.User.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mgabrynowicz.tictactoe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RENT on 2016-12-17.
 */

public class LoginActivity extends AppCompatActivity {


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
}
