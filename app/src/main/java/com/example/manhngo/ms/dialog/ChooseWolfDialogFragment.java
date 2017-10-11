package com.example.manhngo.ms.dialog;

import android.database.Cursor;
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

import com.example.manhngo.ms.Adapter.CardViewCursorWolfAdapter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.inteface.AdapterToDialogFragment;
import com.example.manhngo.ms.inteface.DialogToFragment;

/**
 * Created by NgoXuanManh on 10/8/2017.
 */

public class ChooseWolfDialogFragment extends DialogFragment implements View.OnClickListener, AdapterToDialogFragment {
    private static final String TAG = ChooseWolfDialogFragment.class.getSimpleName();
    static Cursor cursor;
    CardViewCursorWolfAdapter cardViewCursorWolfAdapter;
    DialogToFragment dialogToFragment;

    public static ChooseWolfDialogFragment newInstance(Cursor cursor) {
        ChooseWolfDialogFragment.cursor = cursor;
        return new ChooseWolfDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogToFragment = (DialogToFragment) getTargetFragment();
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardViewCursorWolfAdapter = new CardViewCursorWolfAdapter(getActivity(), cursor, this);
        recyclerView.setAdapter(cardViewCursorWolfAdapter);

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

    @Override
    public void onSelect(long id, Function function) {
        dialogToFragment.onSelect(id, function);
    }

    @Override
    public void onDetach() {
        dialogToFragment = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }
}
