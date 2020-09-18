package com.ric.fab.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class FolderData {

    @Expose
     String path;

    /**costruttore
     * @param path String: prende il path
     */
    public FolderData(String path) {
        this.path=path;
    }

    /**restituisce il valore del path
     * @return restituisce il valore della variabile
     */
    public String getPath() {
        return path;
    }

    static List<FolderData> folderDataList = new ArrayList<>();

    /**restituisce la lista di folder presente in folderdata
     * @return restituisce una lista di folderdata
     */
    public static List<FolderData> getFolderDataList() {
        return folderDataList;
    }
}
