package com.myapplicationname.legalhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registration extends AppCompatActivity {

    private EditText RegEmail , RegPassword, username;
    private MaterialButton regbtn;
    private MaterialButton loginbtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.userName);
        RegEmail = findViewById(R.id.RegEmail);
        RegPassword = findViewById(R.id.LoginPassword);

        regbtn = (MaterialButton) findViewById(R.id.RegisterBtn);

        loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }
    private void createUser(){
        String email = RegEmail.getText().toString();
        String pass = RegPassword.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Registration.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this , Login.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registration.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                RegPassword.setError("Empty Fields Are not Allowed");
            }
        }else if(email.isEmpty()){
            RegEmail.setError("Empty Fields Are not Allowed");
        }else{
            RegEmail.setError("Pleas Enter Correct Email");
        }
    }

}