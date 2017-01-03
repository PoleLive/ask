package cn.lawliex.ask.data.source.remote.http;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.ApplicationContract;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Terence on 2016/12/27.
 */

public class HttpRequests {
    private static String baseUrl = UrlContract.SERVER_ADDRESS;
    private static HttpRequests instance = null;
    private Observable<JSONObject> observable;
    private Subscriber subscriber;
    private HttpApi httpService;
    private JSONObject response;
    public static HttpRequests getInstance(){
        if(instance == null){
            synchronized (HttpRequests.class){
                if(instance == null){
                    instance = new HttpRequests();
                }
            }
        }
        return instance;
    }
    private HttpRequests(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        httpService = retrofit.create(HttpApi.class);
        subscriber = new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JSONObject tmp) {
                response = tmp;
            }
        };

    }
    public void post(RequestBody description, MultipartBody.Part body, Map<String,String>map){
        observable = httpService.upload(description, body,map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public JSONObject post(String path, Map<String, String> map) {
        response = null;

        if(path.split("/").length > 1){
            String root = path.split("/")[0];
            path = path.split("/")[1];
            if(map != null)
                observable = httpService.post(root, path, map);
            else
                observable = httpService.post(root, path);
        }
        else if(map != null)
            observable = httpService.post(path, map);
        else
            observable = httpService.post(path);


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return  response;
    }

    public JSONObject get(String path, Map<String,String> map){
        response = null;
        if(map != null){
            observable = httpService.get(path,map);
        }else{
            observable = httpService.get(path);
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return response;

    }
    public HttpRequests baseUrl(String url){
        baseUrl = url;
        return instance;
    }
    public HttpRequests subscribe(Subscriber<JSONObject> subscriber){
        this.subscriber = subscriber;
        return instance;
    }
}
