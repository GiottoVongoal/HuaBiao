package com.ywy.mylibs.retrofit;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.blankj.ALog;
import com.google.gson.Gson;
import com.ywy.mylibs.R;
import com.ywy.mylibs.base.BaseInterceptor;
import com.ywy.mylibs.listener.BaseRetrofitCallBackResponse;
import com.ywy.mylibs.listener.DownLoadCallBack;
import com.ywy.mylibs.listener.ICallBackResultCode;
import com.ywy.mylibs.listener.ICallBackStatusCode;
import com.ywy.mylibs.manager.RetrofitCookieManger;
import com.ywy.mylibs.manager.RetrofitDownLoadManager;
import com.ywy.mylibs.utils.CommonDialogUtils;
import com.ywy.mylibs.utils.CoreNullUtils;
import com.ywy.mylibs.utils.NetWorkUtils;
import com.ywy.mylibs.utils.ToastUtils;
import com.ywy.mylibs.wedgit.dialog.CommonLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


/**
 * @author shaoshuai.
 * @PackageName com.cyp.p.retrofit.net
 * @date 2016-09-28 17:04
 * @description retrofit client
 */
public class RetrofitClient {

    private static Context mContext;
    private static Retrofit retrofit;
    private static RetrofitApiService apiManager;
    private static Boolean isLoading = true;// Whether it is displayed ywy_lib_loading_iv_loading
    private static Boolean isParseStateCode = false;// Whether to parse the status code
    private static Map<String, String> mCommonParameter;
    private CommonLoadingDialog mLoadingDialog = null;
    private RetrofitSubscriber baseSubscriber;
    private Observable<ResponseBody> downObservable;
    private Map<String, Observable<ResponseBody>> downMaps = new HashMap<String, Observable<ResponseBody>>() {
    };

    /**
     * Mandatory constructor for the
     */
    public RetrofitClient(RetrofitApiService apiManager) {
        this.apiManager = apiManager;
    }

    /**
     * create ApiService
     */
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * @param subscriber
     */
    public <T> T call(Observable<T> observable, Subscriber<T> subscriber) {
        observable.compose(schedulersTransformer)
                .subscribe(subscriber);
        return null;
    }

    /**
     * MethodHandler
     */
    private List<Type> MethodHandler(Type[] types) {
        ALog.d("types size: " + types.length);
        List<Type> needtypes = new ArrayList<Type>();
        Type needParentType = null;

        for (Type paramType : types) {
            System.out.println("  " + paramType);
            // if Type is T
            if (paramType instanceof ParameterizedType) {
                Type[] parentypes = ((ParameterizedType) paramType).getActualTypeArguments();
                ALog.d("TypeArgument: ");
                for (Type childtype : parentypes) {
                    ALog.d("childType:" + childtype);
                    needtypes.add(childtype);
                    //needParentType = childtype;
                    if (childtype instanceof ParameterizedType) {
                        Type[] childtypes = ((ParameterizedType) childtype).getActualTypeArguments();
                        for (Type type : childtypes) {
                            needtypes.add(type);
                            //needChildType = type;
                            ALog.d("type:" + childtype);
                        }
                    }
                }
            }
        }
        return needtypes;
    }

