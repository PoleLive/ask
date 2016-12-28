package cn.lawliex.ask.data.source.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Terence on 2016/12/27.
 */

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    public SharedPreferencesHelper(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ask",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveString(String key, String value){
       editor.putString(key,value);
    }
    public void saveInt(String key, int value){
        editor.putInt(key, value);
    }
    public void saveBoolean(String key, boolean value){
        editor.putBoolean(key, value);
    }
    public String getString(String key){
        return sharedPreferences.getString(key,null);
    }
    public int getInt(String key){
        return sharedPreferences.getInt(key, 0);
    }
    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }
}
