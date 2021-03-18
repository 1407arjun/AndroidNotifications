package com.example.notificationmodule;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
    }
    public void createNotification (View view) {

        EditText day = findViewById(R.id.day);
        EditText hour = findViewById(R.id.hour);
        EditText minute = findViewById(R.id.minute);
        EditText second = findViewById(R.id.second);

        if (day.getText().toString().isEmpty() || hour.getText().toString().isEmpty() || minute.getText().toString().isEmpty() || second.getText().toString().isEmpty()) {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Reminder set", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day.getText().toString()));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.getText().toString()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minute.getText().toString()));
            calendar.set(Calendar.SECOND, Integer.parseInt(second.getText().toString()));

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ReminderChannel";
            String description = "Channel for reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyUs", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}