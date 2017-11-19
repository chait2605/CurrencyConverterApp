package com.android.currencyconverterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Boolean flagConverted = Boolean.FALSE;
    public static String currency;
    public static String amountFrom;
    public static String amountTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flagConverted) {
            TextView textView = (TextView) findViewById(R.id.textViewResult);
            textView.setText("Dollar amount $" + amountFrom + " converted to " + amountTo + " " + currency);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendBroadcast (View view) {
        Log.d("Debug","........Inside sendBraodCast method........");

        Intent intent = new Intent();
        intent.setAction("com.android.currencyconverterapp");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioButtonGroup);
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select you want to convert to", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButtonSelected = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        currency = (String) radioButtonSelected.getText();
        EditText editText = (EditText) findViewById(R.id.editTextAmount);
        amountFrom = editText.getText().toString();
        intent.putExtra("CURRENCY", currency);
        intent.putExtra("AMOUNT", amountFrom);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        sendBroadcast(intent);
    }

    public void closeApp(View v) {
        MainActivity.this.finish();
    }
}
