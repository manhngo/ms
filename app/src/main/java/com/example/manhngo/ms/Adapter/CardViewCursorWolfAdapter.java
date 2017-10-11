package com.example.manhngo.ms.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.inteface.AdapterToDialogFragment;
import com.example.manhngo.ms.libs.CursorRecyclerViewAdapter;
import com.example.manhngo.ms.models.Player;

/**
 * Created by NgoXuanManh on 10/11/2017.
 */

public class CardViewCursorWolfAdapter extends CursorRecyclerViewAdapter<CardViewCursorWolfAdapter.ViewHolder> {

    private static final String TAG = CardViewCursorWolfAdapter.class.getSimpleName();

    private AdapterToDialogFragment adapterToDialogFragment;

    public CardViewCursorWolfAdapter(Context context, Cursor cursor, AdapterToDialogFragment adapterToDialogFragment) {
        super(context, cursor);
        this.adapterToDialogFragment = adapterToDialogFragment;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        Log.d(TAG, "onBindViewHolder: ");
        final Player player = Player.fromCursor(cursor);
        viewHolder.tvName.setText(player.getName());
        if (player.getFunction().equals(Function.WOLF)) {
            viewHolder.chkWolf.setChecked(true);
        }

        viewHolder.chkWolf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    adapterToDialogFragment.onSelect(player.getId(), Function.WOLF);
                } else {
                    adapterToDialogFragment.onSelect(player.getId(), Function.NOTHING);
                }
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_wolf_item, parent, false);
        return new ViewHolder(itemView);
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
