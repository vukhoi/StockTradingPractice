package com.example.stocktrainingpractice.Presenter.LoadAccActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stocktrainingpractice.Model.Account.Account;
import com.example.stocktrainingpractice.Model.Account.Stock;
import com.example.stocktrainingpractice.R;
import com.example.stocktrainingpractice.Util;
import com.example.stocktrainingpractice.View.PortfolioActivity;

import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context context;
    private List<Account> accountList;


    public CustomAdapter(Context context, List<Account> accountList){
        Log.d("accListSize", accountList.size() + "");
        this.context = context;
        this.accountList = accountList;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        LinearLayout rootView = (LinearLayout)layoutInflater.from(context).inflate(R.layout.account_item_view_holder, viewGroup, false);
        if (rootView.getParent() != null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        return new CustomViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        Account currentAccount = accountList.get(i);

        customViewHolder.tvAccName.setText(currentAccount.getAccName());
        customViewHolder.tvCashAmt.setText(currentAccount.getCashAmt().toString());
        customViewHolder.tvEquityAmt.setText(currentAccount.getEquityAmt().toString());
        customViewHolder.tvStockList.setText(formatStockList(currentAccount.getStockList()));
        customViewHolder.llAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PortfolioActivity.class);
                intent.putExtra(Util.ACC_SERIALIZABLE_KEY, currentAccount);
            }
        });
    }


    private String formatStockList(List<Stock> stockList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Stock stock: stockList){
            stringBuilder.append(stock.getName() +
                    ", amt=" + stock.getAmt() +
                    ", avgCost=" + stock.getAvgCost() +
                    " ; ");
        }
        return stringBuilder.toString();
    }


    @Override
    public int getItemCount() {
        return accountList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView tvAccName;
        TextView tvCashAmt;
        TextView tvEquityAmt;
        TextView tvStockList;
        LinearLayout llAccount;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            llAccount = itemView.findViewById(R.id.ll_account);
            tvAccName = itemView.findViewById(R.id.tv_acc_name);
            tvCashAmt = itemView.findViewById(R.id.tv_cash_amt_num);
            tvEquityAmt = itemView.findViewById(R.id.tv_equity_amt_num);
            tvStockList = itemView.findViewById(R.id.tv_stock_list);
        }
    }
}
