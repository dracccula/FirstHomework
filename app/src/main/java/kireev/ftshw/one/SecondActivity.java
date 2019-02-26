package kireev.ftshw.one;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {

    private ImageButton red;
    private ImageButton yellow;
    private ImageButton green;
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        red = findViewById(R.id.RedButton);
        yellow = findViewById(R.id.YellowButton);
        green = findViewById(R.id.GreenButton);

        //слушаем красную кнопку
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redColor = "red";
                Intent intentService = new Intent(SecondActivity.this, MyService.class);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, redColor);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR, "Red button clicked!");
                startService(intentService);
                Log.d(LOG_TAG, "Color " + redColor + " chosen " + intentService.getAction());
                setResult(RESULT_OK, intentService);

            }
        });

        //слушаем желтую кнопку
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yellowColor = "yellow";
                Intent intentService = new Intent(SecondActivity.this, MyService.class);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, yellowColor);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR, "Yellow button clicked!");
                startService(intentService);
                Log.d(LOG_TAG, "Color " + yellowColor + " chosen");
                setResult(RESULT_OK, intentService);

            }
        });

        //слушаем зеленую кнопку
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greenColor = "green";
                Intent intentService = new Intent(SecondActivity.this, MyService.class);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, greenColor);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR, "Green button clicked!");
                startService(intentService);
                Log.d(LOG_TAG, "Color " + greenColor + " chosen");
                setResult(RESULT_OK, intentService);
            }
        });

        IntentFilter intentFilter = new IntentFilter(MyService.ACTION_MYINTENTSERVICE);
        BroadcastReceiver br = new MyBroadcastReceiver();
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(br, intentFilter);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LOG_TAG, "Color " + intent.getAction());
            SecondActivity.this.setResult(RESULT_OK, intent);
            SecondActivity.this.finish();
        }
    }
}
