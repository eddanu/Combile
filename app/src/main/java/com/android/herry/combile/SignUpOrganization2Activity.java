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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpOrganization2Activity extends AppCompatActivity {

    String[] SPINNERLIST = {"Tipe Organisasi", "Kesehatan", "Kebersihan", "Lain-lain"};
    Spinner sp;
    TextView tv;
    ArrayAdapter<String> adapter;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +                 //at least 1 digit
                    "(?=.*[a-zA-Z])" +              //any letter
                    "(?=\\S+$)" +                   //no white space
                    ".{8,}" +                       //at least 6 characters
                    "$"
            );
    private TextInputLayout
            textInputOrganizationName,
            textInputOrganizationDesc,
            textInputOrganizationType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_organization2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sp = (Spinner) findViewById(R.id.sp);
        tv = (TextView) findViewById(R.id.organization_type_signup_et_organization2);
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
              //  validateOrganizationType();
            }
        });

        textInputOrganizationName = findViewById(R.id.organization_name_signup_organization2);
        textInputOrganizationDesc = findViewById(R.id.organization_desc_signup_organization2);
        textInputOrganizationType = findViewById(R.id.organization_type_signup_organization2);
    }

    private boolean validateOrganizationName() {
        String organizationNameInput = textInputOrganizationName.getEditText().getText().toString().trim();

        if (organizationNameInput.isEmpty()) {
            textInputOrganizationName.setError("Nama Organisasi harus diisi");
            return false;
        } else {
            textInputOrganizationName.setError(null);
            return true;
        }
    }

    private boolean validateOrganizationDesc() {
        String organizationDescInput = textInputOrganizationDesc.getEditText().getText().toString().trim();

        if (organizationDescInput.isEmpty()) {
            textInputOrganizationDesc.setError("Deskripsi organisasi harus diisi");
            return false;
        } else if (organizationDescInput.length()>50) {
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
        }else if(!organizationTypeInput.equals("Kesehatan") & !organizationTypeInput.equals("Kebersihan") &
                !organizationTypeInput.equals("Lain-lain")) {
            textInputOrganizationType.setError("Tipe organisasi salah");
            return false;
        }else {
            textInputOrganizationType.setError(null);
            return true;
        }
    }

    public void confirmInputSignUpOrganization2(View v) {
        if (!validateOrganizationName()) {
            return;
        } else if (!validateOrganizationDesc()) {
            return;
        } else if (!validateOrganizationType()) {
            return;
        }

        String input = "Email" + textInputOrganizationName.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}

