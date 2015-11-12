package com.ljw.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ljw on 2015/10/11.
 */
public class NetUtils {
    public static int getLocalIp() {
        WifiManager wifimanage=(WifiManager)Global.getAppContext().getSystemService(Context.WIFI_SERVICE);//获取WifiManager
        //检查wifi是否开启
        if(!wifimanage.isWifiEnabled())  {
            return 0;
        }
        WifiInfo wifiinfo= wifimanage.getConnectionInfo();
        if(wifiinfo != null)
            return wifiinfo.getIpAddress();
        else
            return 0;
    }
    public static InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = { (byte)(0xff & hostAddress),
                (byte)(0xff & (hostAddress >> 8)),
                (byte)(0xff & (hostAddress >> 16)),
                (byte)(0xff & (hostAddress >> 24)) };

        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
