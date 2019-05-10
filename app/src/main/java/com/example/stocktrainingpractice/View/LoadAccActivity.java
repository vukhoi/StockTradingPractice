package com.example.stocktrainingpractice.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.stocktrainingpractice.Presenter.LoadAccActivity.LoadAccActivityPresenter;
import com.example.stocktrainingpractice.Presenter.LoadAccActivity.LoadAccActivityPresenterImplementation;
import com.example.stocktrainingpractice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoadAccActivity extends AppCompatActivity {
    Unbinder unbinder;
    @BindView(R.id.rv_acc)
    RecyclerView recyclerView;
    LoadAccActivityPresenter loadAccActivityPresenter = new LoadAccActivityPresenterImplementation(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_acc);

        unbinder = ButterKnife.bind(this);

        loadAccActivityPresenter.setUpRecyclerView(recyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
