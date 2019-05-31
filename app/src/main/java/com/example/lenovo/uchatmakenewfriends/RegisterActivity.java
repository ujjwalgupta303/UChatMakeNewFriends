package com.example.lenovo.uchatmakenewfriends;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout DisplayName;
    private TextInputLayout Email;
    private TextInputLayout Password;
    private Button Register;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mToolbar=(Toolbar)findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayName= findViewById(R.id.regName);
        Email= findViewById(R.id.regName);
        Password= findViewById(R.id.regPassword);
        Register= findViewById(R.id.regButton);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String display_name= DisplayName.getEditText().getText().toString();
                String email= Email.getEditText().getText().toString();
                String password= Password.getEditText().getText().toString();

                register_user(display_name,email,password);



            }

        });
    }

    private void register_user(String display_name,String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();


                }else{
                    Toast.makeText(RegisterActivity.this,"You got an error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
