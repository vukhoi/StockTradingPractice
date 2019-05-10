package com.example.stocktrainingpractice.Model.Quote;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StockQuoteApi {
    @GET("v1/stock/{symbol}/quote")
    Observable<StockQuote> getStockQuote(@Path("symbol") String symbol,
                                         @Query("token") String token);

    @GET("v1/stock/{symbol}/quote")
    Call<StockQuote> testStockQuote(@Path("symbol") String symbol,
                                    @Query("token") String token);
}
