package com.ric.fab.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    @Expose
   static String nome;
    @Expose
    static String tipo;
    @Expose
    static String data;
    @Expose
    static Long size;
    @Expose
    static String tag;
    @Expose
    static String modifica;
    @Expose
   static int altezza;
    @Expose
   static int lunghezza;

    static List<DataBase> dataBaseList = new ArrayList<>();

    public DataBase( String nome, String modifica, Long size, String tipo, int altezza, int lunghezza) {
        this.nome = nome;
        this.modifica = modifica;
        this.size = size;
        this.tipo = tipo;
        this.altezza = altezza;
        this.lunghezza = lunghezza;

    }

    public static String getNome() {
        return nome;
    }

    public static String getData() {
        return data;
    }

    public  static Long getSize() {
        return size;
    }

    public static String getTag() {
        return tag;
    }

    public static  int getLunghezza() {
        return lunghezza;
    }

    public  static int getAltezza() {
        return altezza;
    }

    public String getModifica() {
        return modifica;
    }

    public String getTipo() {
        return tipo;
    }

    public static List<DataBase> getDataBaseList() {
        return dataBaseList;
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", data='" + data + '\'' +
                ", size=" + size +
                ", tag='" + tag + '\'' +
                ", modifica='" + modifica + '\'' +
                ", altezza=" + altezza +
                ", lunghezza=" + lunghezza +
                '}';
    }
}
