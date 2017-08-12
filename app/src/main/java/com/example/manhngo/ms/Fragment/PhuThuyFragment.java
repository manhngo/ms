package com.example.manhngo.ms.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.FragmentClickListener;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * Created by NgoXuanManh on 3/15/2017.
 */

public class PhuThuyFragment extends Fragment implements BlockingStep, View.OnClickListener {

    FragmentClickListener fragmentClickListener;
    Button button;

    public PhuThuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soi, container, false);
//        button = (Button) view.findViewById(R.id.btnSoi);
//        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.btnSoi){
//            fragmentClickListener.onFragmentClick();
//        }
    }

    // TODO: 7/23/2017 blocking step
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