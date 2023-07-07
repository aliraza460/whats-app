package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    ActivitySigninBinding binding;
    ProgressDialog dialog;

    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(Signin.this);
        dialog.setTitle("Login");
        dialog.setMessage("Login to your Account");

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.show();

               Auth.signInWithEmailAndPassword(binding.Email.getText().toString(), binding.Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       dialog.dismiss();

                       if (task.isSuccessful())
                       {
                           Toast.makeText(Signin.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(Signin.this, MainActivity.class);
                           startActivity(intent);
                       }
                       else {
                           Toast.makeText(Signin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });


            }
        });
        if (Auth.getCurrentUser() != null)
        {
            Intent intent = new Intent(Signin.this, MainActivity.class);
            startActivity(intent);
        }

        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}