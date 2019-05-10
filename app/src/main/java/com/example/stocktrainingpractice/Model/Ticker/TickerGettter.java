package com.example.stocktrainingpractice.Model.Ticker;

import android.app.DownloadManager;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.stocktrainingpractice.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class TickerGettter {
    private String[] exchangeList = {"NASDAQ, AMEX, NYSE"};
    private final String tickerListFileName = Util.FILE_DIR + "ticker_list";
    Context context;

    public TickerGettter(Context context) {
        this.context = context;
    }


    public List<String> getTickers() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Calendar calendarInstance = Calendar.getInstance();
        String today = calendarInstance.get(Calendar.DAY_OF_MONTH) + " " + calendarInstance.get(Calendar.MONTH);
        //if(!sharedPreferences.getString(Util.LAST_TICKER_DOWNLOAD_DATE, "").equals(today)){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Util.LAST_TICKER_DOWNLOAD_DATE, today);
        editor.apply();
        Log.d("sPTest", sharedPreferences.getString(Util.LAST_TICKER_DOWNLOAD_DATE, " no date"));
        downloadAllTickerLists();
        //}
        return readTickerFile();
    }


    private void downloadFile(String exchange, String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(exchange);
        request.setTitle(exchange);

        request.setDestinationUri(Uri.parse(Util.FILE_DIR + exchange + ".csv"));
        Log.d("Uri Test", String.valueOf(Uri.parse(Util.FILE_DIR + exchange + ".csv")));

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }


    private void downloadAllTickerLists() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("counterTest", String.valueOf(Util.TICKERLIST_DOWNLOAD_COUNT.getValue()));
                Util.TICKERLIST_DOWNLOAD_COUNT.postValue(Util.TICKERLIST_DOWNLOAD_COUNT.getValue() + 1);
            }
        };

        context.getApplicationContext()
                .registerReceiver(broadcastReceiver,
                        new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


        Util.TICKERLIST_DOWNLOAD_COUNT.observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == 2) {
                    Log.d("observeCounterTest", String.valueOf(integer));
                    Util.TICKERLIST_DOWNLOAD_COUNT.setValue(0);
                    writeTickerFile();
                    context.unregisterReceiver(broadcastReceiver);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickerList;
    }


    private void writeTickerFile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String exchange : exchangeList) {
            stringBuilder.append(readRawCsvToFormattedString(exchange));
        }
        try {
            PrintWriter printWriter = new PrintWriter(tickerListFileName);
            printWriter.println(stringBuilder.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String readRawCsvToFormattedString(String exchange) {
        FileInputStream fileIn = null;
        try {
            String filePath = Util.FILE_DIR + exchange + ".csv";
            fileIn = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileIn));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            boolean firstlLine = true;
            while ((line = bufferedReader.readLine()) != null) {
                if(firstlLine){
                    firstlLine = false;
                    continue;
                }
                String[] tickerInfo = line.split("\",\"");
                stringBuilder.append(tickerInfo[0].substring(1) + "°" + tickerInfo[1]);
                stringBuilder.append(",");
            }

            new File(filePath).delete();

            return stringBuilder.toString();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
