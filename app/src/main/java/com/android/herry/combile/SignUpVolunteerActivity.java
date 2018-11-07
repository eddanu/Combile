package com.android.herry.combile;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpVolunteerActivity extends AppCompatActivity {

    private static final String TAG = "SignUpVolunteerActivity";
    private TextView mDisplayDate, mChooseDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +                 //at least 1 digit
                    "(?=.*[a-zA-Z])" +              //any letter
                    "(?=\\S+$)" +                   //no white space
                    ".{8,}" +                       //at least 6 characters
                    "$"
            );
    private TextInputLayout
            textInputEmail,
            textInputPassword,
            textInputName,
            textInputDOB,
            textInputNoHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_volunteer);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mDisplayDate = (EditText) findViewById(R.id.dob_signup_et_volunteer);
        mChooseDate = (Button) findViewById(R.id.btn_dob_signup_volunteer);

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

                if (day <= 9 & month >= 10){
                    date = "0" + day + "-" + month + "-" + year;
                }
                else if (month <= 9 & day >=10) {
                    date = day + "-" + "0" + month + "-" + year;
                }else if (month <= 9 & day <=9){
                    date = "0" + day + "-" + "0" + month + "-" + year;
                }
                else {
                    date = day + "-" + month  + "-" + year;
                }


                mDisplayDate.setText(date);
            }
        };

        textInputEmail = findViewById(R.id.email_addr_signup_volunteer);
        textInputPassword = findViewById(R.id.password_signup_volunteer);
        textInputName = findViewById(R.id.name_signup_volunteer);
        textInputDOB = findViewById(R.id.dob_signup_volunteer);
        textInputNoHP = findViewById(R.id.mobile_number_signup_volunteer);
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
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateDOB() {
        String dobInput = textInputDOB.getEditText().getText().toString().trim();

        if (dobInput.isEmpty()) {
            textInputDOB.setError("Tanggal lahir harus diisi");
            return false;
       // } else if (Validation.DOB_PATTERN.matcher(textInputDOB).matches()){
          //  textInputDOB.setError("Format tanggal salah");
          //..  return false;
        } else {
            textInputDOB.setError(null);
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
        } else if (!PASSWORD_PATTERN.matcher(paswordInput).matches()) {
            textInputPassword.setError("Password minimal 8 karakter dan harus mengandung angka");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInputSignUpVolunteer(View v) {
        if (!validateName()) {
            return;
        } else if (!validateEmail()) {
            return;
        } else if (!validateDOB()) {
            return;
        }else if (!validateNoHP()) {
            return;
        }else if (!validatePassword()) {
            return;
        }

        String input = "Email" + textInputEmail.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
