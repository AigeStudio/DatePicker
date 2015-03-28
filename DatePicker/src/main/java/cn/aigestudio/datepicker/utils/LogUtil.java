/*
 Copyright 2014-2015 AigeStudio(https://github.com/AigeStudio)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package cn.aigestudio.datepicker.utils;

import android.util.Log;

/**
 * 日志输出工具类
 * <p/>
 * Util of log.
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public final class LogUtil {
    public final static String TAG = "AigeStudio";
    public final static String MATCH = "%s->%s->%d";
    public final static String CONNECTOR = ":<--->:";

    public final static boolean SWITCH = true;

    public static String buildHeader() {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
        return String.format(MATCH, stack.getClassName(), stack.getMethodName(),
                stack.getLineNumber()) + CONNECTOR;
    }

    public static void v(Object msg) {
        if (SWITCH) {
            Log.v(TAG, buildHeader() + msg.toString());
        }
    }

    public static void d(Object msg) {
        if (SWITCH) {
            Log.d(TAG, buildHeader() + msg.toString());
        }
    }

    public static void i(Object msg) {
        if (SWITCH) {
            Log.i(TAG, buildHeader() + msg.toString());
        }
    }

    public static void w(Object msg) {
        if (SWITCH) {
            Log.w(TAG, buildHeader() + msg.toString());
        }
    }

    public static void e(Object msg) {
        if (SWITCH) {
            Log.e(TAG, buildHeader() + msg.toString());
        }
    }
}
