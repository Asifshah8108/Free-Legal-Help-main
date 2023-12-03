package com.myapplicationname.legalhelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText ForgotEmail;
    private MaterialButton submitFp;
    private String email;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ForgotEmail = findViewById(R.id.userName);
        submitFp = (MaterialButton) findViewById(R.id.submitFp);

        mAuth = FirebaseAuth.getInstance();

        submitFp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }
    private void validateData(){
        email = ForgotEmail.getText().toString();
        if (email.isEmpty()){
            ForgotEmail.setError("Required");
        }
        else {
            forgetPass();
        }
    }
    private void forgetPass(){
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this, "Email Sent! Check Your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,Login.class));
                            finish();
                        }else {
                            Toast.makeText(ForgotPassword.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}