package com.ljw.controlservice;

/**
 * Created by ljw on 2015/10/7.
 */
public class Commander {
    public static final String HEARTBEAT_HEADER = "10";
    public static final String COMMAND_HEADER = "11";
    public static final int WHAT_HEARTBEAT = 10;
    public static final int WHAT_COMMAND = 11;
    public static final int WHAT_MSG = 12;
    public static final int CMD_OPEN163 = 101;
    public static final int CMD_MEDIA_PLAY = 1001;
    public static final int CMD_MEDIA_PAUSE = 1002;
    public static final int CMD_MEDIA_PLAY_PAUSE = 1000;
    public static final int CMD_MEDIA_NEXT = 1003;
    public static final int CMD_MEDIA_PREVIOUS = 1004;
    public static final int CMD_MEDIA_VOLUME_UP = 1005;
    public static final int CMD_MEDIA_VOLUME_DOWN = 1006;
    public static final int CMD_MEDIA_VOLUME_MUTE = 1007;


}
