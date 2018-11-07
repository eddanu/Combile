package com.android.herry.combile;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.regex.Pattern;


public class SignUpOrganization1Activity extends AppCompatActivity {

    private TextInputLayout
            textInputEmail,
            textInputPassword,
            textInputName,
            textInputNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_organization1);

        textInputEmail = findViewById(R.id.email_addr_signup_organization1);
        textInputPassword = findViewById(R.id.password_signup_organization1);
        textInputName = findViewById(R.id.pendaftar_name_signup_organization1);
        textInputNoHP = findViewById(R.id.mobile_number_signup_organization1);

    }

    private boolean validateName() {
        String nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            textInputName.setError("Nama harus diisi");
            return false;
        } else {
            textInputName.setError(null);
            return true;
        }
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

    private boolean validateNoHP() {
        String noHpInput = textInputNoHP.getEditText().getText().toString().trim();

        if (noHpInput.isEmpty()) {
            textInputNoHP.setError("Nomor HP harus diisi");
            return false;
        } else if (!Validation.NOHP_PATTERN.matcher(noHpInput).matches()){
            textInputNoHP.setError("Nomor HP salah");
            return false;
        } else {
            textInputNoHP.setError(null);
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

    public void confirmInputSignUpOrganization1(View v){
        Intent intentSignUpOrganization2 = new Intent(getBaseContext(),SignUpOrganization2Activity.class);

        if (!validateName()) {
            return;
        } else if (!validateEmail()) {
            return;
        }else if (!validateNoHP()) {
            return;
        }else if (!validatePassword()) {
            return;
        }
        else{
            startActivity(intentSignUpOrganization2);
        }
    }
}
