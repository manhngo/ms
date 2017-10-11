package com.example.manhngo.ms.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.manhngo.ms.Adapter.NguoiChoiAdapter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.Util.MyUtils;
import com.example.manhngo.ms.Util.ToastUtil;
import com.example.manhngo.ms.dialog.ChooseWolfDialogFragment;
import com.example.manhngo.ms.dialog.MyDialogFragment;
import com.example.manhngo.ms.inteface.DialogToFragment;
import com.example.manhngo.ms.inteface.FragmentToActivity;
import com.example.manhngo.ms.models.Player;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgoXuanManh on 7/23/2017.
 */

public class SoiFragment extends Fragment implements BlockingStep, DialogToFragment {
    static Cursor cursor;
    FragmentToActivity fragmentToActivity;
    private List<Player> players = new ArrayList<>();

    public static SoiFragment newInstance(Cursor cursor) {
        SoiFragment.cursor = cursor;
        return new SoiFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_soi, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentToActivity = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_chose_wolf);
        ImageButton imageButton = view.findViewById(R.id.imgbtn_bite);
        MyUtils.prepareRecyclerViewData(players);
        NguoiChoiAdapter nguoiChoiAdapter = new NguoiChoiAdapter(getActivity(), players);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(nguoiChoiAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        ToastUtil.show(getActivity(), "on " + position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever

                        ToastUtil.show(getActivity(), "on long " + position);
                        FragmentManager fm = getFragmentManager();
                        ChooseWolfDialogFragment chooseWolfDialogFragment = ChooseWolfDialogFragment.newInstance(cursor);
                        chooseWolfDialogFragment.setTargetFragment(SoiFragment.this, 0);
                        chooseWolfDialogFragment.show(fm, null);
                    }
                })
        );
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("Ngo xuan manh");
                myDialogFragment.show(fm, null);
            }
        });
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onSelect(long id, Function function) {
        fragmentToActivity.onSelect(id, function);
    }

    @Override
    public void onDetach() {
        fragmentToActivity = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }
}