package com.mgabrynowicz.tictactoe.gamelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mgabrynowicz.tictactoe.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2016-12-17.
 */

public class GamelistViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.gameName)
    TextView gameName;

    @BindView(R.id.gameCreator)
    TextView gameCreator;

    @BindView(R.id.gamePlayer)
    TextView gamePlayer;

    public GamelistViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
