package com.revolut.noble.android.task.viewmodel.model;

public class Currency {

    private String name;
    private Float koef;

    private Currency(String name, Float koef) {
        this.name = name;
        this.koef = koef;
    }

    public static Currency create(String name, Float koef) {
        return new Currency(name, koef);
    }


    public String getName() {
        return name;
    }

    public Float getKoef() {
        return koef;
    }

    public void setKoef(Float koef) {
        this.koef = koef;
    }
}
