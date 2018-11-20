package com.android.herry.combile;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UtamaActivity extends AppCompatActivity {

    android.support.v4.app.FragmentManager manage=getSupportFragmentManager();
    private TextView mTextMessage;
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private ArrayAdapter<String> stringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        manage.beginTransaction().replace(R.id.Frame_main,new FragmentHome()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_menu:
                    manage.beginTransaction().replace(R.id.Frame_main,new FragmentHome()).commit();
                    return true;
//*                case R.id.history_menu:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    manage.beginTransaction().replace(R.id.Frame_main,new fragment_history()).commit();
//                    return true;
//                case R.id.scheduler_menu:
//                    mTextMessage.setText(R.string.title_notifications);
//                    manage.beginTransaction().replace(R.id.Frame_main,new fragment_scheduler()).commit();
//                    return true;
//                case R.id.favorite_menu:
//                    manage.beginTransaction().replace(R.id.Frame_main,new fragment_favorite()).commit();
//                    return true;
//                case R.id.account_menu:
//                    manage.beginTransaction().replace(R.id.Frame_main,new fragment_account()).commit();
//                    return true;
            }
            return false;
        }
    };
}
