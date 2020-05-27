package com.example.android.test1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.text.TextUtils.*;

public class Signup_Form extends AppCompatActivity {
    EditText txtEmail,txtPassword,txtConfirmPassword;
    Button btn_regiter;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Sign Up");

        txtEmail = (EditText)findViewById(R.id.txt_Email);
        txtPassword=(EditText)findViewById(R.id.txt_Password);
        txtConfirmPassword=(EditText)findViewById(R.id.txt_Confirm_Password);
        btn_regiter=(Button) findViewById(R.id.buttonRegister);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmPassword = txtConfirmPassword.getText().toString().trim();
//
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup_Form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_Form.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup_Form.this,"Please Enter Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(Signup_Form.this,"Password too short",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.equals(confirmPassword)){
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_Form.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup_Form.this,"Authentication Failed ",Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else{
                    Toast.makeText(Signup_Form.this,"Enter Confirm Password same as Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

