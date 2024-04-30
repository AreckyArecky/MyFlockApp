/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.myflock.myflockapp.entity.finances;

import java.util.ArrayList;
import java.util.List;

/**
 * WROTE MY OWN MONTH ENUM - FOR THIS PROGRAM PURPOSES. DEFAULT DID NOT SUIT ME.
 */
public enum MonthPicker {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public int getValue(MonthPicker month) {
        int value = 0;
        switch (month) {
            case JANUARY:
                value = 1;
                break;
            case FEBRUARY:
                value = 2;
                break;
            case MARCH:
                value = 3;
                break;
            case APRIL:
                value = 4;
                break;
            case MAY:
                value = 5;
                break;
            case JUNE:
                value = 6;
                break;
            case JULY:
                value = 7;
                break;
            case AUGUST:
                value = 8;
                break;
            case SEPTEMBER:
                value = 9;
                break;
            case OCTOBER:
                value = 10;
                break;
            case NOVEMBER:
                value = 11;
                break;
            case DECEMBER:
                value = 12;
                break;

        }
        return value;
    }

    public static int getMonthNumberFromString(String month) {
        int value = 0;
        switch (month) {
            case "JANUARY":
                value = 1;
                break;
            case "FEBRUARY":
                value = 2;
                break;
            case "MARCH":
                value = 3;
                break;
            case "APRIL":
                value = 4;
                break;
            case "MAY":
                value = 5;
                break;
            case "JUNE":
                value = 6;
                break;
            case "JULY":
                value = 7;
                break;
            case "AUGUST":
                value = 8;
                break;
            case "SEPTEMBER":
                value = 9;
                break;
            case "OCTOBER":
                value = 10;
                break;
            case "NOVEMBER":
                value = 11;
                break;
            case "DECEMBER":
                value = 12;
                break;

        }
        return value;
    }

//    public List<MonthPicker> toList() {
//        List<MonthPicker> allMonths = new ArrayList<>();
//        
//        
//    }
}
