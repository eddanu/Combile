package com.android.herry.combile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUpVolunteerClickBtn(View v) {
        Intent intentSignUpVolunteer = new Intent(getBaseContext(), SignUpVolunteerActivity.class);
        startActivity(intentSignUpVolunteer);
    }

    public void signUpOrganizationClickBtn(View v) {
        Intent intentSignUpOrganization = new Intent(getBaseContext(), SignUpOrganizationActivity.class);
        startActivity(intentSignUpOrganization);
    }

}
