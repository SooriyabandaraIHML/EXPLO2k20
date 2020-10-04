package com.explo2k20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addHotels extends AppCompatActivity {

    EditText TxtHName, TxtHAddress, TxtHPhone, TxtHEmail;
    Button hsave, hShow, btnUpdate, btnDelete,HomeBack;
    DatabaseReference dbRef;
    Hotel ht;

    private void clearControls () {
        TxtHName.setText("");
        TxtHAddress.setText("");
        TxtHPhone.setText("");
        TxtHEmail.setText("");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotels);

        TxtHName=findViewById(R.id.hName);
        TxtHAddress=findViewById(R.id.Haddress);
        TxtHPhone=findViewById(R.id.hPhone );
        TxtHEmail=findViewById(R.id.hEmail);

        hsave = findViewById(R.id.hsave);
        hShow=findViewById(R.id.hShow);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        HomeBack = findViewById(R.id.HomeBack);

        ht=new Hotel();

        hsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");


                try {
                    if (TextUtils.isEmpty(TxtHName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Hotel Name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(TxtHAddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hotel Address", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(TxtHPhone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hotel Phone Number ", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(TxtHEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Hotel Email", Toast.LENGTH_SHORT).show();


                    else {
                        ht.setName(TxtHName.getText().toString().trim());
                        ht.setAddress(TxtHAddress.getText().toString().trim());
                        ht.setPhone(TxtHPhone.getText().toString().trim());
                        ht.setEmail(TxtHEmail.getText().toString().trim());

                        dbRef.push().setValue(ht);
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }


                } catch (
                        NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Phone number", Toast.LENGTH_SHORT).show();

                }
            }

        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef= FirebaseDatabase.getInstance().getReference().child("Hotel");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("ht1")){
                            try{
                                ht.setName(TxtHName.getText().toString().trim());
                                ht.setAddress(TxtHAddress.getText().toString().trim());
                                ht.setPhone(TxtHPhone.getText().toString().trim());
                                ht.setEmail(TxtHEmail.getText().toString().trim());

                                dbRef=FirebaseDatabase.getInstance().getReference().child("Hotel").child("ht1");
                                dbRef.setValue(ht);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef= FirebaseDatabase.getInstance().getReference().child("Hotel");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("ht1")){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("Hotel").child("ht1");
                            dbRef.removeValue();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        HomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addHotels.this,hotelHome.class);
                startActivity(intent);
            }
        });
    }
}