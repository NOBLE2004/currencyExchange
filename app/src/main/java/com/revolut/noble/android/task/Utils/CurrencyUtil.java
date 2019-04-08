package com.revolut.noble.android.task.Utils;

import com.mynameismidori.currencypicker.ExtendedCurrency;


/**
 * Created by Noble on 06.04.2019.
 *
 */

public final class CurrencyUtil {

    private CurrencyUtil() {}

    public static int getFlagResByISO(String iso){
        for(ExtendedCurrency currency: ExtendedCurrency.getAllCurrencies())
            if(currency.getCode().equals(iso))
                return currency.getFlag();
        return -1;
    }

    public static String getCurrencyNameResByISO(String iso){
        for(ExtendedCurrency currency: ExtendedCurrency.getAllCurrencies())
            if(currency.getCode().equals(iso))
                return currency.getName();
        return "";
    }

}
