package com.android.herry.combile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void signInClickBtn(View v) {
        Intent intentSignIn = new Intent(getBaseContext(), SignInActivity.class);
        startActivity(intentSignIn);
    }

    public void signUpClickBtn(View v) {
        Intent intentSignUp = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(intentSignUp);
    }

}
