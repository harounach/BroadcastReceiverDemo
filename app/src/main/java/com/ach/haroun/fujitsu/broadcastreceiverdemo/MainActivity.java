package com.ach.haroun.fujitsu.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView mImageView;
    IntentFilter mChargingFilter;
    ChargingBroadcastReceiver mChargingReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.image_view_id);
        IntentFilter mChargingFilter;
        mChargingFilter = new IntentFilter();
        mChargingFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        mChargingFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        mChargingReceiver = new ChargingBroadcastReceiver();
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mChargingReceiver, mChargingFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mChargingReceiver);
    }
    public void showCharging(boolean isCharging){
        if(isCharging){
            Drawable drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_battery_charging_50_black_24dp, null);
            drawable = DrawableCompat.wrap(drawable);
            mImageView.setImageDrawable(drawable);
        }else{
            Drawable drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_battery_alert_black_24dp, null);
            drawable = DrawableCompat.wrap(drawable);
            mImageView.setImageDrawable(drawable);
        }
    }
    private class ChargingBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean isCharging = action.equals(Intent.ACTION_POWER_CONNECTED);
            showCharging(isCharging);
        }
    }
}
