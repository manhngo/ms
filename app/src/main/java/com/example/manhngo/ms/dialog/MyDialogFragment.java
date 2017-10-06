package com.example.manhngo.ms.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manhngo.ms.Adapter.NguoiChoiAdapter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.MyUtils;
import com.example.manhngo.ms.models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgoXuanManh on 7/23/2017.
 */

public class MyDialogFragment extends DialogFragment {
    private RecyclerView mRecyclerView;
    private NguoiChoiAdapter adapter;
    private List<Player> nguoiChois = new ArrayList<>();
    // this method create view for your Dialog

    public static MyDialogFragment newInstance(String data) {
        MyDialogFragment dialog = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("data", data);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_soi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //setadapter
        MyUtils.prepareRecyclerViewData(nguoiChois);
        NguoiChoiAdapter adapter = new NguoiChoiAdapter(getActivity(), nguoiChois);
        mRecyclerView.setAdapter(adapter);

    }
}