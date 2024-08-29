package com.example.leave_application_nmims;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class faculty_main2 extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
    Button b1,b2;
    RadioButton rb1,rb2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_main2);
        String key = RandomKeyGenerator.generateRandomKey();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("leave_applications").child(key);
        t1 = findViewById(R.id.textView14);
        t2 = findViewById(R.id.textView15);
        t3 = findViewById(R.id.textView16);
        t4 = findViewById(R.id.textView17);
        t5 = findViewById(R.id.textView18);
        t6 = findViewById(R.id.textView20);
        t7 = findViewById(R.id.textView22);
        t8 = findViewById(R.id.textView24);
        t9 = findViewById(R.id.textView26);
        t10 = findViewById(R.id.textView28);
        t11 = findViewById(R.id.textView30);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        b1 = findViewById(R.id.button8);
        b2 = findViewById(R.id.button9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isChecked() && rb2.isChecked()){
                    Toast.makeText(faculty_main2.this, "Leave sanctioned", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isChecked() && rb2.isChecked()){
                    Toast.makeText(faculty_main2.this, "Leave rejected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String sphone = dataSnapshot.child("studentphone").getValue(String.class);
                    String pphone = dataSnapshot.child("parentsphone").getValue(String.class);
                    String smail = dataSnapshot.child("selfmail").getValue(String.class);
                    String pmail = dataSnapshot.child("parentsmail").getValue(String.class);
                    String datefrom = dataSnapshot.child("datefrom").getValue(String.class);
                    String dateto = dataSnapshot.child("dateto").getValue(String.class);
                    String nodays = dataSnapshot.child("days").getValue(String.class);
                    String avg = dataSnapshot.child("attendance").getValue(String.class);
                    String reason = dataSnapshot.child("reason").getValue(String.class);
                    String academic = dataSnapshot.child("academicdays").getValue(String.class);
                    t1.setText(name);
                    t2.setText(sphone);
                    t3.setText(pphone);
                    t4.setText(smail);
                    t5.setText(pmail);
                    t6.setText(datefrom);
                    t7.setText(dateto);
                    t8.setText(nodays);
                    t9.setText(avg);
                    t10.setText(reason);
                    t11.setText(academic);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}

