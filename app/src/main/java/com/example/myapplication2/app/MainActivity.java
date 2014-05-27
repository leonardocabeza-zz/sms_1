package com.example.myapplication2.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonEnviarSms = (Button) findViewById(R.id.button);
        final EditText nro = (EditText) findViewById(R.id.editText2);
        final EditText msj = (EditText) findViewById(R.id.editText);

        botonEnviarSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent estadoMsj = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent("SMS_SENT"), 0);
                SmsManager smsManager = SmsManager.getDefault();
                //test1

                registerReceiver(new BroadcastReceiver(){
                    @Override
                    public void onReceive(Context arg0, Intent arg1) {
                        switch (getResultCode())
                        {
                            case Activity.RESULT_OK:
                                Toast.makeText(MainActivity.this, "Mensaje Enviado",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(MainActivity.this, "Falla genérica",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                Toast.makeText(MainActivity.this, "Sin servicio",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(MainActivity.this, "PDU Nulo",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(getBaseContext(), "Radio apagada",
                                        Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }, new IntentFilter("SMS_SENT"));

                try {
                    if (nro.length() > 0 && msj.length() > 0) {
                        smsManager.sendTextMessage(nro.getText().toString(),null,msj.getText().toString(),estadoMsj,null);
                    }
                } catch (Exception e) {
                    Log.d(MainActivity.class.getSimpleName(), "Error al enviar mensaje: " + e.getMessage());
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
