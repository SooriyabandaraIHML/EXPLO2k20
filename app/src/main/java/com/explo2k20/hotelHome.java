package com.explo2k20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hotelHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_home);


        Button VIEW = findViewById(R.id.ViewB);
        Button ADD = findViewById(R.id.BtAdd);



        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hotelHome.this,addHotels.class);
                startActivity(intent);
            }
        });

    }
}