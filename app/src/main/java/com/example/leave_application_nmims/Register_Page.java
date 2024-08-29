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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_Page extends AppCompatActivity {
    Button registerButton;
    EditText e1,e2;
    FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        e1 = findViewById(R.id.email_student);
        e2 = findViewById(R.id.password_student);
        mAuth = FirebaseAuth.getInstance();
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String pass = e2.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(Register_Page.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(Register_Page.this, "Enter passsword", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register_Page.this, "Registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register_Page.this,Login_Page.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Register_Page.this, "Not registered", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
