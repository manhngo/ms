package com.example.manhngo.ms.Util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhngo.ms.Player;
import com.example.manhngo.ms.R;

import java.util.List;

/**
 * Created by NgoXuanManh on 4/29/2017.
 */

public class MyCustomAdapter extends ArrayAdapter<String> {
    private int layout;
    private List<String> players;

    public MyCustomAdapter(Context context, int resource, List<Player> players) {
        super(context, resource);
        players = players;
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewholder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.tv_name);
            viewHolder.btnRemove = convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);
        }

        mainViewholder = (ViewHolder) convertView.getTag();
        mainViewholder.textView.setText(getItem(position));
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

        ImageView thumbnail;
        TextView textView;
        Button btnRemove;
    }
}

