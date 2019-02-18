package kireev.ftshw.one;

import java.util.concurrent.TimeUnit;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private final String TAG = "IntentServiceLogs";

    public static final String ACTION_MYINTENTSERVICE = "kireev.ftshw.one.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    String extraOut = "Кота накормили, погладили и поиграли с ним";

    public static final String ACTION_UPDATE = "kireev.ftshw.one.UPDATE";
    public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";

    public MyIntentService() {
        super("myname");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // посылаем промежуточные данные
            Intent updateIntent = new Intent();
            updateIntent.setAction(ACTION_UPDATE);
            updateIntent.addCategory(Intent.CATEGORY_DEFAULT);
            updateIntent.putExtra(EXTRA_KEY_UPDATE, i);
            sendBroadcast(updateIntent);
        }

        // возвращаем результат
        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra(EXTRA_KEY_OUT, extraOut);
        sendBroadcast(responseIntent);
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

}