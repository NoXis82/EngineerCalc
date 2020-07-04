package com.example.engineercalc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private FrameLayout beginCalc;
    private Switch switchType;
    private ImageView imgBegin;
    private ImageView imgEngineer;
    private static final int SETTINGS_REQUEST_CODE = 345;

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

    private void selectionMenuId(@MenuIntDef int id) {
        switch (id) {
            case MenuIntDef.OPTIONS:
                Intent intentOption = new Intent(MainActivity.this,
                        OptionActivity.class);
                startActivityForResult(intentOption, SETTINGS_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQUEST_CODE &&
                resultCode == RESULT_OK && data != null) {
            String imagePath = OptionActivity.getImagePathFromIntent(data);
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            imgBegin.setImageBitmap(image);
            imgEngineer.setImageBitmap(image);
        }
    }

    private void changeType() {
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

    private void initView() {
        beginCalc = findViewById(R.id.begin_calc);
        switchType = findViewById(R.id.switch_type);
        imgBegin = findViewById(R.id.background_begin_calc);
        imgEngineer = findViewById(R.id.background_engineer_calc);
    }
}
