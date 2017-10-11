package com.example.manhngo.ms.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.manhngo.ms.Adapter.PlayersCursorAdapter;
import com.example.manhngo.ms.Presenter.PlayerPresenter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.DBUtitls;
import com.example.manhngo.ms.Util.ToastUtil;
import com.example.manhngo.ms.dialog.PlayersDialogFragment;
import com.example.manhngo.ms.inteface.FragmentPlayersToActivityPlayers;
import com.example.manhngo.ms.models.Player;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class PlayersActivityPlayers extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, FragmentPlayersToActivityPlayers {

    public static String TAG = "PlayersActivityPlayers";
    EditText edtNhapTen;
    Button btnThem;
    Button btnSubmit;
    ListView lvTenNguoiChoi;
    ArrayList<String> dsNguoiChoi;
    Set dsNguoiChoi_Set;
    SharedPreferences preferences;
    PlayersCursorAdapter adapter;
    Player playerInProcess;

    PlayerPresenter playerPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        playerPresenter = new PlayerPresenter(this);


        edtNhapTen = findViewById(R.id.edtNhapTen);
        btnThem = findViewById(R.id.btnThem);
        btnSubmit = findViewById(R.id.btnSubmit);
        lvTenNguoiChoi = findViewById(R.id.lvTenNguoiChoi);

        btnThem.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        lvTenNguoiChoi.setOnItemClickListener(this);
        lvTenNguoiChoi.setOnItemLongClickListener(this);

        Log.d(TAG, "onCreate: " + playerPresenter.getPlayerList());
        adapter = new PlayersCursorAdapter(this, playerPresenter.fetchAllPlayers());
        lvTenNguoiChoi.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnThem) {
            String temp = edtNhapTen.getText().toString();
            Player player = new Player(temp);
            if (playerPresenter.addPlayer(player) <= 0) {
                ToastUtil.show(getApplicationContext(), "Tên bị trùng");
            }
            ;
            Log.d(TAG, "onClick: " + player.toString());
            adapter.changeCursor(playerPresenter.fetchAllPlayers());
            edtNhapTen.setText("");
        }
        if (v.getId() == R.id.btnSubmit) {
            Intent intent = new Intent(getApplicationContext(), ChooseHeroActivity.class);
            startActivity(intent);
        }
    }

    public void onDeleteItem() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        String myColumnValue = cursor.getString(cursor.getColumnIndex(DBUtitls.PLAYERS_COLUMN_NAME));
        Log.d("Clicked", "Item Text + " + myColumnValue);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
        playerInProcess = playerPresenter.getPlayerFromCursor(cursor);
        FragmentManager fm = getFragmentManager();
        PlayersDialogFragment playersDialogFragment = PlayersDialogFragment.newInstance(playerInProcess.getName());
        playersDialogFragment.show(fm, null);
        return false;
    }

    @Override
    public void onFinishedChangeText(String text) {
        playerInProcess.setName(text);
        playerPresenter.updateNameById(playerInProcess);
        adapter.changeCursor(playerPresenter.fetchAllPlayers());
    }
}
