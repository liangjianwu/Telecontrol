package com.ljw.utils;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * Created by ljw on 2015/10/7.
 */
public class MediaController {
    public static void PlayPause(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setPackage("com.netease.cloudmusic");
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);

    }
    public static void Play(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MEDIA_PLAY);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setPackage("com.netease.cloudmusic");
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PLAY);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);

    }
    public static void Pause(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MEDIA_PAUSE);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setPackage("com.netease.cloudmusic");
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PAUSE);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }
    public static void Next(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MEDIA_NEXT);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setPackage("com.netease.cloudmusic");
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_NEXT);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }
    public static void Previous(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MEDIA_PREVIOUS);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent.setPackage("com.netease.cloudmusic");
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MEDIA_PREVIOUS);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }
    public static void VolumeUp(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_VOLUME_UP);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent.setPackage("com.netease.cloudmusic");
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_VOLUME_UP);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }
    public static void VolumeDown(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_VOLUME_DOWN);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent.setPackage("com.netease.cloudmusic");
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_VOLUME_DOWN);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }
    public static void VolumeMute(Context ctxt) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent1.setPackage("com.netease.cloudmusic");
        KeyEvent ke1 = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_VOLUME_MUTE);
        intent1.putExtra(Intent.EXTRA_KEY_EVENT,ke1);
        ctxt.sendBroadcast(intent1);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_BUTTON);
        intent.setPackage("com.netease.cloudmusic");
        KeyEvent ke = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_VOLUME_MUTE);
        intent.putExtra(Intent.EXTRA_KEY_EVENT,ke);
        ctxt.sendBroadcast(intent);
    }

    public static void excute(int code) {

    }
}
