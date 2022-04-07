package com.funproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.funproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, PostFragment.newInstance())
                .addToBackStack("post")
                .commit();
    }

}