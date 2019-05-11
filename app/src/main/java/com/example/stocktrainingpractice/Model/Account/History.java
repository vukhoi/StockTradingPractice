package com.example.stocktrainingpractice.Model.Account;

import java.io.Serializable;

public class History implements Serializable {
    private Double cashAmt;
    private Double equityAmt;
    private String date;

    History(Double cashAmt, Double equityAmt, String date) {
        this.cashAmt = cashAmt;
        this.equityAmt = equityAmt;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
