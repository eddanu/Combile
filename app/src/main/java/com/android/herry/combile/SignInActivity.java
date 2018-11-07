package com.android.herry.combile;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;



public class SignInActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail, textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        textInputEmail = findViewById(R.id.email_addr_sigin);
        textInputPassword = findViewById(R.id.password_signin);

    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Email harus diisi");
            return false;
        } else if(!Validation.EMAIL_PATTERN.matcher(emailInput).matches()) {
            textInputEmail.setError("Format email salah");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String paswordInput = textInputPassword.getEditText().getText().toString().trim();

        if (paswordInput.isEmpty()) {
            textInputPassword.setError("Password harus diisi");
            return false;
        } else if (!Validation.PASSWORD_PATTERN.matcher(paswordInput).matches()) {
            textInputPassword.setError("Password minimal 8 karakter dan harus mengandung angka");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInputSignIn(View v) {
        if (!validateEmail()) {
            return;
        } else if (!validatePassword()) {
            return;
        }

        String input = "Email" + textInputEmail.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
