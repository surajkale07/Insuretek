package com.example.insuretek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    TextView register,forget;
    EditText emailtxt, passwordtxt;
    Button btn_login;
    ProgressBar progressBar;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    public String email,password;
    boolean isAllFieldsChecked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            emailtxt=findViewById(R.id.email);
            passwordtxt=findViewById(R.id.pass);
            email=String.valueOf(emailtxt.getText());


            btn_login=findViewById(R.id.btn_login);
            progressBar=findViewById(R.id.progress);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    isAllFieldsChecked = CheckAllFields();
                    email = String.valueOf(emailtxt.getText());
                    password = String.valueOf(passwordtxt.getText());
                    if (isAllFieldsChecked) {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                                Toast.makeText(loginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(loginActivity.this, HomeScreen.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(loginActivity.this, "Please verify your email id", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(loginActivity.this, "Login failed. Enter valid credentials..", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });

            register= findViewById(R.id.register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(loginActivity.this, register.class);
                    startActivity(intent);
                }
            });

            forget=findViewById(R.id.forget);
            forget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (emailtxt.length() == 0) {
                        emailtxt.setError("Email is required");
                    }
                    mAuth.sendPasswordResetEmail(emailtxt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(loginActivity.this,"please check your inbox for password rest link",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(loginActivity.this,"please enter registered email address",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            });
        }

        public boolean CheckAllFields() {

            if (emailtxt.length() == 0) {
                emailtxt.setError("Email is required");
                return false;
            }

            if (passwordtxt.length() == 0) {
                passwordtxt.setError("Password is required");
                return false;
            } else if (passwordtxt.length() < 6) {
                passwordtxt.setError("Password must be minimum 6 characters");
                return false;
            }

            // after all validation return true.
            return true;
        }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null && mAuth.getCurrentUser().isEmailVerified()){
            Intent intent=new Intent(this,HomeScreen.class);
            startActivity(intent);
        }

    }
}
