package com.example.saltmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import Fragment.CctvFragment;
import Fragment.ConfigFragment;
import Fragment.ControllFragment;
import Fragment.HomeFragment;
import Fragment.SensorFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nv;
    FrameLayout main_framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nv = findViewById(R.id.bottom_nv);
        main_framelayout = findViewById(R.id.main_framelayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,new HomeFragment()).commit();
        bottom_nv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.nv_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nv_controll:
                        fragment = new ControllFragment();
                        break;
                    case R.id.nv_sensor:
                        fragment = new SensorFragment();
                        break;
                    case R.id.nv_cctv:
                        fragment = new CctvFragment();
                        break;
                    case R.id.nv_config:
                        fragment = new ConfigFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,fragment).commit();
                return true;
            }
        });


    }
    public void changeFragment(Fragment frag,String arg){
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", arg);
        frag.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, frag).commit();
    }
}