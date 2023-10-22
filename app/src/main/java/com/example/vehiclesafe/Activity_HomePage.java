package com.example.vehiclesafe;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vehiclesafe.databinding.ActivityHomePageBinding;

public class Activity_HomePage extends AppCompatActivity {

    ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find the ImageButton by its ID
        ImageButton electricBikeButton = findViewById(R.id.electricBikeButton);
        ImageButton permissionBtn = findViewById(R.id.permission);

        permissionBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Display a toast message when the ImageButton is clicked
                requestNotificationPolicyAccess();
                Toast.makeText(Activity_HomePage.this, "Permission Clicked", Toast.LENGTH_SHORT).show();
            }
        });
                // Set an OnClickListener for the ImageButton
        electricBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast message when the ImageButton is clicked
                enableDoNotDisturbMode();
                Toast.makeText(Activity_HomePage.this, "ImageButton Clicked", Toast.LENGTH_SHORT).show();
            }
        });
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
    private void enableDoNotDisturbMode() {
        // Get the NotificationManager service
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Check if the user has granted your app the permission to change DND mode
        if (isNotificationPolicyAccessGranted()) {
            // Activate Do Not Disturb mode
            if (notificationManager != null) {
                notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
                Toast.makeText(this, "Do Not Disturb mode enabled.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Ask the user to grant the permission manually
            Toast.makeText(this, "Please grant notification access permission manually.", Toast.LENGTH_LONG).show();
            requestNotificationPolicyAccess();
        }
    }
    private boolean isNotificationPolicyAccessGranted() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            NotificationManager.Policy policy = notificationManager.getNotificationPolicy();

            return policy != null && (policy.priorityCategories & NotificationManager.Policy.PRIORITY_CATEGORY_ALARMS) != 0;
        }
        return false;
    }
    private void requestNotificationPolicyAccess() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            // Handle the exception, such as showing an error message to the user
            Toast.makeText(this, "Unable to open notification policy settings.", Toast.LENGTH_SHORT).show();
        }
    }
}
