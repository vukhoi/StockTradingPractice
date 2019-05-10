package com.example.stocktrainingpractice.Model.HistoricalPrice;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoricalPriceGetter {
    private Retrofit retrofit;
    private static HistoricalPriceGetter INSTANCE;
    private HistoricalPriceApi api;

    private HistoricalPriceGetter(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.iextrading.com/")
                .build();
        api = retrofit.create(HistoricalPriceApi.class);
    }


    public Observable<List<Day>> getHistoricalPrice(String symbol, int numYear) {
        return api.getHistoricalPrice(symbol, String.valueOf(numYear)+"y");
    }

    public static HistoricalPriceGetter getINSTANCE(){
        if (INSTANCE == null){
            synchronized (HistoricalPriceGetter.class){
                if(INSTANCE == null){
                    INSTANCE = new HistoricalPriceGetter();
                }
            }
        }
        return INSTANCE;
    }
}
