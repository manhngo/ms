package com.example.manhngo.ms.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhngo.ms.R;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class ChooseWolfDialogFragment extends DialogFragment {
    private static final String TAG = ChooseWolfDialogFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_choose_wolf, container);
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
}
