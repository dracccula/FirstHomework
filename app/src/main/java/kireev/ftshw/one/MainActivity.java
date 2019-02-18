package kireev.ftshw.one;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoTextView;
    private ProgressBar mProgressBar;
    private MyBroadcastReceiver mMyBroadcastReceiver;
    private UpdateBroadcastReceiver mUpdateBroadcastReceiver;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfoTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressbar);
        button = findViewById(R.id.launchSecondActivityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //открываем активити
                openActivity2();
            }
        });

        // Запускаем свой IntentService
        Intent intentMyIntentService = new Intent(this, MyIntentService.class);

        startService(intentMyIntentService.putExtra("time", 3).putExtra("task","Погладить кота"));
        startService(intentMyIntentService.putExtra("time", 1).putExtra("task","Покормить кота"));
        //startService(intentMyIntentService.putExtra("time", 4).putExtra("task","Поиграть с котом"));

        mMyBroadcastReceiver = new MyBroadcastReceiver();
        mUpdateBroadcastReceiver = new UpdateBroadcastReceiver();

        // регистрируем BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(
                MyIntentService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyBroadcastReceiver, intentFilter);

        // Регистрируем второй приёмник
        IntentFilter updateIntentFilter = new IntentFilter(
                MyIntentService.ACTION_UPDATE);
        updateIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mUpdateBroadcastReceiver, updateIntentFilter);

    }

    private void openActivity2() {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadcastReceiver);
        unregisterReceiver(mUpdateBroadcastReceiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent
                    .getStringExtra(MyIntentService.EXTRA_KEY_OUT);
            mInfoTextView.setText(result);
        }
    }

    public class UpdateBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int update = intent
                    .getIntExtra(MyIntentService.EXTRA_KEY_UPDATE, 0);
            mProgressBar.setProgress(update);
        }
    }
}