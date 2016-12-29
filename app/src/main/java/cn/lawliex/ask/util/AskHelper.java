package cn.lawliex.ask.util;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cn.lawliex.ask.data.source.local.SharedPreferencesHelper;

/**
 * Created by Terence on 2016/12/28.
 */

public class AskHelper {
    public static Map<String,String> getRequestMap(Context context){
        SharedPreferencesHelper sphelper = new SharedPreferencesHelper(context);
        String ticket = sphelper.getString("ticket");
        Map<String,String> map = new HashMap<>();
        map.put("ticket",ticket);
        return map;
    }
}
