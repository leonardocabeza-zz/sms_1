package receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.myapplication2.app.R;

/**
 * Created by leonardo.cabeza on 27/05/2014.
 */
public class SendBroadcastReceiver extends BroadcastReceiver {

    private static final String ACTION_SMS_SENT = "SMS_SENT";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_SMS_SENT)) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, R.string.sent_sms,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, R.string.generic_failure,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(context, R.string.no_service,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(context, R.string.null_pdu,
                            Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context, R.string.radio_off,
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
