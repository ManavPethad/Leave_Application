package com.example.leave_application_nmims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Page extends AppCompatActivity {
    Button loginButton;
    EditText e1,e2;
    FirebaseAuth mAuth;
    TextView t1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        e1 = findViewById(R.id.student_email);
        e2 = findViewById(R.id.student_password);
        t1 = findViewById(R.id.textView_student);
        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Page.this, forget_password.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_Page.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Page.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_Page.this, "Login Successsfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login_Page.this, Main_Home_Page_Dashboard.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Login_Page.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}