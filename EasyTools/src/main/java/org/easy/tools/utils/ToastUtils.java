package org.easy.tools.utils;

import android.widget.Toast;

import org.easy.tools.EasySdk;


public class ToastUtils {
    private static ToastUtils toastUtils;
    private ToastUtils() {
    }
    public static ToastUtils getInstance() {
        if (toastUtils==null){
            toastUtils=new ToastUtils();
        }
        return toastUtils;
    }

    public void show(String str) {
        Toast.makeText(EasySdk.getContext(), str, Toast.LENGTH_LONG).show();
    }
}
