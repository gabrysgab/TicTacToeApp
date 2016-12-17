package com.mgabrynowicz.tictactoe.gamelist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mgabrynowicz.tictactoe.R;
import com.mgabrynowicz.tictactoe.gamelist.model.Game;

import java.util.List;

/**
 * Created by RENT on 2016-12-17.
 */

public class GamelistAdapter extends RecyclerView.Adapter<GamelistViewHolder> {

    private final Context context;
    private final List<Game> games;
    private final LayoutInflater layoutInflater;

    public GamelistAdapter(Context context, List<Game> games) {
        this.context = context;
        this.games = games;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public GamelistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.view_game_item, parent, false);
        return new GamelistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GamelistViewHolder holder, int position) {

        Game game = games.get(position);
        holder.gameCreator.setText(game.getCreator());
        holder.gameName.setText(game.getName());
        holder.gamePlayer.setText(game.getPlayer());

    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
