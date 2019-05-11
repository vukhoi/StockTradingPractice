package com.example.stocktrainingpractice.Model.Ticker;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.stocktrainingpractice.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.stocktrainingpractice.Model.Ticker.TickerGettter.downloadManager;
import static com.example.stocktrainingpractice.Model.Ticker.TickerGettter.finishedList;
import static com.example.stocktrainingpractice.Model.Ticker.TickerGettter.tickerStringBuilder;

public class TickerBroadcastReceiver extends BroadcastReceiver {

    public TickerBroadcastReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(this.getClass().getSimpleName(), "onReceived");
        Bundle extras = intent.getExtras();
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(extras.getLong(DownloadManager.EXTRA_DOWNLOAD_ID));
        Cursor cursor = downloadManager.query(query);

        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                int index = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                String path = getRealPath(context, Uri.parse(cursor.getString(index)));
//                int index = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE);
//                String path = cursor.getString(index) + ".csv";
                tickerStringBuilder.append(readCsvToFormattedString(path));
                finishedList.add(path);
                Util.TICKERLIST_DOWNLOAD_COUNT.postValue(Util.TICKERLIST_DOWNLOAD_COUNT.getValue() + 1);
            }
        }
    }


    private String getRealPath(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(contentUri,
                    new String[]{MediaStore.Files.FileColumns.DATA},
                    null, null, null);

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            cursor.moveToFirst();

            return cursor.getString(columnIndex);

        } finally {
            if (cursor != null) cursor.close();
        }
    }

    private String readCsvToFormattedString(String filePath) {
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileIn));

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            boolean firstlLine = true;
            while ((line = bufferedReader.readLine()) != null) {
                if (firstlLine) {
                    firstlLine = false;
                    continue;
                }
                String[] tickerInfo = line.split("\",\"");
                stringBuilder.append(tickerInfo[0].substring(1) + "°" + tickerInfo[1]);
                stringBuilder.append(",");
            }

            new File(filePath).delete();
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
