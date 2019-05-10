package com.example.stocktrainingpractice.Model.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooFinanceApi {
    @GET("rss/2.0/headline")
    Observable<NewsData> getNews(@Query("regio") String region,
                           @Query("lang") String lang,
                           @Query("s") String symbol);
}
