package com.ljw.controlservice;

import android.os.Handler;

import com.ljw.utils.Global;
import com.ljw.utils.MediaController;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ljw on 2015/10/11.
 */
public class HeartBeatServer extends Thread {
    private MulticastSocket multicastSocket = null;
    private final String multicastHost = "224.0.0.1";
    private InetAddress receiveAddress = null;
    private ArrayList<Client> clients = null;
    private Handler handler = null;
    public HeartBeatServer() {
        clients = new ArrayList<Client>();

    }
    public void setHandler(Handler hdl) {
        handler = hdl;
    }
    public ArrayList<Client> getClients() {
        return  clients;
    }
    public void clearClient() {
        for(int i=clients.size()-1;i>=0;i--) {
            if(clients.get(i).lastping< System.currentTimeMillis()-15000) {
                clients.remove(i);
            }
        }
    }
    private Client getClient(String ip) {
        for(int i=0;i<clients.size();i++) {
            if(clients.get(i).ip.equalsIgnoreCase(ip)) {
                return clients.get(i);
            }
        }
        return null;
    }
    @Override
    public void run() {
        byte buf[] = new byte[128];
        DatagramPacket dp = new DatagramPacket(buf, 128);
        try {
            multicastSocket = new MulticastSocket(Global.getHeartBeatPort());
            receiveAddress = InetAddress.getByName(multicastHost);
            multicastSocket.joinGroup(receiveAddress);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        while (true) {
            try {
                multicastSocket.receive(dp);
                String ip = dp.getAddress().getHostAddress();
                String data = new String( dp.getData() ).trim();
                if( data.length() == 0 || !data.contains(":") ) continue;
                String[] arr = data.split(":");
                if( arr[0].equalsIgnoreCase(Commander.HEARTBEAT_HEADER) ) {
                    Client cl = getClient(ip);
                    if (cl == null){
                        cl = new Client();
                        cl.ip = ip;
                        cl.username = data.substring(arr[0].length() + 1);
                        cl.lastping = System.currentTimeMillis();
                        cl.received = false;
                        clients.add(cl);
                    } else {
                        cl.lastping = System.currentTimeMillis();
                    }
                    if(handler != null) handler.sendEmptyMessage(Commander.WHAT_HEARTBEAT);
                }else if(arr[0].equalsIgnoreCase(Commander.COMMAND_HEADER)) {
                    Client cl = getClient(ip);
                    if (cl != null){
                        //if(cl.received) {
                            MediaController.excute(Integer.parseInt(arr[1]));
                        //}
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public class Client {
        public String ip;
        public String username;
        public boolean received;
        public long lastping;
    }
}

