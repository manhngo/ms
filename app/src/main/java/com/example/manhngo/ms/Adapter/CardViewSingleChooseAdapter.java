package com.example.manhngo.ms.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.libs.CursorRecyclerViewAdapter;
import com.example.manhngo.ms.models.Player;

/**
 * Created by NgoXuanManh on 10/12/2017.
 */

public class CardViewSingleChooseAdapter extends CursorRecyclerViewAdapter<CardViewSingleChooseAdapter.ViewHolder> {

    private static final String TAG = CardViewSingleChooseAdapter.class.getSimpleName();

    private OnSelectItem onSelectItem;

    public CardViewSingleChooseAdapter(Context context, Cursor cursor, OnSelectItem onSelectItem) {
        super(context, cursor);
        this.onSelectItem = onSelectItem;
    }


    @Override
    public void onBindViewHolder(CardViewSingleChooseAdapter.ViewHolder viewHolder, Cursor cursor) {
        Log.d(TAG, "onBindViewHolder: ");
        final Player player = Player.fromCursor(cursor);
        viewHolder.tvName.setText(player.getName());
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectItem.onSeclect(player.getId());
            }
        });

    }

    @Override
    public CardViewSingleChooseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choose_single_item, parent, false);
        return new CardViewSingleChooseAdapter.ViewHolder(itemView);
    }

    public interface OnSelectItem {
        void onSeclect(long id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvName = itemLayoutView.findViewById(R.id.tv_name);
        }
    }
}
