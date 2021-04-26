package com.tanmayandev.teamsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailBox , passwordBox ;
    TextView loginBtn , createBtn ;
    FirebaseAuth auth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance() ;

        emailBox = findViewById(R.id.emailBox) ;
        passwordBox = findViewById(R.id.pwdBox) ;

        loginBtn = findViewById(R.id.loginBtn) ;
        createBtn = findViewById(R.id.createBtn) ;

        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(LoginActivity.this , DashBoardActivity.class) ;
            startActivity(intent);
            finish();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailBox.getText().toString() ;
                String password = passwordBox.getText().toString() ;
                auth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this , DashBoardActivity.class) ;
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage()    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , SignUpActivity.class) ;
                startActivity(intent);
                finish();
            }
        });

    }
}