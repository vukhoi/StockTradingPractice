package com.example.stocktrainingpractice.Model.Account;

import java.io.Serializable;

public class Stock implements Serializable {
    private String name;
    private Double avgCost;
    private Double equity;
    private Integer amt;
    private Double currentReturn;
    private Double totalReturn;

    public Stock(String name, Double avgCost, Integer amt) {
        this.name = name;
        this.avgCost = avgCost;
        this.amt = amt;
        this.equity = avgCost*amt;
        this.currentReturn = 0.0;
        this.totalReturn = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(Double avgCost) {
        this.avgCost = avgCost;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public Double getCurrentReturn() {
        return currentReturn;
    }

    public void setCurrentReturn(Double currentReturn) {
        this.currentReturn = currentReturn;
    }

    public Double getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(Double totalReturn) {
        this.totalReturn = totalReturn;
    }
}
