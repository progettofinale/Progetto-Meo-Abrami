package com.ric.fab.utils;

import com.ric.fab.data.DataBase;

import java.util.List;

public class Output {
    public static String output_Dimensioni(List<DataBase> lista){
        return "Le dimensioni sono: \n"+DataBase.getSize()+"\n"+DataBase.getAltezza()+"\n"+DataBase.getLunghezza();
    }
}
