package org.easy.android;

import android.app.Application;
import android.content.Context;

/**
 * author：jyh
 * QQ：847145851
 * time：2019/4/2
 * describe：
 **/
public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
    public static Context getContext(){
        return mContext;
    }
}
