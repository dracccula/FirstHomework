package kireev.ftshw.one;


import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static kireev.ftshw.one.MainActivity.EXTRA_MESSAGE_CHOOSE_COLOR;
import static kireev.ftshw.one.MainActivity.EXTRA_MESSAGE_COLOR;
import static kireev.ftshw.one.MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR;

public class MyService extends IntentService {

    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    public static final String EXTRA_MESSAGE = "message";
    public static final String ACTION_MYINTENTSERVICE = "RESPONSE";
    final String LOG_TAG = "myLogs";
    public static final int NOTIFICATION_ID = 1;

    public MyService() {
        super("MyService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String messageText = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_COLOR);
        String messageTitle = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_NOTIFICATION_COLOR);
        String capMessageText = messageText.toUpperCase() + "!";
        showText(messageTitle, messageText);

        final Intent intent1 = new Intent(ACTION_MYINTENTSERVICE);
        intent1.putExtra(EXTRA_MESSAGE, capMessageText);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);
    }

    void showText(final String title, final String text) {
    /**
     * Создание построителя уведомлений
     */
        //noinspection deprecation
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(new long[] {0, 50})
            .setAutoCancel(true);

    /**
    * Создание построителя уведомлений
    */
    Intent actionIntent = new Intent(this, MainActivity.class);
    PendingIntent actionPendingIntent = PendingIntent.getActivity(
            this,
            0,
            actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    builder.setContentIntent(actionPendingIntent);

        /**
         * Выдача уведомления
         */
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

}