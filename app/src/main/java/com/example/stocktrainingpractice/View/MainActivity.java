package com.example.stocktrainingpractice.View;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.stocktrainingpractice.Model.Ticker.TickerGettter;
import com.example.stocktrainingpractice.R;
import com.example.stocktrainingpractice.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class MainActivity extends AppCompatActivity {
    Unbinder unbinder;
    boolean readPermissionGranted = true;
    boolean writePermissionGranted = true;
    boolean internetPermissionGranted = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFilesDirConstant();
        getPermissions();

        unbinder = ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_create_new_acc)
    void startCreateNewAccountActivity(){
        Intent intent = new Intent(this, CreateNewAccActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_load_acc)
    void startLoadAccountActivity(){
        Intent intent = new Intent(this, LoadAccActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    private void setFilesDirConstant() {
        Util.FILE_DIR = getFilesDir().getPath() + "/";
        Util.TICKERLIST_DOWNLOAD_COUNT.setValue(0);
    }


    private boolean grantPermission(String permission, int requestCode) {

        long checkPermission = ContextCompat.checkSelfPermission(this, permission);
        if (checkPermission != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }


    private void getPermissions() {
        Log.d(this.getClass().getSimpleName(), "getPermimssion");
        internetPermissionGranted = grantPermission(INTERNET, Util.INTERNET_PERMISSION_REQUESTCODE);
        writePermissionGranted = grantPermission(WRITE_EXTERNAL_STORAGE, Util.WRITE_PERMISSION_REQUESTCODE);
        readPermissionGranted = grantPermission(READ_EXTERNAL_STORAGE, Util.READ_PERMISSION_REQUESTCODE);
        if (internetPermissionGranted && readPermissionGranted && writePermissionGranted){
            Log.d(this.getClass().getSimpleName(), "getTicker");
            TickerGettter tickerGettter = new TickerGettter(this);
            tickerGettter.downloadTickers();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(this,"require internet, read and write storage permission",Toast.LENGTH_LONG).show();
        }
        else{
            switch (requestCode){
                case Util.INTERNET_PERMISSION_REQUESTCODE:
                    internetPermissionGranted = true;
                    break;
                case Util.READ_PERMISSION_REQUESTCODE:
                    readPermissionGranted = true;
                    break;
                case Util.WRITE_PERMISSION_REQUESTCODE:
                    writePermissionGranted = true;
                    break;
            }
            if (internetPermissionGranted && readPermissionGranted && writePermissionGranted){
                Log.d(this.getClass().getSimpleName(), "getTicker");
                TickerGettter tickerGettter = new TickerGettter(this);
                tickerGettter.downloadTickers();            }
        }
    }
}
