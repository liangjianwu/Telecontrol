package com.ljw.controlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class ControlService extends Service {
    private static final String TAG = "ControlService";
    private ControlBinder mBinder = null;
    private HeartBeatServer hbs = null;
    private HeartBeatClient hbc = null;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG, "bind onBind~~~");
        return mBinder;
    }
    @Override
    public void onCreate() {
        Log.e(TAG, "start onCreate~~~");
        super.onCreate();
        mBinder = new ControlBinder();
        hbs = new HeartBeatServer();
        hbc = new HeartBeatClient(hbs);
        hbs.start();
        hbc.start();
    }
    public boolean sendCmd(String ip,int cmd) {
        hbc.sendCommand(ip, cmd);
        return false;
    }

    public ArrayList<HeartBeatServer.Client> getClients() {
        if( hbs != null )
            return hbs.getClients();
        else
            return null;
    }
    public void setHandler(Handler hdl) {
        if(hbs != null)
            hbs.setHandler(hdl);
    }
    @Override
    public void onDestroy() {
        Log.e(TAG, "start onDestroy~~~");
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "start onUnbind~~~");
        return super.onUnbind(intent);
    }

    public class ControlBinder extends Binder {
        public ControlService getService() {
            return ControlService.this;
        }
    }
}
