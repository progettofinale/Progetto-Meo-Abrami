package com.ric.fab.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ric.fab.data.FileData;
import com.ric.fab.data.FolderData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {
    static String path;

    public static int getFolderFinded() {
        return folderFinded;
    }

    public static void addFinded() {
        Utils.folderFinded = Utils.folderFinded+1;
    }

    static  int folderFinded=0;

    public static void setPath(String path) {
        Utils.path = path;
    }

    public static String getPath() {
        return path;
    }
    static boolean fileFound=false;

    public static String bodyGenerator(String path2, String tag){
    String body;
    if(tag.equals("folder")){
    body="{\r\n" + "    \"path\": \""+path2+"\",\r\n" + "    \"recursive\": true,\r\n"
            + "    \"include_media_info\": false,\r\n" + "    \"include_deleted\": false,\r\n"
            + "    \"include_has_explicit_shared_members\": false,\r\n"
            + "    \"include_mounted_folders\": true,\r\n" + "    \"include_non_downloadable_files\": true\r\n"
            + "}";}
    else{body = "{\r\n" +
            "    \"path\": \""+path2+"\",\r\n" +
            "    \"include_media_info\": true,\r\n" +
            "    \"include_deleted\": false,\r\n" +
            "    \"include_has_explicit_shared_members\": false\r\n" +
            "}";}
    return body;
}
    public static void commonReader(BufferedReader buf) {
        JsonElement je1 = new JsonParser().parse(buf);
        JsonObject all=je1.getAsJsonObject();

        if(all.size()!=1) {
            JsonElement element = all.get("entries");
            JsonArray entries = element.getAsJsonArray();
            System.out.println(entries.size()+" numero elementi");
            for (int i = 0; i < entries.size(); i++) {
                JsonElement je3 = entries.get(i);
                JsonObject singleFoto = je3.getAsJsonObject();
                JsonElement tag = singleFoto.get(".tag");
                JsonElement path = singleFoto.get("path_display");
                String tag2 = tag.getAsString();

                String path2 = path.getAsString();
                if(tag2.equals("file")) {
                    FileData.getFileDataList().add(new FileData(path2));
                }else{
                    FolderData.getFolderDataList().add(new FolderData(path2));
                    addFinded();
                }
            }
        }
    }

    public static void lettoreFile(String http,String jsonBody) throws MalformedURLException {
        URL url= new URL(http);
        try {
            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("POST");
            openConnection.setRequestProperty("Authorization",
                    "Bearer khpXnv7sjrwAAAAAAAAAAXdr6kgKYwoEmWHkBZtg8KjNuLey7Zrj_v8Fy36z4sbF");
            openConnection.setRequestProperty("Content-Type", "application/json");
            openConnection.setRequestProperty("Accept", "application/json");
            openConnection.setDoOutput(true);
            openConnection.connect();
            try (OutputStream os = openConnection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

            }
            if (openConnection.getResponseCode()==400) {
                return;
            }
            try (InputStream in = openConnection.getInputStream()) {
                InputStreamReader inR = new InputStreamReader(in);
                BufferedReader buf = new BufferedReader(inR);

                    Utils.commonReader(buf);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }

