    package com.example.insuretek;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ProgressBar;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FirebaseFirestore;

    import java.util.HashMap;
    import java.util.Map;

public class register extends AppCompatActivity {

    EditText nametxt,emailidtxt,companytxt,destxt,contacttxt,passregistxt;
    Button btn_reg;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        nametxt=findViewById(R.id.name);
        emailidtxt=findViewById(R.id.emailid);
        companytxt=findViewById(R.id.company);
        destxt=findViewById(R.id.des);
        contacttxt=findViewById(R.id.contact);
        passregistxt=findViewById(R.id.passregis);
        progressBar=findViewById(R.id.progress);

        btn_reg=findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name,email,company,designation,contact,passwordreg;
                name= String.valueOf(nametxt.getText());
                email= String.valueOf(emailidtxt.getText());
                company= String.valueOf(companytxt.getText());
                designation= String.valueOf(destxt.getText());
                contact= String.valueOf(contacttxt.getText());
                passwordreg= String.valueOf(passregistxt.getText());

//                if(TextUtils.isEmpty(name)){
//                    Toast.makeText(register.this,"Enter name",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(email)){
//                    Toast.makeText(register.this,"Enter email",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(company)){
//                    Toast.makeText(register.this,"Enter company name",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(designation)){
//                    Toast.makeText(register.this,"Enter your designation",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(contact)){
//                    Toast.makeText(register.this,"Enter contact number",Toast.LENGTH_SHORT).show();
//                    return;
//                }if(TextUtils.isEmpty(passwordreg)){
//                    Toast.makeText(register.this,"Enter password",Toast.LENGTH_SHORT).show();
//                    return;
//                }


                mAuth.createUserWithEmailAndPassword( email,passwordreg)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                         if (task.isSuccessful()){
                                             Toast.makeText(register.this,"Registered Successfully. Please verify your email id.",Toast.LENGTH_SHORT).show();
                                             userId= mAuth.getCurrentUser().getUid();
                                             DocumentReference documentReference= fstore.collection("users").document(userId);
                                             Map<String,Object> user= new HashMap<>();
                                             user.put("name",name);
                                             user.put("email",email);
                                             user.put("company name",company);
                                             user.put("designation",designation);
                                             user.put("contact number",contact);
                                             documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                 @Override
                                                 public void onSuccess(Void unused) {
//                                                     Toast.makeText(register.this,"Saved.",Toast.LENGTH_SHORT).show();

                                                 }
                                             });

                                             Intent intent= new Intent(register.this,loginActivity.class);
                                             startActivity(intent);
                                             finish();
                                         }else{
                                             Toast.makeText(register.this,"Registration in loop failed",Toast.LENGTH_SHORT).show();
                                         }
                                        }
                                    });
                                }else{

                                    Toast.makeText(register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


    }
}