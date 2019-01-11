package com.wenjie.base;


import android.support.annotation.NonNull;
import com.wenjie.utils.CacheUtil;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.base
 * Author: wenjie
 * Date: 2019-01-11 12:18
 * Description:
 */
public class BaseImpl<Service> {
    protected Service mService;
    private static Retrofit mRetrofit;
    private static CacheUtil cacheUtil = new CacheUtil(MyApplication.getContext());

    public BaseImpl() {
        initRetrofit();
        this.mService = mRetrofit.create(getServiceClass());
    }

    private void initRetrofit() {
        if (null != mRetrofit)
            return;
        // 配置 client
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new BaseInterceptor())        // 添加请求拦截器
                .retryOnConnectionFailure(true)                      // 是否重试
                .connectTimeout(30, TimeUnit.SECONDS)        // 连接超时事件
                .readTimeout(30, TimeUnit.SECONDS)           // 读取超时时间
                .build();

        // 配置 Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://diycode.cc/api/v3/")// 设置 base url
                .client(client)                                     // 设置 client
                .addConverterFactory(GsonConverterFactory.create()) // 设置 Json 转换工具
//                .addConverterFactory(ScalarsConverterFactory.create())//添加primitives, boxed, and String转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * 请求拦截器
     */
    private class BaseInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();
            // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
            if (null == cacheUtil.getToken() || alreadyHasAuthorizationHeader(original)) {
                return chain.proceed(original);
            }
            String token = "Bearer " + cacheUtil.getToken().getAccess_token();
            Request request = original.newBuilder()
                    .header("Authorization", token)
                    .build();
            return chain.proceed(request);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Service> getServiceClass() {
        return (Class<Service>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private static boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        String token = originalRequest.header("Authorization");
        // 如果本身是请求 token 的 URL，直接返回 true
        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
        return !(null == token || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"));
    }

}
