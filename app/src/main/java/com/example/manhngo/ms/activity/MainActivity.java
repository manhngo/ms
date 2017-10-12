package com.example.manhngo.ms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.manhngo.ms.Adapter.MyStepperAdapter;
import com.example.manhngo.ms.Presenter.PlayerDetailsPresenter;
import com.example.manhngo.ms.Presenter.PlayerPresenter;
import com.example.manhngo.ms.R;
import com.example.manhngo.ms.Util.Function;
import com.example.manhngo.ms.inteface.FragmentToActivity;
import com.example.manhngo.ms.models.Player;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements StepperLayout.StepperListener, FragmentToActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private StepperLayout mStepperLayout;
    private List<Player> players = new ArrayList<>();
    private PlayerDetailsPresenter playerDetailsPresenter;
    private PlayerPresenter playerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = getIntent().getStringArrayListExtra("hero");
        Log.d(TAG, "onCreate: List" + list);


        playerPresenter = new PlayerPresenter(this);
        playerDetailsPresenter = new PlayerDetailsPresenter(this);
        mStepperLayout = findViewById(R.id.stepperLayout);
        MyStepperAdapter myStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this, list, playerPresenter.fetchAllPlayers());
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

    @Override
    public void onSelectFunction(long id, Function function) {
        long x = playerPresenter.updateFunctionPlayerDetails(id, function);
        Log.d(TAG, "onSelectFunction: " + id + " f " + function);
        Log.d(TAG, "onSelectFunction: 123 " + x);
    }

    @Override
    public void onSelect(long id) {
        Function function = Function.WOLF;
        if (function.equals(Function.WOLF)) {
            playerPresenter.selectPlayerWithActive(function, id, action);
        }
    }
}
