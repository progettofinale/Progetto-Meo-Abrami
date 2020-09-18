package com.ric.fab.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 *database che contiene i dati dei file
 */
public class FileData {

    @Expose
    String path;

    /**costruttore di filedata
     * @param path String: contiene il percorso del file
     */
    public FileData(String path) {
        this.path=path;
    }

    /**restituisce una stringa contenente il path
     * @return restituisce il path del file
     */
    public  String getPath() {
        return path;
    }

    static List<FileData> fileDataList = new ArrayList<>();

    /**restituisce una lista di filedata
     * @return  filedatalist di tipo listfiledata
     */
    public static List<FileData> getFileDataList() {
        return fileDataList;
    }
}
