package com.example.leave_application_nmims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class faculty_main extends AppCompatActivity {
    private DatabaseReference databaseReference;
    TextView t1,t2,t3;
    Button b1,b2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_main);
        b1 = findViewById(R.id.see_details);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faculty_main.this, faculty_main2.class);
                startActivity(intent);
            }
        });

        b2 = findViewById(R.id.see_details1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faculty_main.this, seedetails2.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("leave_applications");
        String key = RandomKeyGenerator.generateRandomKey();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("leave_applications").child(key);
        t1 = findViewById(R.id.faculty_name);
        t2 = findViewById(R.id.date_from);
        t3 = findViewById(R.id.date_to);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String date_from = dataSnapshot.child("datefrom").getValue(String.class);
                    String date_to = dataSnapshot.child("dateto").getValue(String.class);
                    t1.setText(name);
                    t2.setText(date_from);
                    t3.setText(date_to);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}