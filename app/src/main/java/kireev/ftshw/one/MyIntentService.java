package kireev.ftshw.one;

import android.app.IntentService;
import android.content.Intent;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("myname");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        for (int i = 0; i <= 10000; i++) {
            int iRandomNumber = 1 + (int) (Math.random() * 10);
            // посылаем промежуточные данные
            Intent updateIntent = new Intent();
            sendBroadcast(updateIntent);
        }
        // возвращаем результат
        Intent responseIntent = new Intent();

        sendBroadcast(responseIntent);


    }
}