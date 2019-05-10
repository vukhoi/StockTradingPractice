package com.example.stocktrainingpractice.Model.Quote;

import com.example.stocktrainingpractice.Util;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StockQuoteGetter{
    private Retrofit retrofit;
    private static StockQuoteGetter INSTANCE;
    private StockQuoteApi api;

    private StockQuoteGetter(){
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://cloud.iexapis.com/")
                .build();
        api = retrofit.create(StockQuoteApi.class);
    }


    public Observable<StockQuote> getStockQuote(String symbol) {
        return api.getStockQuote(symbol, Util.IEX_API_KEY);
    }

    public Call<StockQuote> testStockQuote(String symbol) {
        return api.testStockQuote(symbol, Util.IEX_API_KEY);
    }

    public static StockQuoteGetter getINSTANCE(){
        if (INSTANCE == null){
            synchronized (StockQuoteGetter.class){
                if(INSTANCE == null){
                    INSTANCE = new StockQuoteGetter();
                }
            }
        }
        return INSTANCE;
    }
}
