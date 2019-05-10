package com.example.stocktrainingpractice.Presenter.LoadAccActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stocktrainingpractice.Model.Account.Account;
import com.example.stocktrainingpractice.Model.Account.AccountManager;
import com.example.stocktrainingpractice.Model.Account.AccountManagerImplementation;

import java.util.List;

public class LoadAccActivityPresenterImplementation implements LoadAccActivityPresenter {
    private Context context;

    public LoadAccActivityPresenterImplementation(Context context){
        this.context = context;
    }

    @Override
    public void setUpRecyclerView(RecyclerView recyclerView) {
        List<Account> accountList = getAccountList();
        CustomAdapter customAdapter = new CustomAdapter(context, accountList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }



    private List<Account> getAccountList(){
        AccountManager accountManager = AccountManagerImplementation.getInstance();
        return accountManager.getAllAccount();
    }
}
