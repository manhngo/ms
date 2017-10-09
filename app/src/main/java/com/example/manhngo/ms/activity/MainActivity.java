package com.example.manhngo.ms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.manhngo.ms.Adapter.MyStepperAdapter;
import com.example.manhngo.ms.R;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements StepperLayout.StepperListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = getIntent().getStringArrayListExtra("hero");
        Log.d(TAG, "onCreate: List" + list);


        mStepperLayout = findViewById(R.id.stepperLayout);
        MyStepperAdapter myStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this, list);
        mStepperLayout.setAdapter(myStepperAdapter);
        mStepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(this, "onCompleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(this, "onError! -> " + verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {
        Toast.makeText(this, "onStepSelected! -> " + newStepPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {
        finish();
    }

}
