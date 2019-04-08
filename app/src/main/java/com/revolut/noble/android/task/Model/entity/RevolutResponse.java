package com.revolut.noble.android.task.Model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.TreeMap;


public class RevolutResponse {

        @SerializedName("base")
        @Expose
        private String base;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("rates")
        @Expose
        private TreeMap<String, Float> rates;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public TreeMap<String, Float> getRates() {
            return rates;
        }

        public void setRates(TreeMap<String, Float> rates) {
            this.rates = rates;
        }
}
