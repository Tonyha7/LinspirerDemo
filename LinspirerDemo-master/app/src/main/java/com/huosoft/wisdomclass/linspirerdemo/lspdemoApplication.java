package com.huosoft.wisdomclass.linspirerdemo;
import android.app.Application;
import android.util.Log;

import com.ljlVink.MDM;
import com.ljlVink.core.CrashHandler;
import com.ljlVink.core.DataCleanManager;
import com.ljlVink.core.FileUtils;
import com.ljlVink.core.HackMdm;
import com.ljlVink.core.security.ROM_identifier;
import com.tencent.bugly.Bugly;
public class lspdemoApplication extends Application {
    int MMDM=0;
    @Override
    public void onCreate() {
        super.onCreate();
        DataCleanManager.clearAllCache(this);
        /*int keystatus = new Signutil(this, "97:8D:89:23:F9:F3:AF:C9:A3:79:37:2C:C8:A6:FF:A8:26:CC:DE:EF").f();
        if (1154 == keystatus) {
            exit("fuckyoU bitch");
        }
        if (!new SoChecker(this).socheck()){
            exit("fuckyOu bitch");
        }
        if (!new AppEntranceChecker(this).checkEntrance()){
            exit("fuckYou bitch");
        }
        */
        new ROM_identifier(this).checkrom();
        Bugly.init(getApplicationContext(), "50a36059a3", false);
        CrashHandler crashHandler=CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        MMDM=new MDM(this).MDM();
        HackMdm hminit=new HackMdm(this);
        FileUtils.getInstance(this).copyAssetsToSD("apk","lspdemo.apks");
        Log.e("加载","加载完毕");
    }
    public void exit(String str){
        try{
            Log.e("lspdemo","https://gitee.com/ljlvink/huovink_-mdm_catch_for_-lenovo");
        }catch (Error e){
            e.printStackTrace();
        }
    }

}