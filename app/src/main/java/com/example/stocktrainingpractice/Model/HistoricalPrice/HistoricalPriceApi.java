package com.example.stocktrainingpractice.Model.HistoricalPrice;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HistoricalPriceApi {
    @GET("1.0/stock/{symbol}/chart/{numYear}")
    Observable<List<Day>> getHistoricalPrice(@Path("symbol") String symbol,
                                             @Path("numYear") String numYear);
}
