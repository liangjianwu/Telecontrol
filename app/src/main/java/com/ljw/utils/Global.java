package com.ljw.utils;

import android.content.Context;

/**
 * Created by ljw on 2015/10/11.
 */
public class Global {
    private static int heart_beat_port = 10001;
    private static Context mContext = null;
    private static int heart_beat_sleep_time = 10000;
    private static boolean is_wifi_connected = false;

    public static int getHeartBeatPort() {
        return heart_beat_port;
    }
    public static int getHeartBeatSleepTime() {
        return heart_beat_sleep_time;
    }
    public static void setAppContext(Context app) {
        mContext = app;
    }
    public static Context getAppContext() {
        return mContext;
    }
    public static void setWifiConnected(boolean is) {
        is_wifi_connected = is;
    }
    public static boolean isWifiConnected() {
        return is_wifi_connected;
    }
}
