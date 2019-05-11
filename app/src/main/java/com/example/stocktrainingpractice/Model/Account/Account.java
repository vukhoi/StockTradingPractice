package com.example.stocktrainingpractice.Model.Account;

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
    private List<History> historyList;

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

    public void addStock(Stock stock){
        this.stockList.add(stock);
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public void addHistory(History history){
        this.historyList.add(history);
    }
}
