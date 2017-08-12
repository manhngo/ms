package com.example.manhngo.ms.Util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.manhngo.ms.R;

import java.util.List;

/**
 * Created by NgoXuanManh on 4/29/2017.
 */

public class MyCustomAdapter extends ArrayAdapter<String> {
    private int layout;
    private List<String> mObjects;

    public MyCustomAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        mObjects = objects;
        layout = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mainViewholder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            //viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_string);
            viewHolder.editText = (EditText) convertView.findViewById(R.id.editText);
            viewHolder.btnRemove = (Button) convertView.findViewById(R.id.delete_btn);
            convertView.setTag(viewHolder);
        }

        mainViewholder = (ViewHolder) convertView.getTag();
        mainViewholder.editText.setText(getItem(position));
        mainViewholder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mObjects.set(position, s.toString());
                ToastUtil.show(getContext(), mObjects.get(position));

            }
        });
        mainViewholder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mObjects.remove(position);
                notifyDataSetChanged();
                ToastUtil.show(getContext(), "Button was clicked for list item " + position);
            }
        });

        return convertView;
    }

    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        EditText editText;
        Button btnRemove;
    }
}

