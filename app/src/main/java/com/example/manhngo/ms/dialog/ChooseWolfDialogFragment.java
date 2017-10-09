package com.example.manhngo.ms.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manhngo.ms.Adapter.CardViewWolfAdapter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.models.Player;

import java.util.List;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class ChooseWolfDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = ChooseWolfDialogFragment.class.getSimpleName();

    static List<Player> players;
    CardViewWolfAdapter cardViewWolfAdapter;

    public static ChooseWolfDialogFragment newInstance(List<Player> players) {
        ChooseWolfDialogFragment dialog = new ChooseWolfDialogFragment();
        ChooseWolfDialogFragment.players = players;
        return dialog;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_choose_wolf, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lấy giá trị tự bundle
        RecyclerView recyclerView = view.findViewById(R.id.rv_choose_wolf);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        Button btnClose = view.findViewById(R.id.btn_close);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardViewWolfAdapter = new CardViewWolfAdapter(players);
        recyclerView.setAdapter(cardViewWolfAdapter);

        btnConfirm.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                Log.d(TAG, "onClick: confirm");
                getDialog().dismiss();
                break;
            case R.id.btn_close:
                Log.d(TAG, "onClick: close");
                getDialog().dismiss();
                break;
            default:
                break;
        }
    }
}
