package com.example.stocktrainingpractice.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.stocktrainingpractice.Model.Ticker.TickerGettter;
import com.example.stocktrainingpractice.R;
import com.example.stocktrainingpractice.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFilesDirConstant();

        unbinder = ButterKnife.bind(this);

        TickerGettter tickerGettter = new TickerGettter(this);
        tickerGettter.getTickers();
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
}
