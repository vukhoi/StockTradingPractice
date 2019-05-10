package com.example.stocktrainingpractice.Model.HistoricalPrice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("open")
    @Expose
    private Double open;
    @SerializedName("high")
    @Expose
    private Double high;
    @SerializedName("low")
    @Expose
    private Double low;
    @SerializedName("close")
    @Expose
    private Double close;
    @SerializedName("volume")
    @Expose
    private Integer volume;
    @SerializedName("unadjustedVolume")
    @Expose
    private Integer unadjustedVolume;
    @SerializedName("change")
    @Expose
    private Double change;
    @SerializedName("changePercent")
    @Expose
    private Double changePercent;
    @SerializedName("vwap")
    @Expose
    private Double vwap;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("changeOverTime")
    @Expose
    private Double changeOverTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getUnadjustedVolume() {
        return unadjustedVolume;
    }

    public void setUnadjustedVolume(Integer unadjustedVolume) {
        this.unadjustedVolume = unadjustedVolume;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    public Double getVwap() {
        return vwap;
    }

    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getChangeOverTime() {
        return changeOverTime;
    }

    public void setChangeOverTime(Double changeOverTime) {
        this.changeOverTime = changeOverTime;
    }

}