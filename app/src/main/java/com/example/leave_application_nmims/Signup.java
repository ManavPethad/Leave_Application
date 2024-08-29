package com.example.leave_application_nmims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity{
    ConstraintLayout cl2;
    ImageView i2;
    EditText t1,t2,t4,t5,t6;
    TextView t3;
    Button b1;
    FirebaseAuth mAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        b1 = findViewById(R.id.button3);
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.email);
        t3 = findViewById(R.id.textView);
        t4 = findViewById(R.id.sap);
        t5 = findViewById(R.id.editTextTextPassword);
        t6 =findViewById(R.id.editTextTextPassword5);
        i2 = findViewById(R.id.imageView2);
        cl2 = findViewById(R.id.cl2);
        cl2.setBackgroundResource(R.color.white);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t2.getText().toString();
                String password = t5.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Signup.this, "Please enter EMAIL ID", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Signup.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Signup.this, faculty_login.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}


