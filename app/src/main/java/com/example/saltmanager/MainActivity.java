package com.example.saltmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nv;
    FrameLayout main_framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nv = findViewById(R.id.bottom_nv);
        main_framelayout = findViewById(R.id.main_framelayout);
        bottom_nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nv_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new HomeFragment()).commit();
                        Toast.makeText(getApplicationContext(),"첫번째",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nv_controll:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new ControllFragment()).commit();
                        Toast.makeText(getApplicationContext(),"첫번째",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nv_sensor:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new SensorFragment()).commit();
                        Toast.makeText(getApplicationContext(),"첫번째",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nv_cctv:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new CctvFragment()).commit();
                        Toast.makeText(getApplicationContext(),"첫번째",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nv_config:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new ConfigFragment()).commit();
                        Toast.makeText(getApplicationContext(),"첫번째",Toast.LENGTH_SHORT).show();
                        break;

                }

                return true;
            }
        });


    }
}