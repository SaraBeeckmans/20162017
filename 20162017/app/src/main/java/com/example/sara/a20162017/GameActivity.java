package com.example.sara.a20162017;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GameActivity extends AppCompatActivity {

//Button buttonplr1 = findViewById(R.id.btnPlr1);
//Button buttonplr2 = findViewById(R.id.bntPlr2);

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;


    String filename = "myfile";
    String fileContents = "Hello world!";
    FileOutputStream outputStream;
    //File file= new File(context.getFilesDir(), filename);



   static int count1 = 0;
   static int count2 = 0;
   static int Highscore = 0;
   static int temp = 0;

    private SharedPreferences mPreferences;
    private String mSharedPrefFile =
            "com.example.android.20162017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

        //file

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Restore preferences
       // Highscore = mPreferences.getInt(HIGHSCORE_KEY, 0);


        new CountDownTimer(5000, 1000) {
            TextView textWinner = (TextView) findViewById(R.id.txtWinner);
            public void onTick(long millisUntilFinished) {
                textWinner.setText("" + millisUntilFinished / 1000);

                if((millisUntilFinished/1000)<2){
                   findViewById(R.id.btnPlr1).setBackgroundColor(Color.RED);
                    findViewById(R.id.bntPlr2).setBackgroundColor(Color.RED);
                }

                //here you can have your logic to set text to edittext
            }




            public void onFinish() {
                if(count1>count2){
                    textWinner.setText("Player 1 wins!");
                    temp = count1;
                }
                else if(count1<count2){
                    textWinner.setText("Player 2 wins!");
                    temp=count2;
                }
                else if(count1==count2){
                    textWinner.setText("Tie!");
                    temp=count2;
                }

                if (temp>Highscore){
                    Highscore = temp;
                    Toast.makeText(getApplicationContext(),Integer.toString(Highscore),Toast.LENGTH_LONG).show();
                    sendNotification();
                }



                findViewById(R.id.btnPlr1).setBackgroundColor(Color.BLUE);
                findViewById(R.id.bntPlr2).setBackgroundColor(Color.BLUE);



            }

        }.start();


        createNotificationChannel();
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Highscore")
                .setContentText(Integer.toString(Highscore))
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        return notifyBuilder;

    }

    public void sendNotification() {
        getNotificationBuilder();
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
       // preferencesEditor.putInt(COUNT_KEY, Highscore);
        preferencesEditor.apply();
    }

    public void ClickBtn1(View view){
        count1++;
        //Toast.makeText(getApplicationContext(),Integer.toString(count1),Toast.LENGTH_LONG).show();
    }

    public void ClickBtn2(View view){
        count2++;
        //Toast.makeText(getApplicationContext(),Integer.toString(count2),Toast.LENGTH_LONG).show();
    }
}
