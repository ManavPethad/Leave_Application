package com.example.leave_application_nmims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
    ConstraintLayout cl1;
    ImageView i1;
    Button b1;
    EditText e1;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        cl1 = findViewById(R.id.cl4);
        i1 = findViewById(R.id.imageView5);
        b1 = findViewById(R.id.button5);
        e1 = findViewById(R.id.editTextTextEmailAddress);

        cl1.setBackgroundResource(R.color.white);
        auth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();

            }
        });

    }
    private void validatedata() {
        String email = e1.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        else{
            forgetPass();
        }
    }

    private void forgetPass() {
        String email = e1.getText().toString();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forget_password.this, "Check email", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(forget_password.this, "Wrong email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}