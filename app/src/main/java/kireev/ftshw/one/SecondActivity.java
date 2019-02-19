package kireev.ftshw.one;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static ProgressBar mProgressBar;
    private ImageButton red;
    private ImageButton yellow;
    private ImageButton green;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        red = findViewById(R.id.redButton);
        yellow = findViewById(R.id.yellowButton);
        green = findViewById(R.id.greenButton);
        mProgressBar = findViewById(R.id.circleProgressBar);
        clickColorButton(this);

        // Запускаем свой IntentService
        Intent intentMyIntentService = new Intent(this, MyIntentService.class);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void clickColorButton(SecondActivity view){
        MainActivity.text.setText("#4CAF50");
    }
}


