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

import com.example.manhngo.ms.Presenter.PlayerPresenter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.PlayersAdapter;
import com.example.manhngo.ms.Util.ToastUtil;
import com.example.manhngo.ms.models.Player;
import com.example.manhngo.ms.models.PlayerDatabaseHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class MemberActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static String TAG = "MemberActivity";
    EditText edtNhapTen;
    Button btnThem;
    Button btnSubmit;
    ListView lvTenNguoiChoi;
    ArrayList<String> dsNguoiChoi;
    Set dsNguoiChoi_Set;
    SharedPreferences preferences;
    PlayersAdapter adapter;
    PlayerDatabaseHelper databaseHelper;
    PlayerPresenter playerPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        playerPresenter = new PlayerPresenter();


        edtNhapTen = findViewById(R.id.edtNhapTen);
        btnThem = findViewById(R.id.btnThem);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvTenNguoiChoi = findViewById(R.id.lvTenNguoiChoi);

        btnThem.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        lvTenNguoiChoi.setOnItemClickListener(this);


//        preferences = getSharedPreferences("my_data", MODE_PRIVATE);
//        dsNguoiChoi = new ArrayList<>();
//        dsNguoiChoi_Set = new HashSet();
//        dsNguoiChoi_Set = preferences.getStringSet("DANH_SACH_NGUOI_CHOI", null);
//        if (dsNguoiChoi_Set != null)
//            dsNguoiChoi.addAll(dsNguoiChoi_Set);
        //instantiate custom adapter

        //handle listview and assign adapter
        databaseHelper = PlayerDatabaseHelper.getInstance(this);

        playerPresenter.setPlayerList(databaseHelper.getAllPlayers());
        adapter = new PlayersAdapter(this, playerPresenter.getPlayerList());
        lvTenNguoiChoi.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnThem) {
            String temp = edtNhapTen.getText().toString();
            Player player = new Player(temp);
            databaseHelper.addPlayer(player);
            Log.d(TAG, "onClick: " + player.toString());
            playerPresenter.addPlayer(player);
            adapter.notifyDataSetChanged();
            edtNhapTen.setText("");

            ToastUtil.show(getApplicationContext(), "Tên bị trùng");
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

    public void onDeleteItem() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Clicked", "Item Text");
        String item = (String) parent.getItemAtPosition(position);
        edtNhapTen.setText(item);
    }
}
