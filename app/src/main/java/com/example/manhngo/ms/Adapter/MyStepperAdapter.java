package com.example.manhngo.ms.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.example.manhngo.ms.Fragment.BaoVeFragment;
import com.example.manhngo.ms.Fragment.CupidFragment;
import com.example.manhngo.ms.Fragment.PhuThuyFragment;
import com.example.manhngo.ms.Fragment.SoiFragment;
import com.example.manhngo.ms.Fragment.ThoSanFragment;
import com.example.manhngo.ms.Fragment.TienTriFragment;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Key;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.ArrayList;

/**
 * Created by NgoXuanManh on 7/23/2017.
 */

public class MyStepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "A";
    private static Cursor cursor;
    private ArrayList<String> heroes;

    public MyStepperAdapter(FragmentManager fm, Context context, ArrayList<String> heroes, Cursor cursor) {
        super(fm, context);
        this.heroes = heroes;
        MyStepperAdapter.cursor = cursor;
    }

    @Override
    public Step createStep(int position) {

        if (TextUtils.equals(heroes.get(position), Key.WOLF)) {
            return SoiFragment.newInstance(cursor);
        }

        if (TextUtils.equals(heroes.get(position), Key.CUPID)) {
            return new CupidFragment();
        }

        if (TextUtils.equals(heroes.get(position), Key.BAO_VE)) {
            return new BaoVeFragment();
        }

        if (TextUtils.equals(heroes.get(position), Key.PHU_THUY)) {
            return new PhuThuyFragment();
        }

        if (TextUtils.equals(heroes.get(position), Key.TIEN_TRI)) {
            return new TienTriFragment();
        }

        if (TextUtils.equals(heroes.get(position), Key.THO_SAN)) {
            return new ThoSanFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return heroes.size(); // wolf alway exist
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        StepViewModel.Builder builder = new StepViewModel.Builder(context)
                .setTitle(R.string.tab_title);
        if (TextUtils.equals(heroes.get(position), Key.WOLF)) {
            builder.setTitle(Key.WOLF)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Cancel")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }

        if (TextUtils.equals(heroes.get(position), Key.CUPID)) {
            builder.setTitle(Key.CUPID)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Back")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }

        if (TextUtils.equals(heroes.get(position), Key.BAO_VE)) {
            builder.setTitle(Key.BAO_VE)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Back")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }

        if (TextUtils.equals(heroes.get(position), Key.PHU_THUY)) {
            builder.setTitle(Key.PHU_THUY)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Back")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }

        if (TextUtils.equals(heroes.get(position), Key.TIEN_TRI)) {
            builder.setTitle(Key.TIEN_TRI)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Back")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }

        if (TextUtils.equals(heroes.get(position), Key.THO_SAN)) {
            builder.setTitle(Key.THO_SAN)
                    .setEndButtonLabel("This way")
                    .setBackButtonLabel("Back")
                    .setBackButtonStartDrawableResId(StepViewModel.NULL_DRAWABLE);
        }
        return builder.create();
    }
}