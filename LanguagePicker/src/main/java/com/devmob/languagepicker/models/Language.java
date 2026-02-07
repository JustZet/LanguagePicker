package com.devmob.languagepicker.models;

import com.devmob.languagepicker.helpers.LanguageHelper;

import java.util.Locale;

public class Language implements Cloneable {
    private final String languageCode;
    private final String languageName;
    private final String countryCode;
    private final String countryName;
    private final Locale locale;
    private final int orderId;

    public Language(String languageCode, String languageName,
                    String countryCode, String countryName, int orderId) {
        this.languageCode = languageCode;
        this.languageName = languageName;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.orderId = orderId;

        this.locale = new Locale(languageCode, countryCode);
    }

    public String getLanguageCode() { return languageCode; }
    public String getLanguageName() { return languageName; }
    public String getCountryCode() { return countryCode; }
    public String getCountryName() { return countryName; }
    public Locale getLocale() { return locale; }
    public int getOrderId() { return orderId; }
    public String getIcon() { return LanguageHelper.getLanguageIcon(countryCode); }

    @Override
    public String toString() {
        return languageName + " (" + countryName + ") - " +
                languageCode + "_" + countryCode;
    }
}