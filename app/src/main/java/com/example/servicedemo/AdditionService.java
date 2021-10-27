package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdditionService extends Service {

    ScheduledExecutorService executorService;
    public final IBinder binder = new Binder();

    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        showAddition();
        return binder;
    }

    private void showAddition() {
        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int a = 2+2;
                Log.e("Addition of 2 + 2 is ", String.valueOf(a));
            }
            //1st '50' is the initlization time and 2nd '50' is interval between next calling
        },50,50, TimeUnit.MINUTES);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        executorService.shutdown();
        return super.onUnbind(intent);
    }

}