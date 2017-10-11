package com.example.manhngo.ms.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.inteface.FragmentPlayersToActivityPlayers;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class PlayersDialogFragment extends DialogFragment {
    //Được dùng khi khởi tạo dialog mục đích nhận giá trị

    private static final String TAG = PlayersDialogFragment.class.getSimpleName();

    FragmentPlayersToActivityPlayers fragmentToActivity;

    public static PlayersDialogFragment newInstance(String data) {
        PlayersDialogFragment dialog = new PlayersDialogFragment();
        Bundle args = new Bundle();
        args.putString("data", data);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentPlayersToActivityPlayers) {
            fragmentToActivity = (FragmentPlayersToActivityPlayers) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_member, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lấy giá trị tự bundle
        String data = getArguments().getString("data", "");
        final EditText edtName = view.findViewById(R.id.edt_name);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        Button btnClose = view.findViewById(R.id.btn_close);
        edtName.setText(data);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 1111");
                fragmentToActivity.onFinishedChangeText(String.valueOf(edtName.getText()));
                getDialog().dismiss();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public void onDetach() {
        fragmentToActivity = null;
        super.onDetach();
    }
}