    final Observable.Transformer schedulersTransformer = new Observable.Transformer() {

        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            //todo 在doOnSubscribe()的后面跟一个 subscribeOn() ，就能指定准备工作的线程了
                            //todo Run on the main thread
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * Retroift get
     *
     * @param action
     * @param parameters
     * @param subscriber
     * @return no parse data
     */
    private void get(String action, Map<String, String> parameters, BaseSubscriber<ResponseBody> subscriber) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return;
        }
        if (mContext != null && mContext instanceof Activity && isLoading && subscriber != null) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });
            subscriber.setContext(mContext);
            subscriber.setLoadingDialog(mLoadingDialog);
        }
        apiManager.executeGet(action, parameters).compose(schedulersTransformer).subscribe(subscriber);
    }

    /**
     * Retroift post
     *
     * @param action
     * @param parameters
     * @param subscriber
     */
    private void post(String action, Map<String, String> parameters, BaseSubscriber<ResponseBody> subscriber) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return;
        }
        if (mContext != null && mContext instanceof Activity && isLoading && mContext != null && subscriber != null) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });
            subscriber.setContext(mContext);
            subscriber.setLoadingDialog(mLoadingDialog);
        }
        apiManager.executePost(action, parameters).compose(schedulersTransformer).subscribe(subscriber);
    }

    /**
     * Retroift get
     *
     * @param action
     * @param parameters
     * @param callBack
     * @return no parse data
     */
    public <T> T getL(String action, Map<String, String> parameters, final ResponseCallBack<ResponseBody> callBack) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return null;
        }
        if (mContext != null && mContext instanceof Activity && isLoading)
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });
        baseSubscriber = new RetrofitSubscriber<ResponseBody>(mContext, null, callBack);
        apiManager.executeGet(action, parameters).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * Retroift post
     *
     * @param action
     * @param parameters
     * @param callBack
     */
    public <T> T postL(String action, Map<String, String> parameters, final ResponseCallBack<ResponseBody> callBack) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext instanceof Activity && mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return null;
        }
        if (mContext != null && mContext instanceof Activity && isLoading)
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });
        baseSubscriber = new RetrofitSubscriber<ResponseBody>(mContext, null, callBack);
        apiManager.executePost(action, parameters).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * Retroift executeGet
     *
     * @param action
     * @param parameters
     * @param callBack
     * @param <T>
     * @return
     */
    public <T> T executeGet(final String action, final Map<String, String> parameters, final ResponseCallBack<T> callBack) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext instanceof Activity && mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return null;
        }
        if (mContext != null && mContext instanceof Activity && isLoading)
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });

        final Type[] types = callBack.getClass().getGenericInterfaces();

        if (MethodHandler(types) == null && MethodHandler(types).size() == 0) {
            return null;
        }
        final Type finalNeedType = MethodHandler(types).get(0);
        ALog.d("Type -->: " + types[0]);

        baseSubscriber = new RetrofitSubscriber<T>(mContext, finalNeedType, callBack);
        apiManager.executeGet(action, parameters).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * Retroift executePost
     *
     * @param action
     * @param parameters
     * @param callBack
     * @param <T>
     * @return
     */
    public <T> T executePost(final String action, final Map<String, String> parameters, final ResponseCallBack<T> callBack) {
        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }
        if (mContext instanceof Activity && mContext != null && !NetWorkUtils.isNetWorkConnected(mContext)) {
            ToastUtils.getInstance().showToast(mContext.getString(R.string.check_net_connection));
            return null;
        }
        if (mContext != null && mContext instanceof Activity && isLoading)
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog = CommonDialogUtils.showLoadingDialog(mContext, mContext.getString(R.string.common_loading_loadingmsg));
                }
            });

        final Type[] types = callBack.getClass().getGenericInterfaces();

        if (MethodHandler(types) == null && MethodHandler(types).size() == 0) {
            return null;
        }
        final Type finalNeedType = MethodHandler(types).get(0);
        ALog.d("Type -->: " + types[0]);

        baseSubscriber = new RetrofitSubscriber<T>(mContext, finalNeedType, callBack);
        apiManager.executePost(action, parameters).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * Execute http by Delete
     *
     * @return parsed data
     * you don't need to   parse ResponseBody
     */
    public <T> T executeDelete(final String action, final Map<String, String> maps, final ResponseCallBack<T> callBack) {
        final Type[] types = callBack.getClass().getGenericInterfaces();

        if (MethodHandler(types) == null && MethodHandler(types).size() == 0) {
            return null;
        }
        final Type finalNeedType = MethodHandler(types).get(0);
        ALog.d("Type -->: " + types[0]);

        baseSubscriber = new RetrofitSubscriber<T>(mContext, finalNeedType, callBack);
        apiManager.executeDelete(action, maps).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * Execute  Http by Put
     *
     * @return parsed data
     * you don't need to parse ResponseBody
     */
    public <T> T executePut(final String action, final Map<String, String> maps, final ResponseCallBack<T> callBack) {
        final Type[] types = callBack.getClass().getGenericInterfaces();

        if (MethodHandler(types) == null && MethodHandler(types).size() == 0) {
            return null;
        }
        final Type finalNeedType = MethodHandler(types).get(0);
        ALog.d("Type -->: " + types[0]);

        baseSubscriber = new RetrofitSubscriber<T>(mContext, finalNeedType, callBack);
        apiManager.executePut(action, maps).compose(schedulersTransformer).subscribe(baseSubscriber);
        return null;
    }

    /**
     * upload
     *
     * @param action
     * @param requestBody requestBody
     * @param subscriber  subscriber
     * @param <T>         T
     * @return
     */
    public <T> T upload(String action, RequestBody requestBody, Subscriber<ResponseBody> subscriber) {
        apiManager.upLoadFile(action, requestBody).compose(schedulersTransformer).subscribe(subscriber);
        return null;
    }


    /**
     * download
     *
     * @param action
     * @param callBack
     */
    public void download(String action, DownLoadCallBack callBack) {

        if (downMaps.get(action) == null) {
            downObservable = apiManager.downloadFile(action);
            downMaps.put(action, downObservable);
        } else {
            downObservable = downMaps.get(action);
        }

        if (RetrofitDownLoadManager.isDownLoading) {
            downObservable.unsubscribeOn(Schedulers.io());
            RetrofitDownLoadManager.isDownLoading = false;
            RetrofitDownLoadManager.isCancel = true;
            return;
        }
        RetrofitDownLoadManager.isDownLoading = true;
        downObservable.compose(schedulersTransformer).subscribe(new DownSubscriber<ResponseBody>(callBack, mContext));
    }

    /**
     * cancel request
     */
    public void cancelRequest() {
        if (baseSubscriber != null)
            baseSubscriber.unsubscribe();
    }

    /**
     * Mandatory Builder for the Builder
     */
    public static final class Builder {

        private static final int DEFAULT_TIMEOUT = 5;
        private static final int DEFAULT_MAXIDLE_CONNECTIONS = 5;
        private static final long DEFAULT_KEEP_ALIVEDURATION = 8;
        private static final long caheMaxSize = 10 * 1024 * 1024;

        private okhttp3.Call.Factory callFactory;
        private String baseUrl ;
        private Boolean isLog = false;
        private List<InputStream> certificateList;
        private HostnameVerifier hostnameVerifier;
        private CertificatePinner certificatePinner;
        private List<Converter.Factory> converterFactories = new ArrayList<Converter.Factory>();
        private List<CallAdapter.Factory> adapterFactories = new ArrayList<CallAdapter.Factory>();
        private Executor callbackExecutor;
        private boolean validateEagerly;
        private Context context;
        private RetrofitCookieManger cookieManager;
        private Cache cache = null;
        private Proxy proxy;
        private File httpCacheDirectory;
        private SSLSocketFactory sslSocketFactory;
        private ConnectionPool connectionPool;
        private Converter.Factory converterFactory;
        private CallAdapter.Factory callAdapterFactory;

        private Map<String, String> headers;
        private Map<String, String> parameters;
        private Retrofit.Builder retrofitBuilder;
        private OkHttpClient okHttpClient;
        private OkHttpClient.Builder okhttpBuilder;

        private Boolean bLoading = true;// Whether it is displayed ywy_lib_loading_iv_loading
        private Boolean bParseStateCode = false;// Whether to parse the status code

        public Builder(Context context) {
            // Add the base url first. This prevents overriding its behavior but also
            // ensures correct behavior when using novate that consume all types.
            okhttpBuilder = new OkHttpClient.Builder();
            retrofitBuilder = new Retrofit.Builder();
            this.context = context;
        }

        /**
         * The HTTP client used for requests. default OkHttpClient
         * <p/>
         * This is a convenience method for calling {@link #callFactory}.
         * <p/>
         * Note: This method <b>does not</b> make a defensive copy of {@code client}. Changes to its
         * settings will affect subsequent requests. Pass in a {@linkplain OkHttpClient#clone() cloned}
         * instance to prevent this if desired.
         */
        public Builder client(OkHttpClient client) {
            retrofitBuilder.client(CoreNullUtils.checkNotNull(client, "client == null"));
            return this;
        }

        /**
         * Add ApiManager for serialization and deserialization of objects.
         *//*
        public Builder addApiManager(final Class<ApiManager> service) {

            apiManager = retrofit.create((Utils.checkNotNull(service, "apiManager == null")));
            //return retrofit.create(service);
            return this;
        }*/

        /**
         * Specify a custom call factory for creating {@link } instances.
         * <p/>
         * Note: Calling {@link #client} automatically sets this value.
         */
        public Builder callFactory(okhttp3.Call.Factory factory) {
            this.callFactory = CoreNullUtils.checkNotNull(factory, "factory == null");
            return this;
        }

        /**
         * Sets the default connect timeout for new connections. A value of 0 means no timeout,
         * otherwise values must be between 1 and {@link Integer#MAX_VALUE} when converted to
         * milliseconds.
         */
        public Builder connectTimeout(int timeout) {
            return connectTimeout(timeout, TimeUnit.SECONDS);
        }

        /**
         * Sets the default connect timeout for new connections. A value of 0 means no timeout,
         * otherwise values must be between 1 and {@link Integer#MAX_VALUE} when converted to
         * milliseconds.
         */
        public Builder writeTimeout(int timeout) {
            return writeTimeout(timeout, TimeUnit.SECONDS);
        }

        public Builder addLog(boolean isLog) {
            this.isLog = isLog;
            return this;
        }

        public Builder addLoading(boolean isLoading) {
            this.bLoading = isLoading;
            return this;
        }

        public Builder addParseStateCode(boolean isParseStateCode) {
            this.bParseStateCode = isParseStateCode;
            return this;
        }

        public Builder proxy(Proxy proxy) {
            okhttpBuilder.proxy(CoreNullUtils.checkNotNull(proxy, "proxy == null"));
            return this;
        }

        /**
         * Sets the default write timeout for new connections. A value of 0 means no timeout,
         * otherwise values must be between 1 and {@link TimeUnit #MAX_VALUE} when converted to
         * milliseconds.
         */
        public Builder writeTimeout(int timeout, TimeUnit unit) {
            if (timeout != -1) {
                okhttpBuilder.writeTimeout(timeout, unit);
            } else {
                okhttpBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            }
            return this;
        }

        /**
         * Sets the connection pool used to recycle HTTP and HTTPS connections.
         * <p>
         * <p>If unset, a new connection pool will be used.
         */
        public Builder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool == null) throw new NullPointerException("connectionPool == null");
            this.connectionPool = connectionPool;
            return this;
        }

        /**
         * Sets the default connect timeout for new connections. A value of 0 means no timeout,
         * otherwise values must be between 1 and {@link TimeUnit #MAX_VALUE} when converted to
         * milliseconds.
         */
        public Builder connectTimeout(int timeout, TimeUnit unit) {
            if (timeout != -1) {
                okhttpBuilder.connectTimeout(timeout, unit);
            } else {
                okhttpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            }
            return this;
        }


        /**
         * Set an API base URL which can change over time.
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = CoreNullUtils.checkNotNull(baseUrl, "baseUrl == null");
            return this;
        }

        /**
         * Add converter factory for serialization and deserialization of objects.
         */
        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactory = factory;
            return this;
        }

        /**
         * Add a call adapter factory for supporting service method return types other than {@link CallAdapter
         * }.
         */
        public Builder addCallAdapterFactory(CallAdapter.Factory factory) {
            this.callAdapterFactory = factory;
            return this;
        }

        /**
         * Add Header for serialization and deserialization of objects.
         */
        public Builder addHeader(Map<String, String> headers) {
            okhttpBuilder.addInterceptor(new BaseInterceptor((CoreNullUtils.checkNotNull(headers, "header == null"))));
            return this;
        }

        /**
         * Add parameters for serialization and deserialization of objects.
         */
        public Builder addParameters(Map<String, String> parameters) {
            okhttpBuilder.addInterceptor(new BaseInterceptor((CoreNullUtils.checkNotNull(parameters, "parameters == null"))));
            return this;
        }

        /**
         * Add common parameters for serialization and deserialization of objects.
         */
        public Builder addCommonParameters(Map<String, String> parameters) {
            this.parameters = parameters;
            return this;
        }

        /**
         * Returns a modifiable list of interceptors that observe a single network request and response.
         * These interceptors must call {@link Interceptor.Chain#proceed} exactly once: it is an error
         * for a network interceptor to short-circuit or repeat a network request.
         */
        public Builder addInterceptor(Interceptor interceptor) {
            okhttpBuilder.addInterceptor(CoreNullUtils.checkNotNull(interceptor, "interceptor == null"));
            return this;
        }

        /**
         * The executor on which {@link Call} methods are invoked when returning {@link Call} from
         * your service method.
         * <p/>
         * Note: {@code executor} is not used for {@linkplain #addCallAdapterFactory custom method
         * return types}.
         */
        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = CoreNullUtils.checkNotNull(executor, "executor == null");
            return this;
        }

        /**
         * When calling {@link #create} on the resulting {@link Retrofit} instance, eagerly validate
         * the configuration of all methods in the supplied interface.
         */
        public Builder validateEagerly(boolean validateEagerly) {
            this.validateEagerly = validateEagerly;
            return this;
        }

        /**
         * Sets the handler that can accept cookies from incoming HTTP responses and provides cookies to
         * outgoing HTTP requests.
         * <p/>
         * <p>If unset, {@linkplain RetrofitCookieManger#NO_COOKIES no cookies} will be accepted nor provided.
         */
        public Builder cookieManager(RetrofitCookieManger cookie) {
            if (cookie == null) throw new NullPointerException("cookieManager == null");
            this.cookieManager = cookie;
            return this;
        }

        /**
         *
         */
        public Builder addSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
            return this;
        }

        public Builder addHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder addCertificatePinner(CertificatePinner certificatePinner) {
            this.certificatePinner = certificatePinner;
            return this;
        }


        /**
         * Sets the handler that can accept cookies from incoming HTTP responses and provides cookies to
         * outgoing HTTP requests.
         * <p/>
         * <p>If unset, {@linkplain RetrofitCookieManger#NO_COOKIES no cookies} will be accepted nor provided.
         */
        public Builder addSSL(String[] hosts, int[] certificates) {
            if (hosts == null) throw new NullPointerException("hosts == null");
            if (certificates == null) throw new NullPointerException("ids == null");


            addSSLSocketFactory(RetrofitHttpsFactroy.getSSLSocketFactory(context, certificates));
            addHostnameVerifier(RetrofitHttpsFactroy.getHostnameVerifier(hosts));
            return this;
        }

        public Builder addNetworkInterceptor(Interceptor interceptor) {
            okhttpBuilder.addNetworkInterceptor(interceptor);
            return this;
        }

        /**
         * setCache
         *
         * @param cache cahe
         * @return Builder
         */
        public Builder addCache(Cache cache) {
            int maxStale = 60 * 60 * 24 * 3;
            return addCacheAge(cache, maxStale);
        }

        /**
         * @return
         */
        public Builder addCacheAge(Cache cache, final int cacheTime) {
            addCache(cache, String.format("max-age=%d", cacheTime));
            return this;
        }

        /**
         * @param cache
         * @param cacheTime ms
         * @return
         */
        public Builder addCacheStale(Cache cache, final int cacheTime) {
            addCache(cache, String.format("max-stale=%d", cacheTime));
            return this;
        }

        /**
         * @param cache
         * @param cacheControlValue Cache-Control值
         * @return
         */
        public Builder addCache(Cache cache, final String cacheControlValue) {
            Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new CaheInterceptor(mContext, cacheControlValue);
            addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            this.cache = cache;
            return this;
        }

        /**
         * Create the {@link Retrofit} instance using the configured values.
         * <p/>
         * Note: If neither {@link #client} nor {@link #callFactory} is called a default {@link
         * OkHttpClient} will be created and used.
         */
        public RetrofitClient build() {

            if (baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }

            if (okhttpBuilder == null) {
                throw new IllegalStateException("okhttpBuilder required.");
            }

            if (retrofitBuilder == null) {
                throw new IllegalStateException("retrofitBuilder required.");
            }

            okhttpBuilder
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            /** set Context. */
            mContext = context;

            /** common parameters*/
            mCommonParameter = parameters;

            /** set ywy_lib_loading_iv_loading. */
            isLoading = bLoading;

            /** set stateCode*/
            isParseStateCode = bParseStateCode;

            /**
             * Set a fixed API base URL.
             *
             * @see #baseUrl(HttpUrl)
             */
            retrofitBuilder.baseUrl(baseUrl);

            /** Add converter factory for serialization and deserialization of objects. */
            if (converterFactory == null) {
                converterFactory = GsonConverterFactory.create();
            }
            retrofitBuilder.addConverterFactory(converterFactory);

            /**
             * Add a call adapter factory for supporting service method return types other than {@link
             * Call}.
             */
            if (callAdapterFactory == null) {
                callAdapterFactory = RxJavaCallAdapterFactory.create();
            }
            retrofitBuilder.addCallAdapterFactory(callAdapterFactory);

            if (isLog) {
                okhttpBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            if (sslSocketFactory != null) {
                okhttpBuilder.sslSocketFactory(sslSocketFactory);
            }

            if (hostnameVerifier != null) {
                okhttpBuilder.hostnameVerifier(hostnameVerifier);
            }

            if (httpCacheDirectory == null) {
                if (mContext.getCacheDir() != null)
                    httpCacheDirectory = new File(mContext.getCacheDir(), "cyp_retrofit_cache");
            }

            try {
                if (cache == null) {
                    cache = new Cache(httpCacheDirectory, caheMaxSize);
                }
            } catch (Exception e) {
                ALog.e("OKHttp Could not create http cache ---> " + e);
            }

            okhttpBuilder.cache(cache);

            addCache(cache);

            /**
             * Sets the connection pool used to recycle HTTP and HTTPS connections.
             *
             * <p>If unset, a new connection pool will be used.
             */
            if (connectionPool == null) {
                connectionPool = new ConnectionPool(DEFAULT_MAXIDLE_CONNECTIONS, DEFAULT_KEEP_ALIVEDURATION, TimeUnit.SECONDS);
            }
            okhttpBuilder.connectionPool(connectionPool);

            /**
             * Sets the HTTP proxy that will be used by connections created by this client. This takes
             * precedence over {@link #proxySelector}, which is only honored when this proxy is null (which
             * it is by default). To disable proxy use completely, call {@code setProxy(Proxy.NO_PROXY)}.
             */
            if (proxy == null) {
                okhttpBuilder.proxy(proxy);
            }

            /**
             * Sets the handler that can accept cookies from incoming HTTP responses and provides cookies to
             * outgoing HTTP requests.
             *
             * <p>If unset, {@link CoreRetrofitClient CookieManager#NO_COOKIES no cookies} will be accepted nor provided.
             */
            if (cookieManager == null) {
                cookieManager = new RetrofitCookieManger(context);
            }
            okhttpBuilder.cookieJar(new RetrofitCookieManger(context));

            /**
             *okhttp3.Call.Factory callFactory = this.callFactory;
             */
            if (callFactory != null) {
                retrofitBuilder.callFactory(callFactory);
            }

            /**
             * create okHttpClient
             */
            okHttpClient = okhttpBuilder.build();

            /**
             * set Retrofit client
             */
            retrofitBuilder.client(okHttpClient);

            /**
             * create Retrofit
             */
            retrofit = retrofitBuilder.build();

            /**
             *create BaseApiService;
             */
            apiManager = retrofit.create(RetrofitApiService.class);

            return new RetrofitClient(apiManager);
        }
    }

    /**
     * RetrofitSubscriber
     *
     * @param <T>
     */
    private class RetrofitSubscriber<T> extends Subscriber<ResponseBody> {

        private ResponseCallBack<T> callBack;
        private Type finalNeedType;

        public RetrofitSubscriber(Context context, Type finalNeedType, ResponseCallBack<T> callBack) {
            this.callBack = callBack;
            this.finalNeedType = finalNeedType;
        }

        @Override
        public void onCompleted() {
            if (mLoadingDialog != null)
                CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);
        }

        @Override
        public void onError(Throwable e) {
            if (mLoadingDialog != null)
                CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);
            if (callBack != null && e != null) {
                callBack.onFailure(e);
                ALog.e("onError --> :" + e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if (mLoadingDialog != null)
                CommonDialogUtils.dismissLoadingDialog(mLoadingDialog);

            try {
                if (callBack != null) {
                    try {
                        /**
                         * if need parse baseRespone<T> use ParentType, if parse T use childType . defult parse baseRespone<T>
                         *
                         *  callBack.onSuccee((T) JSON.parseArray(jsStr, (Class<Object>) finalNeedType));
                         *  Type finalNeedType = needChildType;
                         */
                        if (finalNeedType != null) {
                            byte[] bytes = responseBody.bytes();
                            String result = new String(bytes);
                            ALog.i("ResponseBody --> :" + result);

                            parseResultCode(result, mCallBackResultCode);
                            callBack.onSucceess((T) new Gson().fromJson(result, finalNeedType));

                            if (isParseStateCode && mCallBackStatusCode != null) {
                                BaseRetrofitCallBackResponse<T> baseResponse = new Gson().fromJson(result, finalNeedType);
                                mCallBackStatusCode.getCallBackStatusCode(baseResponse);
                            }
                        } else {
                            callBack.onSucceess((T) responseBody);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (callBack != null) {
                            callBack.onFailure(e);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (callBack != null) {
                    callBack.onFailure(e);
                }
            }
        }
    }

    private void parseResultCode(String result, ICallBackResultCode callBackResultCode) {
        if (!TextUtils.isEmpty(result) && result.contains("resultCode")) {
            try {
                JSONObject mJsonObj = new JSONObject(result);
                String resultCode = mJsonObj.getString("resultCode");
                String StateDescription = mJsonObj.getString("StateDescription");
                if (callBackResultCode != null) {
                    callBackResultCode.getCallBackResultCode(resultCode, StateDescription);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * executeRetrofitGet
     *
     * @param action
     * @param parameters
     * @param callBack
     * @param <T>
     * @return
     */
    public <T> T executeRetrofitGet(final String action, final Map<String, String> parameters, final ResponseCallBack<T> callBack) {

        if (parameters != null) {
            parameters.putAll(mCommonParameter);
        }

        Call<Object> call = apiManager.executeRetrofitGet(action, parameters);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Gson gson = new Gson();
                String result = gson.toJson(response.body());
                try {
                    JSONObject mJsonObj = new JSONObject(result);
                    String resultCode = mJsonObj.getString("resultCode");

                    final Type[] types = callBack.getClass().getGenericInterfaces();

                    if (MethodHandler(types) == null && MethodHandler(types).size() == 0) {
                        return;
                    }
                    final Type finalNeedType = MethodHandler(types).get(0);
                    ALog.d("Type -->: " + types[0]);

                    if (callBack != null) {
                        callBack.onSucceess((T) new Gson().fromJson(result, finalNeedType));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (t != null)
                    t.printStackTrace();
            }
        });
        return null;
    }


    private ICallBackStatusCode mCallBackStatusCode;

    /**
     * @param callBackStatusCode
     */
    public void setRetrofitCallBackStatusCode(ICallBackStatusCode callBackStatusCode) {
        this.mCallBackStatusCode = callBackStatusCode;
    }

    private ICallBackResultCode mCallBackResultCode;

    /**
     * @param callBackResultCode
     */
    public void setRetrofitCallBackResultCode(ICallBackResultCode callBackResultCode) {
        this.mCallBackResultCode = callBackResultCode;
    }


    /**
     * ResponseCallBack <T> Support your custom data model
     */
    public interface ResponseCallBack<T> {

        public abstract void onSucceess(T response);

        public abstract void onFailure(Throwable e);

    }
}


