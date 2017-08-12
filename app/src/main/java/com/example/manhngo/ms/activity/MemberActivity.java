package com.example.manhngo.ms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.MyCustomAdapter;
import com.example.manhngo.ms.Util.ToastUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class MemberActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText edtNhapTen;
    Button btnThem;
    Button btnSubmit;
    ListView lvTenNguoiChoi;
    ArrayList<String> dsNguoiChoi;
    Set dsNguoiChoi_Set;
    SharedPreferences preferences;
    MyCustomAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        edtNhapTen = (EditText) findViewById(R.id.edtNhapTen);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        lvTenNguoiChoi = (ListView) findViewById(R.id.lvTenNguoiChoi);

        btnThem.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        lvTenNguoiChoi.setOnItemClickListener(this);
        preferences = getSharedPreferences("my_data", MODE_PRIVATE);
        dsNguoiChoi = new ArrayList<>();
        dsNguoiChoi_Set = new HashSet();
        dsNguoiChoi_Set = preferences.getStringSet("DANH_SACH_NGUOI_CHOI", null);
        if (dsNguoiChoi_Set != null)
            dsNguoiChoi.addAll(dsNguoiChoi_Set);
        //instantiate custom adapter
        adapter = new MyCustomAdapter(this, R.layout.custom_listview, dsNguoiChoi);

        //handle listview and assign adapter
        lvTenNguoiChoi.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnThem) {
            String temp = edtNhapTen.getText().toString();
            if (!dsNguoiChoi.contains(temp)) {
                dsNguoiChoi.add(temp);
                adapter.notifyDataSetChanged();
                edtNhapTen.setText("");
            } else {
                ToastUtil.show(getApplicationContext(), "Tên bị trùng");
            }
        }
        if (v.getId() == R.id.btnSubmit) {
            //ToastUtil.show(getApplicationContext(), dsNguoiChoi.toString());
            if (dsNguoiChoi_Set == null)
                dsNguoiChoi_Set = new HashSet();
            dsNguoiChoi_Set.addAll(dsNguoiChoi);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putStringSet("DANH_SACH_NGUOI_CHOI", dsNguoiChoi_Set);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), ChooseHeroActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Clicked", "Item Text");
        String item = (String) parent.getItemAtPosition(position);
        edtNhapTen.setText(item);
    }
}
