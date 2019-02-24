package kireev.ftshw.one;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
        final Intent intentService = new Intent(this, MyService.class);
        startService(new Intent(this, MyService.class));

        //слушаем красную кнопку
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redColor = "RED!";
                //Intent intent = new Intent();
                //Intent intentService = new Intent(SecondActivity.this, MyService.class);
                BroadcastReceiver br = new MyBR();
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, redColor);
                intentService.putExtra(MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR, redColor);
                br.onReceive(SecondActivity.this, intentService);
                Log.d(LOG_TAG, "Color " + redColor + " chosen" + intentService.getAction());
                setResult(RESULT_OK, intentService);
                finish();
            }
        });

        //слушаем желтую кнопку
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yellowColor = "YELLOW!";
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, yellowColor);
                Log.d(LOG_TAG, "Color " + yellowColor + " chosen");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //слушаем зеленую кнопку
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greenColor = "GREEN!";
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_MESSAGE_COLOR, greenColor);
                Log.d(LOG_TAG, "Color " + greenColor + " chosen");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(MyService.EXTRA_KEY_OUT);
        }
    }
}
