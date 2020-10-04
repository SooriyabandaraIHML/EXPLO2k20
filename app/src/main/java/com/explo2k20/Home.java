package com.explo2k20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button Locations = findViewById(R.id.lbu1);
        Button Hotels = findViewById(R.id.hbu3);
        Button Budget = findViewById(R.id.bbu2);
        Button Trip_Planing = findViewById(R.id.tbu4);

        Locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Locations.class);
                startActivity(intent);
            }
        });

        Hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,hotelHome.class);
                startActivity(intent);
            }
        });
    }
}