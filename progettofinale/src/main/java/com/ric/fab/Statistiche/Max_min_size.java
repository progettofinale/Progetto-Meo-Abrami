package com.ric.fab.Statistiche;

import com.ric.fab.data.DataBase;

import java.util.List;

public class Max_min_size {
    static long  tempmax=0;
    static long tempmin;
    public Max_min_size(List<DataBase> datalist){
        tempmin=datalist.get(0).getSize();
        for (DataBase data :datalist) {
            if(data.getSize()>tempmax){
            tempmax=data.getSize();
            }
            if(data.getSize()<tempmin){
                tempmin=data.getSize();
            }
        }

    }

    public static long getTempmax() {
        return tempmax;
    }

    public static long getTempmin() {
        return tempmin;
    }
}
