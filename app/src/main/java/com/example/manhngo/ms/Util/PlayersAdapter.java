package com.example.manhngo.ms.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.models.Player;

import java.util.List;

/**
 * Created by NgoXuanManh on 4/29/2017.
 */

public class PlayersAdapter extends ArrayAdapter<Player> {
    private static final String TAG = PlayersAdapter.class.getSimpleName();
    private List<Player> players;

    public PlayersAdapter(Context context, List<Player> players) {
        super(context, 0, players);
        this.players = players;
        Log.d(TAG, "PlayersAdapter: ");
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        Player player = getItem(position);
        ViewHolder mainViewholder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.player_item, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textView = convertView.findViewById(R.id.tv_name);
        viewHolder.btnRemove = convertView.findViewById(R.id.delete_btn);
        convertView.setTag(viewHolder);

        Log.d(TAG, "getView: ");
        mainViewholder = (ViewHolder) convertView.getTag();
        mainViewholder.textView.setText(player != null ? player.getName() : null);
        mainViewholder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mObjects.remove(position);
//                notifyDataSetChanged();
                ToastUtil.show(getContext(), "Button was clicked for list item " + position);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView textView;
        Button btnRemove;
    }
}

