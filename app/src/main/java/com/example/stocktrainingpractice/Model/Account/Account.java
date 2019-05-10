package com.example.stocktrainingpractice.Model.Account;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String accName;
    private Double cashAmt;
    private Double equityAmt;
    private Double transactionFee;
    private Boolean dayTradeRestriction;
    private List<Stock> stockList;

    public Account(String accName, Double cashAmt, Double transactionFee, Boolean dayTradeRestriction) {
        this.accName = accName;
        this.cashAmt = cashAmt;
        this.equityAmt = 0.0;
        this.transactionFee = transactionFee;
        this.dayTradeRestriction = dayTradeRestriction;
        this.stockList = new ArrayList<>();
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public Double getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(Double cashAmt) {
        this.cashAmt = cashAmt;
    }

    public Double getEquityAmt() {
        return equityAmt;
    }

    public void setEquityAmt(Double equityAmt) {
        this.equityAmt = equityAmt;
    }

    public Double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Boolean getDayTradeRestriction() {
        return dayTradeRestriction;
    }

    public void setDayTradeRestriction(Boolean dayTradeRestriction) {
        this.dayTradeRestriction = dayTradeRestriction;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
