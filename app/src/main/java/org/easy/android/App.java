package org.easy.android;

import android.app.Application;
import android.content.Context;

import org.easy.tools.EasySdk;
import org.easy.tools.utils.CrashHandler;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/2
 * https://github.com/ZengChong500373
 * describe：
 **/
public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        EasySdk.init(this);
    }
    public static Context getContext(){
        return mContext;
    }
}
