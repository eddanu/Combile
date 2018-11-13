package com.android.herry.combile;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Pattern;


public class SignUpOrganizationActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_organization);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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

        textInputEmail = findViewById(R.id.email_addr_signup_organization);
        textInputPassword = findViewById(R.id.password_signup_organization);
        textInputName = findViewById(R.id.name_signup_organization);
        textInputNoHP = findViewById(R.id.mobile_number_signup_organization);
        textInputOrganizationDesc = findViewById(R.id.organization_desc_signup_organization);
        textInputOrganizationType = findViewById(R.id.organization_type_signup_organization);

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
        } else if (!Validation.EMAIL_PATTERN.matcher(emailInput).matches()) {
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
        } else if (!Validation.NOHP_PATTERN.matcher(noHpInput).matches()) {
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

    private boolean validateOrganizationDesc() {
        String organizationDescInput = textInputOrganizationDesc.getEditText().getText().toString().trim();

        if (organizationDescInput.isEmpty()) {
            textInputOrganizationDesc.setError("Deskripsi organisasi harus diisi");
            return false;
        } else if (organizationDescInput.length() > 50) {
            textInputOrganizationType.setError("Deskripsi terlalu panjang");
            return false;
        } else {
            textInputOrganizationDesc.setError(null);
            return true;
        }
    }

    private boolean validateOrganizationType() {
        String organizationTypeInput = textInputOrganizationType.getEditText().getText().toString().trim();

        if (organizationTypeInput.isEmpty() | organizationTypeInput.equals("Tipe Organisasi")) {
            textInputOrganizationType.setError("Tipe organisasi harus dipilih");
            return false;
        } else if (!organizationTypeInput.equals("Kesehatan") & !organizationTypeInput.equals("Kebersihan") &
                !organizationTypeInput.equals("Lain-lain")) {
            textInputOrganizationType.setError("Tipe organisasi salah");
            return false;
        } else {
            textInputOrganizationType.setError(null);
            return true;
        }
    }

    public void confirmInputSignUpOrganization(View v) {

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
        String input = "Email" + textInputName.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
