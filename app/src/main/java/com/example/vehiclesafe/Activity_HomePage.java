package com.example.vehiclesafe;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vehiclesafe.databinding.ActivityHomePageBinding;

public class Activity_HomePage extends AppCompatActivity {

    ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNavView.setBackground(null);

        binding.bottomNavView.setOnItemSelectedListener(item->{
            int id = item.getItemId();
            switch(id) {
                case R.id.navigation_home:
                    break;

                case R.id.navigation_logs:
                    Intent intent1 = new Intent(Activity_HomePage.this, Activity_CallLogs.class);

                    startActivity(intent1);
                    finish();
                    break;

                case R.id.navigation_emergency:
                    Intent intent2 = new Intent(Activity_HomePage.this, Activity_Contacts.class);

                    startActivity(intent2);
                    finish();
                    break;

                default:

            }
            return true;
        });
    }
}
