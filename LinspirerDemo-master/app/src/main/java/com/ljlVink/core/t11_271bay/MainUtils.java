package com.ljlVink.core.t11_271bay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.ljlVink.core.ToastUtils;

public class MainUtils {
    private Context context;
    private IUidSystemService iUidSystemService;
    public MainUtils(Context context){
        this.context=context;
    }
    public String getversion(){
        return "20220703";
    }
    public void InitHack(){
        if(iUidSystemService==null){
            Intent intent = new Intent("com.tensafe.app.onerun.uidsystem_service");
            intent.setPackage("com.tensafe.app.onerun");
            try{
                context.bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        iUidSystemService = IUidSystemService.Stub.asInterface(iBinder);
                        try{
                            iUidSystemService.InnerEnableSdCard();
                            iUidSystemService.InnerEnableUsbState();
                            iUidSystemService.InnerEnableStatusBarAndHomeRecent();
                            iUidSystemService.InnerDebugMode(true);
                        }catch (RemoteException e){
                        }
                    }
                    @Override public void onServiceDisconnected(ComponentName componentName){}
                },Context.BIND_AUTO_CREATE);

            }catch (Throwable th){
                ToastUtils.ShowToast("onerun服务绑定异常",context);
            }
        }
    }

    public void iceapp(String pkgname,boolean isice){
        Intent intent = new Intent("com.tensafe.app.onerun.uidsystem_service");
        intent.setPackage("com.tensafe.app.onerun");
        try{
            context.bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    iUidSystemService = IUidSystemService.Stub.asInterface(iBinder);
                    try{
                        iUidSystemService.InnerDisablePackage(pkgname,isice);
                    }catch (RemoteException e){
                    }
                }
                @Override public void onServiceDisconnected(ComponentName componentName){}
            },Context.BIND_AUTO_CREATE);

        }catch (Throwable th){
            ToastUtils.ShowToast("onerun服务绑定异常",context);
        }
    }
    public void InstallApp(String abspath){
        Intent intent = new Intent("com.tensafe.app.onerun.fun.slient_installpackage_pm");
        intent.setPackage("com.topjohnwu.magisk");
        intent.putExtra("pkgpath", abspath);
        context.sendBroadcast(intent);
    }
    public void RootCommand(String str){
        Intent intent = new Intent("com.tensafe.app.onerun.fun.sucmd");
        intent.setPackage("com.topjohnwu.magisk");
        intent.putExtra("argv", str);
        intent.putExtra("magic", "!@#$@ss2$");
        context.sendBroadcast(intent);
    }
    public void killapp(String pkgname){
        Intent intent = new Intent("com.tensafe.app.onerun.uidsystem_service");
        intent.setPackage("com.tensafe.app.onerun");
        try{
            context.bindService(intent, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    iUidSystemService = IUidSystemService.Stub.asInterface(iBinder);
                    try{
                        iUidSystemService.InnerForceStopPackage(pkgname);
                    }catch (RemoteException e){
                    }
                }
                @Override public void onServiceDisconnected(ComponentName componentName){}
            },Context.BIND_AUTO_CREATE);

        }catch (Throwable th){
            ToastUtils.ShowToast("onerun服务绑定异常",context);
        }

    }
    public void FirstHack(){
        ToastUtils.ShowToast("T11设备 加载",context);
        InitHack();

    }

}
