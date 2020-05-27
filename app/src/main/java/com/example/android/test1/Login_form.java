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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_form extends AppCompatActivity {
    EditText txtEmail,txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");

        txtEmail = (EditText)findViewById(R.id.txt_Email);
        txtPassword = (EditText)findViewById(R.id.txt_Password);
        btn_login = (Button)findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login_form.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login_form.this,"Please Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Login_form.this,"Password too short",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                  startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login_form.this,"Login Failed or User not available",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }

    public void btn_signupForm(View view) {

        startActivity(new Intent(getApplicationContext(),Signup_Form.class));

    }
}
