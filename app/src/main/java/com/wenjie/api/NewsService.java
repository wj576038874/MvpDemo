package com.wenjie.api;

import com.wenjie.entity.BaseNewsPageData;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * ProjectName: MvpDemo
 * PackageName com.wenjie.api
 * Author: wenjie
 * Date: 2019-05-05 11:10
 * Description:
 */
public interface NewsService {

    @GET("http://api01.idataapi.cn:8000/news/qihoo?kw=%E5%86%9B%E4%BA%8B&site=qq.com&apikey=xFWiP8vUr6WmUf6Dr24kZEaZfLNayDhJ7sTMYnXEVmvhOcEYTQ7zDiuDKAEn2Bp")
    Observable<Response<BaseNewsPageData>> getNews();
}
