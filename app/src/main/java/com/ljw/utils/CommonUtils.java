package com.ljw.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.KeyEvent;

import java.util.List;

/**
 * Created by ljw on 2015/10/7.
 */
public class CommonUtils {
    public static void startApp(Context ctxt,String packageName) {

        PackageInfo packageInfo = null;
        try {
            packageInfo = ctxt.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo==null) {
                System.out.println("packageInfo==null");
            } else {
                System.out.println("packageInfo!=null");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //<data android:scheme="app" android:host="jp.co.cybird.barcodefootballer/" />

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        //resolveIntent.setData(Uri.parse("app://jp.co.cybird.barcodefootballer/"));
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageInfo.packageName);
        System.out.println("packageInfo.packageName=" + packageInfo.packageName);

        List<ResolveInfo> resolveInfoList =
                ctxt.getPackageManager().queryIntentActivities(resolveIntent, 0);

        System.out.println("resolveInfoList.size()=" + resolveInfoList.size());

        ResolveInfo resolveInfo = resolveInfoList.iterator().next();
        if (resolveInfo != null ) {
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName componentName = new ComponentName(activityPackageName, className);

            intent.setComponent(componentName);
            ctxt.startActivity(intent);
        }

    }

    public static String readCache(String key) {
        return "123";
    }
    public static void writeCache(String key,String value) {

    }

}
