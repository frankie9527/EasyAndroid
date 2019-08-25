package org.easy.tools;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;

import org.easy.tools.utils.CrashHandler;

public class EasySdk {
    @SuppressLint("StaticFieldLeak")
    private static Application mContext;
    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        EasySdk.mContext = app;
        CrashHandler.getInstance().init();
    }
    public static Application getContext(){
        return mContext;
    }
}
