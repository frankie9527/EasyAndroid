package org.easy.tools.utils;


import android.text.TextUtils;
import android.util.Log;


import org.easy.tools.EasySdk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 把代码 log 日志写在本地
 * */
public class FlieLog {
//    public static String path = "/storage/emulated/0/PeiFeng";
public static String path = EasySdk.getContext().getExternalFilesDir(null).getAbsolutePath();
    public static String name = path + File.separator + "log.txt";

    public static String filePath="";
    private static void writeBase(String str,String path) {
        StackTraceElement ste[] = Thread.currentThread().getStackTrace();
        StackTraceElement heihei=ste[3];
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("["+heihei.getClassName()+"]").append("["+heihei.getMethodName()+" "+ heihei.getLineNumber()+"]");
        Log.e("writeLocal",str);
        BufferedWriter out = null;
        try {
            init();
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path, true)));
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(currentTime);
            out.write(dateString + " " + str + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
    * 把log 写在 android data/data /应用名 里面
    * */
    public static void writeFile2Local(String str) {
     writeBase(str,getFilePath());
    }
    /**
     * 把log 写在 android data/data /应用名 里面
     * */
    public static void writeFile2Path(String str,String local) {
       writeBase(str,path + File.separator + local+".txt");
    }
    /**
     * 把log 写在 任何可以获取到的地方
     * */
    public static void writeFile2Want(String str,String path) {
        writeBase(str,path);
    }
    public  static String getFilePath(){
        if (!TextUtils.isEmpty(filePath)){
            return filePath;
        }
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        filePath=path + File.separator+dateString+"log.txt";
        return filePath;
    }


    private static void init() {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


}
