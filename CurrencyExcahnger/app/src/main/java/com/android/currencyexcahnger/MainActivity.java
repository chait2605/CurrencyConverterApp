package com.android.currencyexcahnger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String currency;
    public static String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        currency = getIntent().getExtras().getString("CURRENCY");
        amount = getIntent().getExtras().getString("AMOUNT");
        TextView tv = (TextView)findViewById(R.id.textViewAmount);
        tv.setText("Dollar Amount: $" + amount);
        tv = (TextView)findViewById(R.id.textViewCurrency);
        tv.setText("Convert To: " + currency);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendBroadcastReply (View view) {
        Log.d("Debug","........Indside sendBroadcastReply method......");
        Double amt = Double.parseDouble(amount);
        Double tgtamt = 0.0;
        if (currency.equals("Indian Rupee")) tgtamt = amt*65.0;
        else if (currency.equals("British Pound")) tgtamt = amt*0.74;
        else tgtamt = amt*112.0;
        String amountConverted = tgtamt.toString();
        Log.d("Debug","Converted amount is " + amountConverted);
        Intent intent = new Intent();
        intent.setAction("com.android.currencyexcahnger");
        intent.putExtra("CURRENCY", currency);
        intent.putExtra("AMOUNT", amountConverted);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        finish();
    }

    public void closeApp(View v) {
        MainActivity.this.finish();
    }
}
