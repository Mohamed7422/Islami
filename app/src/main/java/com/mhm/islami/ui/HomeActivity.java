package com.mhm.islami.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mhm.islami.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
                                  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            Fragment fragment = null;
            switch (id){
                case R.id.navigation_quraan: {
                   fragment = new QuraanFragment();
                    break;
                }
                case R.id.navigation_tasbeeh:{
                    fragment = new TasbeehFragment();
                    break;
                }
                case R.id.navigation_radio:{
                    fragment = new RadioFragment();
                    break;
                }
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_home);

        BottomNavigationView navView =  findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_quraan);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);

    }
}