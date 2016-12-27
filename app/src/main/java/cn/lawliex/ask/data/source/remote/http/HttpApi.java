package cn.lawliex.ask.data.source.remote.http;

import java.util.Map;


import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.LoginResponse;
import cn.lawliex.ask.data.User;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Terence on 2016/12/27.
 */

public interface HttpApi {
    @FormUrlEncoded
    @POST("{path}")
    Observable<BaseResponse> post(@Path("path") String path, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("{path}")
    Observable<BaseResponse> post(@Path("path") String path);

    @GET("{path}")
    Observable<BaseResponse> get(@Path("path") String path);

    @GET("{path}")
    Observable<BaseResponse> get(@Path("path") String path, @QueryMap Map<String,String> map);
}
