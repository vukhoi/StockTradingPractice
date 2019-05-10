package com.example.stocktrainingpractice.Model.News;

import com.example.stocktrainingpractice.Util;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NewsGetter {
    private Retrofit retrofit;
    private static NewsGetter INSTANCE;
    private YahooFinanceApi api;

    private NewsGetter(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("https://feeds.finance.yahoo.com/")
                .build();
        api = retrofit.create(YahooFinanceApi.class);
    }


    public Observable<NewsData> getHistoricalPrice(String symbol) {
        return api.getNews(Util.REGION, Util.LANG, symbol);
    }

    public static NewsGetter getINSTANCE(){
        if (INSTANCE == null){
            synchronized (NewsGetter.class){
                if(INSTANCE == null){
                    INSTANCE = new NewsGetter();
                }
            }
        }
        return INSTANCE;
    }
}
