package com.example.engineercalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    FrameLayout beginCalc;
    Switch switchType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        initView();
        changeType();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        selectionMenuId(id);
        return super.onOptionsItemSelected(item);
    }

    public void selectionMenuId(@MenuIntDef int id) {
        switch (id) {
            case MenuIntDef.OPTIONS:
                Intent intentOption = new Intent(MainActivity.this,
                        OptionActivity.class);
                startActivity(intentOption);
                break;
        }
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
