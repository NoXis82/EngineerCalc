package com.example.engineercalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    FrameLayout beginCalc;
    Switch switchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        changeType();
    }

    public void changeType() {
        switchType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    beginCalc.setVisibility(View.GONE);
                } else {
                    beginCalc.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void initView() {
        beginCalc = findViewById(R.id.begin_calc);
        switchType = findViewById(R.id.switch_type);
    }
}
