package cn.lawliex.ask.data.source.remote.http;

import java.util.Map;

import cn.lawliex.ask.ApplicationContract;
import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.User;
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
    private static String baseUrl = ApplicationContract.SERVER_ADDRESS;
    private static HttpRequests instance = null;
    private Observable<BaseResponse> observable;
    private Subscriber subscriber;
    private HttpApi httpService;
    private BaseResponse response;
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
        subscriber = new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseResponse tmp) {
                response = tmp;
            }
        };

    }
    public BaseResponse post(String path, Map<String, String> map) {
        response = null;
        if(map != null)
            observable = httpService.post(path, map);
        else
            observable = httpService.post(path);


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return  response;
    }

    public BaseResponse get(String path, Map<String,String> map){
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
    public HttpRequests subscribe(Subscriber<BaseResponse> subscriber){
        this.subscriber = subscriber;
        return instance;
    }
}
