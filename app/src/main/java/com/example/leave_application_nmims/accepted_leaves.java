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

public class accepted_leaves extends AppCompatActivity {
    TextView t1,t2;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_leaves);
        t1 = findViewById(R.id.date_from);
        t2 = findViewById(R.id.date_to);
        String key = RandomKeyGenerator.generateRandomKey();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("leave_applications").child(key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String datefrom = snapshot.child("datefrom").getValue(String.class);
                    String dateto = snapshot.child("dateto").getValue(String.class);
                    t1.setText(datefrom);
                    t2.setText(dateto);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}