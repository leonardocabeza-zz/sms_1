package ve.com.leonardocabeza.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ve.com.leonardocabeza.R;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button sendSmsButton = (Button) findViewById(R.id.button);
		final EditText number = (EditText) findViewById(R.id.editText2);
		final EditText msg = (EditText) findViewById(R.id.editText3);

        sendSmsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PendingIntent msgSent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("SMS_SENT"), PendingIntent.FLAG_UPDATE_CURRENT);
				PendingIntent msgDelivered = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("SMS_DELIVERED"), 0);
				SmsManager smsManager = SmsManager.getDefault();

				try {
					if (number.length() > 0 && msg.length() > 0) {
						if (msg.length() > 160) {
							ArrayList<String> messageList = smsManager.divideMessage(msg.getText().toString());
							smsManager.sendMultipartTextMessage(number.getText().toString(), null, messageList, null, null);
						} else {
							smsManager.sendTextMessage(number.getText().toString(), null, msg.getText().toString(), msgSent, msgDelivered);
						}
					}
				} catch (Exception e) {
					Log.d(MainActivity.class.getSimpleName(), "Error sending SMS: " + e.getMessage());
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return id == R.id.action_settings || super.onOptionsItemSelected(item);
	}
}
