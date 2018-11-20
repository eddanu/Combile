package com.android.herry.combile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputEmail, textInputPassword;
    private String emailInput, passwordInput;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        textInputEmail = findViewById(R.id.email_addr_sigin);
        textInputPassword = findViewById(R.id.password_signin);

        progressBar = (ProgressBar) findViewById(R.id.signin_progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    private boolean validateEmail() {
        emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Email harus diisi");
            textInputPassword.setError(null);
            textInputEmail.requestFocus();
            return false;
        } else if (!Validation.EMAIL_PATTERN.matcher(emailInput).matches()) {
            textInputEmail.setError("Format email salah");
            textInputPassword.setError(null);
            textInputEmail.requestFocus();
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Password harus diisi");
            textInputEmail.setError(null);
            textInputPassword.requestFocus();
            return false;
        } else if (!Validation.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password minimal 8 karakter dan harus mengandung angka");
            textInputEmail.setError(null);
            textInputPassword.requestFocus();
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void userLogin() {
        if (!validateEmail()) {
            return;
        } else if (!validatePassword()) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(SignInActivity.this, UtamaActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                userLogin();
                break;
        }
    }
}
