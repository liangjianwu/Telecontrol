package com.ljw.controlservice;

import com.ljw.utils.CommonUtils;
import com.ljw.utils.Global;
import com.ljw.utils.NetUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import android.util.Log;

/**
 * Created by ljw on 2015/10/11.
 */
public class HeartBeatClient extends Thread{
    private HeartBeatServer heartBeatServer;
    public HeartBeatClient(HeartBeatServer hbs) {
        heartBeatServer = hbs;
    }
    public boolean sendCommand(String ip,int cmd) {
        byte[] data = (Commander.COMMAND_HEADER + ":" + cmd).getBytes();
        try {
            InetAddress addr = InetAddress.getByName(ip);
            DatagramPacket pack = new DatagramPacket(data,data.length,addr,Global.getHeartBeatPort());
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(pack);
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public void run() {
        DatagramPacket dataPacket = null;
        MulticastSocket multicastSocket = null;

        while(true) {
            heartBeatServer.clearClient();
            int ip = NetUtils.getLocalIp();
            if(ip != 0 ) {
                Global.setWifiConnected(true);
                try {
                    byte[] data = (Commander.HEARTBEAT_HEADER + ":" + CommonUtils.readCache("username")).getBytes();
                    multicastSocket = new MulticastSocket();
                    multicastSocket.setTimeToLive(4);
                    InetAddress address = InetAddress.getByName("224.0.0.1");
                    dataPacket = new DatagramPacket(data, data.length, address, Global.getHeartBeatPort());
                    multicastSocket.send(dataPacket);
                    multicastSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    this.sleep(Global.getHeartBeatSleepTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                Global.setWifiConnected(false);
                try {
                    this.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
