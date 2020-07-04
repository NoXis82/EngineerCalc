package com.example.engineercalc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;


public class OptionActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_WRITE = 100;
    private static final String IMAGE_KEY = "IMAGE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        loadBtn();
    }

    public void loadBtn() {
        Button btnLoad = findViewById(R.id.loadBtn);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionCheck();
            }
        });
    }

    public void permissionCheck() {
        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            loadImg();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE);
        }
    }

    private void loadImg() {
        EditText fileName = findViewById(R.id.edit_text);
        String name = fileName.getText().toString();
        if (isExternalStorageWritable()) {
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    name);
            if (!file.exists()) {
                Toast.makeText(this,
                        R.string.file_not_found,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            setResult(RESULT_OK, new Intent().putExtra(IMAGE_KEY, file.getAbsolutePath()));
            finish();
        }
    }

    @Nullable
    public static String getImagePathFromIntent(@NonNull Intent intent) {
        return intent.getStringExtra(IMAGE_KEY);
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WRITE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadImg();
                } else {
                    Toast.makeText(this,
                            R.string.no_access,
                            Toast.LENGTH_SHORT).show();
                }
        }
    }
}