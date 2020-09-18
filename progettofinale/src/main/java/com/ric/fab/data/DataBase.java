package com.ric.fab.data;

import java.util.ArrayList;
import java.util.List;

/**
 * salva/immagazzina i valori
 */
public class DataBase {

    String nome;

    String tipo;

    String data;

    Long size;

    String tag;

    String modifica;

    int altezza;

    int lunghezza;

    long durata;

    static List<DataBase> dataBaseList = new ArrayList<>();



    /**
     * costruttore del database
     *
     * @param nome      String: prende il nome
     * @param modifica  String: ultima modifica del file
     * @param size      Long: peso del file
     * @param tipo      String: tipo del file
     * @param altezza   int: altezza
     * @param lunghezza int: lunghezza
     * @param durata Long: durata
     */
    public DataBase(String nome, String modifica, Long size, String tipo, int altezza, int lunghezza, long  durata) {
        this.nome = nome;
        this.modifica = modifica;
        this.size = size;
        this.tipo = tipo;
        this.altezza = altezza;
        this.lunghezza = lunghezza;
        this.durata = durata;

    }
    private static List<DataBase> subdatalist = new ArrayList<>();
    public static List<DataBase> getsubdatalist(){return subdatalist;}

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public Long getSize() {
        return size;
    }

    public String getTag() {
        return tag;
    }

    public int getLunghezza() {
        return lunghezza;
    }

    public int getAltezza() {
        return altezza;
    }

    public String getModifica() {
        return modifica;
    }

    public String getTipo() {
        return tipo;
    }

    public long getDurata() {
        return durata;
    }

    public static List<DataBase> getDataBaseList() {
        return dataBaseList;
    }

    public String toString() {
        return "\nFilterDataBase{" +
                "\n\t\"nome\":\"" + nome + '\"' +
                ",\n\t\"tipo\":\"" + tipo + '\"' +
                ",\n\t\"size\":" + size +
                ",\n\t\"modifica\":\"" + modifica + '\"' +
                ",\n\t\"altezza\":" + altezza +
                ",\n\t\"lunghezza\":" + lunghezza +
                ",\n\t\"durata\":" + durata +
                "\n}";
    }

}