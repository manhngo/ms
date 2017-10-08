package com.example.manhngo.ms.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.DBUtitls;

/**
 * Created by NgoXuanManh on 4/29/2017.
 */

public class PlayersCursorAdapter extends CursorAdapter {
    private static final String TAG = PlayersCursorAdapter.class.getSimpleName();

    public PlayersCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        Log.d(TAG, "PlayersCursorAdapter: ");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.player_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textView = view.findViewById(R.id.tv_name);
        viewHolder.textView.setText(cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME)));
    }

    public class ViewHolder {
        TextView textView;
    }

}

