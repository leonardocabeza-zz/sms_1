package receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication2.app.R;

/**
 * Created by leonardo.cabeza on 27/05/2014.
 */
public class DeliveryBroadcastReceiver extends BroadcastReceiver {

    private static final String ACTION_SMS_DELIVERED = "SMS_DELIVERED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SMS_DELIVERED)) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, R.string.delivered_sms,
                            Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, R.string.not_delivered_sms,
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
