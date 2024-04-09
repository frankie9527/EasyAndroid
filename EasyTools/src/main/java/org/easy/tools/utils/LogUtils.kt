package org.easy.tools.utils

import android.util.Log
import org.easy.tools.BuildConfig

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
class LogUtils {


    companion object {
        private val TAG = "LogUtils"
        /**
         * 打印普通日志
         */
        @JvmStatic
        fun d(tag: String, str: String?) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG + "_" + tag, str!!)
            }
        }

        /**
         * 打印错误日志
         */
        @JvmStatic
        fun e(tag: String, str: String?) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG + "_" + tag, str!!)
            }
        }

    }


}