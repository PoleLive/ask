package cn.lawliex.ask.data.source;

/**
 * Created by Terence on 2017/1/5.
 */

public interface DataSource<T> {
    interface OnLoadDataCallback<T>{
        void onLoadDataSuccess(T data);
        void onLoadDataFail();
    }
    void loadData(OnLoadDataCallback callback);
    void saveData(T data);
}
