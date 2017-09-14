package com.example.manhngo.ms.Fragment;

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
import android.widget.ImageView;

import com.example.manhngo.ms.Adapter.NguoiChoiAdapter;
import com.example.manhngo.ms.Player;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.MyUtils;
import com.example.manhngo.ms.Util.ToastUtil;
import com.example.manhngo.ms.dialog.MyDialogFragment;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgoXuanManh on 7/23/2017.
 */

public class SoiFragment extends Fragment implements BlockingStep {
    private List<Player> nguoiChois = new ArrayList<>();
    private NguoiChoiAdapter nguoiChoiAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_soi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imgbtn_bite);
        imageView.setImageResource(R.drawable.soi_icon);
        MyUtils.prepareRecyclerViewData(nguoiChois);
        nguoiChoiAdapter = new NguoiChoiAdapter(getActivity(), nguoiChois);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(nguoiChoiAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        ToastUtil.show(getContext(), "on " + position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                        ToastUtil.show(getContext(), "on long " + position);
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
}