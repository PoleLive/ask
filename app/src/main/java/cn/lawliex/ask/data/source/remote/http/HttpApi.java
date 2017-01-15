package cn.lawliex.ask.data.source.remote.http;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;


import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.LoginResponse;
import cn.lawliex.ask.data.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Terence on 2016/12/27.
 */

public interface HttpApi {
    @FormUrlEncoded
    @POST("{path}")
    Observable<JSONObject> post(@Path("path") String path, @FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("{root}/{path}")
    Observable<JSONObject> post(@Path("root") String root,@Path("path") String path, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("{root}/{path}")
    Observable<JSONObject> post(@Path("root") String root,@Path("path") String path);
    @FormUrlEncoded
    @POST("{path}")
    Observable<JSONObject> post(@Path("path") String path);

    @GET("{path}")
    Observable<JSONObject> get(@Path("path") String path);

    @GET("{path}")
    Observable<JSONObject> get(@Path("path") String path, @QueryMap Map<String,String> map);

    @Multipart
    @POST("upload")
    Observable<JSONObject> upload(@Part("file") RequestBody description,
                                  @Part MultipartBody.Part file, @QueryMap Map<String,String> map);
    @Multipart
    @POST("sendimg")
    Observable<JSONObject> sendImg(@Part("file") RequestBody description,
                                  @Part MultipartBody.Part file, @QueryMap Map<String,String> map);
}
