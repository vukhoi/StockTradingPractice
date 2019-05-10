package com.example.stocktrainingpractice;


import android.arch.lifecycle.MutableLiveData;

public class Util {
    public static final String IEX_API_KEY = "pk_95225a3e24df462b8d91e3b0487bb7ef";
    public static final String LANG = "en-US";
    public static final String REGION = "US";
    public static String FILE_DIR;
    public static String ACC_SERIALIZABLE_KEY = "ACC";
    public static MutableLiveData<Integer> TICKERLIST_DOWNLOAD_COUNT = new MutableLiveData<>();
    public static final String LAST_TICKER_DOWNLOAD_DATE = "lastTickerDownloadDate";

}
