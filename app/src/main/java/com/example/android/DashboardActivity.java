package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class DashboardActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        progressBar = findViewById(R.id.progressBarDashboard);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void profile_click(View view) {
        progressBar.setVisibility(View.VISIBLE);
        openProfileActivity();

    }

    public void insignias_click(View view){
        progressBar.setVisibility(View.VISIBLE);
        openInsigniasActivity();
    }

    public void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }

    public void openInsigniasActivity(){
        Intent intent = new Intent(this, BadgesActivity.class);
        this.startActivity(intent);
    }
}