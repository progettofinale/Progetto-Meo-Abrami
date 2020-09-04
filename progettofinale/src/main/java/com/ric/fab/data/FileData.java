package com.ric.fab.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class FileData {

    @Expose
    String path;

    public FileData(String path) {
        this.path=path;
    }

    public  String getPath() {
        return path;
    }

    static List<FileData> fileDataList = new ArrayList<>();

    public static List<FileData> getFileDataList() {
        return fileDataList;
    }
}
