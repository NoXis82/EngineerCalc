package com.example.engineercalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.provider.CalendarContract.CalendarCache.URI;

public class OptionActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_WRITE = 100;

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
                Intent intentMain = new Intent(OptionActivity.this,
                        MainActivity.class);
                startActivity(intentMain);
                finish();
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
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    name);
            ImageView img = findViewById(R.id.image_background);
            Bitmap map = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (map != null) {
                img.setImageBitmap(map);
            } else {
                Toast.makeText(this,
                        "Фаил не найден",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
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
                            "Нет доступа",
                            Toast.LENGTH_SHORT).show();
                }
        }
    }
}