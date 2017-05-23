package com.ywy.mylibs.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created ywy on 2017/5/15.
 */

public interface RetrofitApiService {
    @GET("{url}")
    Call<Object> executeRetrofitGet(
            @Path(value = "url", encoded = true) String url,
            @QueryMap(encoded = true) Map<String, String> maps);

    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> executePost(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, String> maps);

    @GET("{url}")
    Observable<ResponseBody> executeGet(
            @Path(value = "url", encoded = true) String url,
            @QueryMap(encoded = true) Map<String, String> maps);

    @DELETE("{url}")
    Observable<ResponseBody> executeDelete(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps);

    @PUT("{url}")
    Observable<ResponseBody> executePut(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps);

    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path(value = "url", encoded = true) String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @Multipart
    @POST("{url}")
    Observable<ResponseBody> uploadFiles(
            @Path(value = "url", encoded = true) String url,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
