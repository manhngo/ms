package com.example.manhngo.ms.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Key;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NgoXuanManh on 3/14/2017.
 */

public class ChooseHeroActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener {
    private static final String TAG = ChooseHeroActivity.class.getSimpleName();
    CheckBox chkThoSan;
    CheckBox chkBaoVe;
    CheckBox chkCupid;
    CheckBox chkPhuThuy;
    CheckBox chkTienTri;
    SeekBar seekBarSoi;
    Button btnSubmit;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_hero);

        seekBarSoi = findViewById(R.id.seekBarSoi);
        chkBaoVe = findViewById(R.id.chkBaoVe);
        chkCupid = findViewById(R.id.chkCupid);
        chkPhuThuy = findViewById(R.id.chkPhuThuy);
        chkThoSan = findViewById(R.id.chkThoSan);
        chkTienTri = findViewById(R.id.chkTienTri);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        seekBarSoi.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: choosehero");
        ArrayList<String> list = new ArrayList<>();
        list.add(Key.WOLF);
        if (chkBaoVe.isChecked()) {
            list.add(Key.BAO_VE);
        }
        if (chkTienTri.isChecked()) {
            list.add(Key.TIEN_TRI);
        }
        if (chkThoSan.isChecked()) {
            list.add(Key.THO_SAN);
        }
        if (chkPhuThuy.isChecked()) {
            list.add(Key.PHU_THUY);
        }
        if (chkCupid.isChecked()) {
            list.add(Key.CUPID);
        }

        //ToastUtil.show(getApplicationContext(), nhanVatThamGia);
        Set<String> set = new HashSet<>(list);
        SharedPreferences preferences = getSharedPreferences("my_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("NHAN_VAT_THAM_GIA", set);
        editor.putInt("WOLF", seekBarSoi.getProgress());
        editor.apply();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putStringArrayListExtra("hero", list);
        startActivity(intent);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        value = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
