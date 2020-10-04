package com.explo2k20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Add_locations extends AppCompatActivity {

    EditText name,descrip,caption;
    Button button,button2,btnUpload;
    DatabaseReference dbRef;
    Dlocation dl;

    private void clearControls() {
        name.setText("");
        descrip.setText("");
        caption.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_locations);

        name = findViewById(R.id.EtTitle);
        descrip = findViewById(R.id.EtDestin);
        caption = findViewById(R.id.EtDura);



        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);




        dl = new Dlocation();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dbRef = FirebaseDatabase.getInstance().getReference().child("Dlocation");


                try {
                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a location Name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(descrip.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the Description", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(caption.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the caption", Toast.LENGTH_SHORT).show();


                    else {
                        dl.setLName(name.getText().toString().trim());
                        dl.setDescription(descrip.getText().toString().trim());
                        dl.setCaption(caption.getText().toString().trim());

                        dbRef.push().setValue(dl);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                } catch (
                        NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                }
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef= FirebaseDatabase.getInstance().getReference().child("Dlocation");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("dl1")){
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

    }
}