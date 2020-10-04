package com.explo2k20;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class Locations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Button ADD = findViewById(R.id.button4);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Locations.this,Add_locations.class);
                startActivity(intent);
            }
        });




    }
}