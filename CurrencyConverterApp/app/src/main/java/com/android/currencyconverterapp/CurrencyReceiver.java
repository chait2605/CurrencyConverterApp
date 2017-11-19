package com.android.currencyconverterapp;

/**
 * Created by SRIKUCHAITU on 9/24/2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CurrencyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.android.currencyexcahnger")) {
            MainActivity.currency = intent.getExtras().getString("CURRENCY");
            MainActivity.amountTo = intent.getExtras().getString("AMOUNT");
            MainActivity.flagConverted = true;
        }
    }
}