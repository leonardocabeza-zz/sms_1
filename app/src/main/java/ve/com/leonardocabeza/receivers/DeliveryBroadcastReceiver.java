package ve.com.leonardocabeza.receivers;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import ve.com.leonardocabeza.R;
import ve.com.leonardocabeza.activities.MainActivity;

/**
 * Created by leonardo.cabeza on 27/05/2014.
 */
public class DeliveryBroadcastReceiver extends BroadcastReceiver {

    private static final String ACTION_SMS_DELIVERED = "SMS_DELIVERED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SMS_DELIVERED)) {
            CharSequence notification_title = "";
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setAutoCancel(true);

            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    notification_title = context.getString(R.string.delivered_sms);
                    break;
                case Activity.RESULT_CANCELED:
                    notification_title = context.getString(R.string.not_delivered_sms);
                    break;
            }

            mBuilder.setContentTitle(notification_title);
            Intent activityIntent = new Intent(context, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(activityIntent);
            PendingIntent activityPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(activityPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
        }
    }
}
