package com.example.manhngo.ms.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.models.Player;

import java.util.List;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class CardViewDataAdapter extends
        RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

    private final List<Player> players;

    public CardViewDataAdapter(List<Player> students) {
        this.players = students;

    }

    // Create new views
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.choose_wolf_item, null);

        // create ViewHolder

        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.tvName.setText(players.get(position).getName());

        viewHolder.chkSelected.setChecked(players.get(position).isSelected());

        viewHolder.chkSelected.setTag(players.get(position));


        viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Player contact = (Player) cb.getTag();

                contact.setSelected(cb.isChecked());
                players.get(pos).setSelected(cb.isChecked());

                Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + cb.getText() + " is "
                                + cb.isChecked(), Toast.LENGTH_LONG).show();
            }
        });

    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return players.size();
    }

    // method to access in activity after updating selection
    public List<Player> getStudentist() {
        return players;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Player singlestudent;
        TextView tvName;
        TextView tvEmailId;
        CheckBox chkSelected;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvName = itemLayoutView.findViewById(R.id.tvName);
            chkSelected = itemLayoutView
                    .findViewById(R.id.chkSelected);

        }

    }

}