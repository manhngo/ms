package com.example.manhngo.ms.dialog;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhngo.ms.Adapter.CardViewSingleChooseAdapter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.inteface.DialogToFragment;

/**
 * Created by NgoXuanManh on 10/12/2017.
 */

public class SingleChooseDialogFragment extends DialogFragment implements CardViewSingleChooseAdapter.OnSelectItem {

    static Cursor cursor;
    CardViewSingleChooseAdapter cardViewSingleChooseAdapter;
    DialogToFragment dialogToFragment;

    public static SingleChooseDialogFragment newInstance(Cursor cursor) {
        SingleChooseDialogFragment.cursor = cursor;
        return new SingleChooseDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogToFragment = (DialogToFragment) getTargetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_recyclerview, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lấy giá trị tự bundle
        RecyclerView recyclerView = view.findViewById(R.id.rv_list_tiem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardViewSingleChooseAdapter = new CardViewSingleChooseAdapter(getActivity(), cursor, this);
        recyclerView.setAdapter(cardViewSingleChooseAdapter);

    }

    @Override
    public void onSeclect(long id) {
        dialogToFragment.onSelect(id);
    }
}
