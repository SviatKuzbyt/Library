package com.sviatkuzbyt.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sviatkuzbyt.library.ui.main.MainActivity;

public class TestTask extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activity = new Intent(this, MainActivity.class);
        startActivity(activity);
    }
}
