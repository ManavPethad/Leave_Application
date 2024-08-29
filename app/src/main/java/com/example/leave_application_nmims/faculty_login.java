package com.example.leave_application_nmims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class faculty_login extends AppCompatActivity {
    Button b1;
    EditText e1,p1;
    TextView t1;
    ConstraintLayout cl1;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_login);
        mAuth = FirebaseAuth.getInstance();
        b1 = findViewById(R.id.button);
        e1 = findViewById(R.id.email);
        p1 = findViewById(R.id.editTextTextPassword2);
        t1 = findViewById(R.id.textView2);
        cl1 = findViewById(R.id.cl1);
        cl1.setBackgroundResource(R.color.white);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = p1.getText().toString();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(faculty_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(faculty_login.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(faculty_login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(faculty_login.this, faculty_main.class);
                                    startActivity(intent);
                                    finish(); // Finish the current activity to prevent the user from navigating back to it
                                }
                                else
                                {
                                    Toast.makeText(faculty_login.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(faculty_login.this, forget_password.class);
                startActivity(intent);
            }
        });

    }
}
