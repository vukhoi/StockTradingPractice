package com.example.stocktrainingpractice.Model.Ticker;

import android.app.DownloadManager;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stocktrainingpractice.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class TickerGettter {
    private String[] exchangeList = {"NASDAQ", "AMEX", "NYSE"};
    private final String tickerListFileName = Util.FILE_DIR + "ticker_list";
    static List<String> finishedList = new ArrayList<>();
    private List<Long> idList = new ArrayList<>();
    static StringBuilder tickerStringBuilder = new StringBuilder();
    static DownloadManager downloadManager;
    Context context;


    public TickerGettter(Context context) {
        this.context = context;
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }


    public void downloadTickers() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Calendar calendarInstance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(calendarInstance.getTime());
        Log.d("DOWNLOADTICKERS", today);
//        if (!sharedPreferences.getString(Util.LAST_TICKER_DOWNLOAD_DATE, "").equals(today)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Util.LAST_TICKER_DOWNLOAD_DATE, today);
            editor.apply();
            downloadAllTickerLists();
//        }
    }

    public List<String> getTickers(){
        return readTickerFile();
    }


    private void downloadFile(String exchange, String url) {
        Log.d("DOWNLOAD", exchange);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(exchange);
        request.setTitle(exchange);
//        request.setDestinationInExternalPublicDir(Util.FILE_DIR, exchange+".csv");
        idList.add(downloadManager.enqueue(request));
    }


    private void downloadAllTickerLists() {
        TickerBroadcastReceiver tickerBroadcastReceiver = new TickerBroadcastReceiver();
        try {
            context.getApplicationContext()
                    .registerReceiver(tickerBroadcastReceiver,
                            new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }

        Util.TICKERLIST_DOWNLOAD_COUNT.observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.d(this.getClass().getSimpleName(), "onChanged " + integer);
                if (integer == null){
                    throw new NullPointerException();
                }
                else if (integer == 3) {
                    Util.TICKERLIST_DOWNLOAD_COUNT.setValue(0);
                    writeTickerFile();
                    try {
                        context.unregisterReceiver(tickerBroadcastReceiver);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        String baseUrl = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=";
        for (String exchange : exchangeList) {
            downloadFile(exchange, baseUrl + exchange + "&render=download");
        }
    }


    private List<String> readTickerFile() {
        List<String> tickerList = new ArrayList<>();
        StringBuilder tickers = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(tickerListFileName);
            int content;
            while ((content = fileInputStream.read()) != -1) {
                tickers.append((char) content);
            }
            tickerList = Arrays.asList(tickers.toString().split(","));

            if(tickerList.get(tickerList.size()-1) == null){
                tickerList.remove(tickerList.size()-1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickerList;
    }


    private void writeTickerFile() {
        Log.d("writeTicker", tickerStringBuilder.toString().substring(0, 200));
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(tickerListFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (printWriter != null) {
            printWriter.println(tickerStringBuilder.toString());
        }
        printWriter.close();

        File folder = new File(Util.FILE_DIR);
        File[] listOfFiles = folder.listFiles();
        for (File file: listOfFiles){
            Log.d("files", file.getPath());
        }

        for (Long id: idList){
            downloadManager.remove(id);
        }

    }
}
