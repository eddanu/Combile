package com.android.herry.combile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Pattern;


public class SignUpOrganizationActivity extends AppCompatActivity implements View.OnClickListener {

    String[] SPINNERLIST = {"Tipe Organisasi", "Kesehatan", "Kebersihan", "Lain-lain"};
    Spinner sp;
    TextView tv;
    ArrayAdapter<String> adapter;

    private TextInputLayout
            textInputEmail,
            textInputPassword,
            textInputName,
            textInputNoHP,
            textInputOrganizationDesc,
            textInputOrganizationType;

    private String
            nameInput,
            emailInput,
            noHpInput,
            organizationDescInput,
            organizationTypeInput,
            passwordInput;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_organization);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        textInputEmail = findViewById(R.id.email_addr_signup_organization);
        textInputPassword = findViewById(R.id.password_signup_organization);
        textInputName = findViewById(R.id.name_signup_organization);
        textInputNoHP = findViewById(R.id.mobile_number_signup_organization);
        textInputOrganizationDesc = findViewById(R.id.organization_desc_signup_organization);
        textInputOrganizationType = findViewById(R.id.organization_type_signup_organization);


        mAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.signup_organization_progress_bar);

        findViewById(R.id.sign_up_organization_button).setOnClickListener(this);

        sp = (Spinner) findViewById(R.id.sp);
        tv = (TextView) findViewById(R.id.organization_type_signup_et_organization);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (0) {
                    case 0:
                        tv.setText(SPINNERLIST[i]);
                        break;
                    case 1:
                        tv.setText(SPINNERLIST[i]);
                        break;
                    case 2:
                        tv.setText(SPINNERLIST[i]);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private boolean validateName() {
        nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            textInputName.setError("Nama harus diisi");
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputName.requestFocus();
            return false;
        } else {
            textInputName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Email harus diisi");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);

            textInputEmail.requestFocus();
            return false;
        } else if (!Validation.EMAIL_PATTERN.matcher(emailInput).matches()) {
            textInputEmail.setError("Format email salah");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);

            textInputEmail.requestFocus();
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateNoHP() {
        noHpInput = textInputNoHP.getEditText().getText().toString().trim();

        if (noHpInput.isEmpty()) {
            textInputNoHP.setError("Nomor HP harus diisi");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputEmail.setError(null);

            textInputNoHP.requestFocus();
            return false;
        } else if (!Validation.NOHP_PATTERN.matcher(noHpInput).matches()) {
            textInputNoHP.setError("Nomor HP salah");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputEmail.setError(null);

            textInputNoHP.requestFocus();
            return false;
        } else {
            textInputNoHP.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Password harus diisi");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputPassword.requestFocus();
            return false;
        } else if (!Validation.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password minimal 8 karakter dan harus mengandung angka");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputPassword.requestFocus();
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    private boolean validateOrganizationDesc() {
        organizationDescInput = textInputOrganizationDesc.getEditText().getText().toString().trim();

        if (organizationDescInput.isEmpty()) {
            textInputOrganizationDesc.setError("Deskripsi organisasi harus diisi");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputOrganizationDesc.requestFocus();
            return false;
        } else if (organizationDescInput.length() > 50) {
            textInputOrganizationType.setError("Deskripsi terlalu panjang");
            textInputName.setError(null);
            textInputOrganizationType.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputOrganizationDesc.requestFocus();
            return false;
        } else {
            textInputOrganizationDesc.setError(null);
            return true;
        }
    }

    private boolean validateOrganizationType() {
        organizationTypeInput = textInputOrganizationType.getEditText().getText().toString().trim();

        if (organizationTypeInput.isEmpty() | organizationTypeInput.equals("Tipe Organisasi")) {
            textInputOrganizationType.setError("Tipe organisasi harus dipilih");
            textInputName.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputOrganizationType.requestFocus();
            return false;
        } else if (!organizationTypeInput.equals("Kesehatan") & !organizationTypeInput.equals("Kebersihan") &
                !organizationTypeInput.equals("Lain-lain")) {
            textInputOrganizationType.setError("Tipe organisasi salah");
            textInputName.setError(null);
            textInputOrganizationDesc.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputEmail.setError(null);

            textInputOrganizationType.requestFocus();
            return false;
        } else {
            textInputOrganizationType.setError(null);
            return true;
        }
    }

    public void signUpOrganization(){

        if (!validateName()) {
            return;
        } else if (!validateEmail()) {
            return;
        } else if (!validatePassword()) {
            return;
        } else if (!validateNoHP()) {
            return;
        } else if (!validateOrganizationDesc()) {
            return;
        } else if (!validateOrganizationType()) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailInput, passwordInput)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registrasi anda berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(SignUpOrganizationActivity.this, UtamaActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "Email anda sudah terdaftar", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up_organization_button:
                signUpOrganization();
                break;
        }
    }
}
