package com.android.herry.combile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpVolunteerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SignUpVolunteerActivity";
    private TextView mDisplayDate, mChooseDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextInputLayout
            textInputEmail,
            textInputPassword,
            textInputName,
            textInputDOB,
            textInputNoHP;

    private String
            nameInput,
            emailInput,
            dobInput,
            noHpInput,
            passwordInput;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_volunteer);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mDisplayDate = (EditText) findViewById(R.id.dob_signup_et_volunteer);
        mChooseDate = (Button) findViewById(R.id.btn_dob_signup_volunteer);
        textInputEmail = findViewById(R.id.email_addr_signup_volunteer);
        textInputPassword = findViewById(R.id.password_signup_volunteer);
        textInputName = findViewById(R.id.name_signup_volunteer);
        textInputDOB = findViewById(R.id.dob_signup_volunteer);
        textInputNoHP = findViewById(R.id.mobile_number_signup_volunteer);

        mAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.signup_volunteer_progress_bar);

        findViewById(R.id.sign_up_volunteer_button).setOnClickListener(this);

        mChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpVolunteerActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

                String date = "mm-dd-yyyy";

                if (day <= 9 & month >= 10) {
                    date = "0" + day + "-" + month + "-" + year;
                } else if (month <= 9 & day >= 10) {
                    date = day + "-" + "0" + month + "-" + year;
                } else if (month <= 9 & day <= 9) {
                    date = "0" + day + "-" + "0" + month + "-" + year;
                } else {
                    date = day + "-" + month + "-" + year;
                }

                mDisplayDate.setText(date);
            }
        };
    }

    private boolean validateName() {
        nameInput = textInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            textInputName.setError("Nama harus diisi");
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputDOB.setError(null);
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
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputDOB.setError(null);

            textInputEmail.requestFocus();
            return false;
        } else if (!Validation.EMAIL_PATTERN.matcher(emailInput).matches()) {
            textInputEmail.setError("Format email salah");
            textInputName.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);
            textInputDOB.setError(null);

            textInputEmail.requestFocus();
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateDOB() {
        dobInput = textInputDOB.getEditText().getText().toString().trim();

        if (dobInput.isEmpty()) {
            textInputDOB.setError("Tanggal lahir harus diisi");
            textInputEmail.setError(null);
            textInputName.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);

            textInputDOB.requestFocus();
            return false;
        } else if (!Validation.DOB_PATTERN.matcher(dobInput).matches()) {
            textInputDOB.setError("Format tanggal salah");
            textInputEmail.setError(null);
            textInputName.setError(null);
            textInputPassword.setError(null);
            textInputNoHP.setError(null);

            textInputDOB.requestFocus();
            return false;
        } else {
            textInputDOB.setError(null);
            return true;
        }
    }

    private boolean validateNoHP() {
        noHpInput = textInputNoHP.getEditText().getText().toString().trim();

        if (noHpInput.isEmpty()) {
            textInputNoHP.setError("Nomor HP harus diisi");
            textInputDOB.setError(null);
            textInputEmail.setError(null);
            textInputName.setError(null);
            textInputPassword.setError(null);

            textInputNoHP.requestFocus();
            return false;
        } else if (!Validation.NOHP_PATTERN.matcher(noHpInput).matches()) {
            textInputNoHP.setError("Nomor HP salah");
            textInputDOB.setError(null);
            textInputEmail.setError(null);
            textInputName.setError(null);
            textInputPassword.setError(null);

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
            textInputNoHP.setError(null);
            textInputDOB.setError(null);
            textInputEmail.setError(null);
            textInputName.setError(null);

            textInputPassword.requestFocus();
            return false;
        } else if (!Validation.PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password minimal 8 karakter dan harus mengandung angka");
            textInputNoHP.setError(null);
            textInputDOB.setError(null);
            textInputEmail.setError(null);
            textInputName.setError(null);

            textInputPassword.requestFocus();
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void signUpVolunteer() {
        if (!validateName()) {
            return;
        } else if (!validateEmail()) {
            return;
        } else if (!validateDOB()) {
            return;
        } else if (!validateNoHP()) {
            return;
        } else if (!validatePassword()) {
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
                            Intent intent = new Intent(SignUpVolunteerActivity.this, UtamaActivity.class);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_volunteer_button:
                signUpVolunteer();
                break;
        }
    }
}
