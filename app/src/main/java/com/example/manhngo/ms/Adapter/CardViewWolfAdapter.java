package com.example.manhngo.ms.Adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.Util.Key;
import com.example.manhngo.ms.models.Player;

import java.util.List;
import java.util.Objects;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class CardViewWolfAdapter extends
        RecyclerView.Adapter<CardViewWolfAdapter.ViewHolder> {
    private static final String TAG = CardViewWolfAdapter.class.getSimpleName();
    private final List<Player> players;

    public CardViewWolfAdapter(List<Player> players) {
        this.players = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_wolf_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        final int pos = position;

        holder.tvName.setText(players.get(position).getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(players.get(position).getFunction().toString(), Key.WOLF)) {
                holder.chkWolf.setChecked(true);
            }
        }

        holder.chkWolf.setTag(players.get(position));


        holder.chkWolf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    players.get(pos).setFunction(Function.WOLF);
                } else {
                    players.get(pos).setFunction(Function.NOTHING);
                }
            }
        });
    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return players.size();
    }

    // method to access in activity after updating selection
    public List<Player> getPlayersList() {
        return players;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CheckBox chkWolf;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvName = itemLayoutView.findViewById(R.id.tv_name);
            chkWolf = itemLayoutView.findViewById(R.id.chk_wolf);
        }
    }

}