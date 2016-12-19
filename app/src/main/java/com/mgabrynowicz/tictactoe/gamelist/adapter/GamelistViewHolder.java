package com.mgabrynowicz.tictactoe.gamelist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;

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

    private final GamelistAdapter.ItemClickListener itemClickListener;

    private Game game;

    public GamelistViewHolder(View itemView, final GamelistAdapter.ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClicked(game);
            }
        });
    }

    public void setGame(Game game){this.game = game;}
}
