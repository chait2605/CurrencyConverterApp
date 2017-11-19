package com.android.currencyexcahnger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by SRIKUCHAITU on 9/24/2017.
 */

public class ExchangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.android.currencyconverterapp")) {
            String message1 = intent.getExtras().getString("CURRENCY");
            String message2 = intent.getExtras().getString("AMOUNT");
            PackageManager pm = context.getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage("com.android.currencyexcahnger");
            launchIntent.putExtra("CURRENCY", message1);
            launchIntent.putExtra("AMOUNT", message2);
            context.startActivity(launchIntent);
        }
    }
}