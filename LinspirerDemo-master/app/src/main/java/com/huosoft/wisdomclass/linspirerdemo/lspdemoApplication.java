package com.huosoft.wisdomclass.linspirerdemo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

import com.ljlVink.Activity.NewUI;
import com.ljlVink.utils.DataCleanManager;
import com.ljlVink.utils.FileUtils;
import com.ljlVink.utils.Sysutils;
import com.ljlVink.utils.Toast;
import com.ljlVink.utils.Signutil;

import java.lang.reflect.Field;

public class lspdemoApplication extends Application {
    private static lspdemoApplication mApplication;
    public  static String a;
    public  static String d="e";
    public  static String b="r";
    public static String c="u";


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        FileUtils.getInstance(this).copyAssetsToSD("apk","lspdemo.apks");
        DataCleanManager.clearAllCache(this);
        String truePMName = "android.content.pm.IPackageManager$Stub$Proxy";
        String nowPMName = "";
        String finger= Build.FINGERPRINT;
        String romname= Sysutils.getDevice();
        try {
            PackageManager packageManager = this.getPackageManager();
            Field mPMField = packageManager.getClass().getDeclaredField("mPM");
            mPMField.setAccessible(true);
            Object mPM = mPMField.get(packageManager);
            assert mPM != null;
            nowPMName = mPM.getClass().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int keystatus = new Signutil(this, "97:8D:89:23:F9:F3:AF:C9:A3:79:37:2C:C8:A6:FF:A8:26:CC:DE:EF").f();

        if (!this.getApplicationInfo().name.equals("com.huosoft.wisdomclass.linspirerdemo.lspdemoApplication")){
            throw new RuntimeException("?!! detect!");
        }
        a="t";
        a+=b;
        if(!nowPMName.equals(truePMName)){
            throw new RuntimeException("?!! detect!");
        }
        a+=c;
        String[] permission={"android.permission.INTERNET","android.permission.ACCESS_NETWORK_STATE","android.permission.ACCESS_WIFI_STATE"};
        for (String permissions : permission) {
            int per =this.checkPermission(permissions, Process.myPid(),Process.myUid());
            if (PackageManager.PERMISSION_GRANTED != per) {
                throw new RuntimeException("?!! detect!");
            }
        }

        if (finger.contains("TOSOT") ||finger.contains("anbox") ||finger.contains("android_x86") ||finger.contains("vpro") ||romname.contains("vmos") ||romname.contains("Vmos")) {
            throw new RuntimeException("Detected non-mdm illegal os");
        }
        a+=d;
        if ( 666 == keystatus) {
        }
        else{
            Toast.ShowInfo(this,"请使用官方渠道安装包进行安装,否则将收不到更新!");
        }
        if(BuildConfig.VERSION_NAME.contains("oem")){
            if(!Sysutils.isSystemApplication(this)){
                Toast.ShowErr(this,"请安装到系统或使用正常版本");
                throw new RuntimeException("oem app error");
            }
            else{
                Toast.ShowSuccess(this,"oem app loaded!");
                Intent intent=new Intent(this, NewUI.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }
    public static Context getApplication() {
        return mApplication;
    }
    public static String geta(){
        return a;
    }
}