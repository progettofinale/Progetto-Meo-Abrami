package com.ric.fab.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class FolderData {

    @Expose
     String path;

    public FolderData(String path) {
        this.path=path;
    }

    public String getPath() {
        return path;
    }

    static List<FolderData> folderDataList = new ArrayList<>();

    public static List<FolderData> getFolderDataList() {
        return folderDataList;
    }
}
