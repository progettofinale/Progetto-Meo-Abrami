package com.ric.fab.fileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ric.fab.data.DataBase;
import com.ric.fab.data.FileData;
import com.ric.fab.data.FolderData;
import com.ric.fab.utils.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;*/

/**<h1>ClasseFile</h1>
 * stabilisce le connessioni alle api di dropbox e salva i risultati nei database
 */
public class ClasseFile {

    /**stabilisce le connessioni alle api di dropbox e salva i risultati nei database
     * @throws MalformedURLException: eccezione che gestisce la validità dell' url
     */
    public ClasseFile() throws MalformedURLException {
        String url = "https://api.dropboxapi.com/2/files/list_folder";
        String body = "{\r\n" + "    \"path\": \"" + Utils.getPath() + "\",\r\n" + "    \"recursive\": false,\r\n"
                + "    \"include_media_info\": true,\r\n" + "    \"include_deleted\": false,\r\n"
                + "    \"include_has_explicit_shared_members\": false,\r\n"
                + "    \"include_mounted_folders\": true,\r\n" + "    \"include_non_downloadable_files\": true\r\n"
                + "}";

        Utils.lettoreFile(url, body);

        int i=0;
        FolderData folder;
        String arrow="->";
        while (i<Utils.getFolderFinded()){
            if(i>FolderData.getFolderDataList().size()){System.out.println("break");break;}
            String http = "https://api.dropboxapi.com/2/files/list_folder";
            folder=FolderData.getFolderDataList().get(i);
            System.out.println(arrow+folder.getPath());
            body="{\r\n" + "    \"path\": \""+folder.getPath()+"\",\r\n" + "    \"recursive\": false,\r\n"
                    + "    \"include_media_info\": false,\r\n" + "    \"include_deleted\": false,\r\n"
                    + "    \"include_has_explicit_shared_members\": false,\r\n"
                    + "    \"include_mounted_folders\": true,\r\n" + "    \"include_non_downloadable_files\": true\r\n"
                    + "}";
            Utils.lettoreFile(http,body);
            i++;
        }

        System.out.println("--> "+FileData.getFileDataList().size()+" Elementi Trovati\nEsamino i loro contenuti");
        for (FileData file : FileData.getFileDataList()
        ) {
            String http = "https://api.dropboxapi.com/2/files/get_metadata";
            URL urls = new URL(http);
            body = "{\r\n" +
                    "    \"path\": \"" + file.getPath() + "\",\r\n" +
                    "    \"include_media_info\": true,\r\n" +
                    "    \"include_deleted\": false,\r\n" +
                    "    \"include_has_explicit_shared_members\": false\r\n" +
                    "}";
            try {
                HttpURLConnection openConnection = (HttpURLConnection) urls.openConnection();
                openConnection.setRequestMethod("POST");
                openConnection.setRequestProperty("Authorization",
                        "Bearer khpXnv7sjrwAAAAAAAAAAXdr6kgKYwoEmWHkBZtg8KjNuLey7Zrj_v8Fy36z4sbF");
                openConnection.setRequestProperty("Content-Type", "application/json");
                openConnection.setRequestProperty("Accept", "application/json");
                openConnection.setDoOutput(true);
                openConnection.connect();
                try (OutputStream os = openConnection.getOutputStream()) {
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);

                }
                if (openConnection.getResponseCode() == 400) {
                    return;
                }
                try (InputStream in = openConnection.getInputStream()) {
                    InputStreamReader inR = new InputStreamReader(in);
                    BufferedReader buf = new BufferedReader(inR);

                    JsonElement je1 = new JsonParser().parse(buf);
                    JsonObject all=je1.getAsJsonObject();
                    JsonElement je9 = all.get("name");
                    String name = je9.getAsString();
                    JsonElement je10 = all.get("client_modified");
                    JsonElement je3 = all.get("size");

                    if(name.substring(name.length()-4,name.length()).equals(".png")||name.substring(name.length()-4,name.length()).equals("jpeg")||name.substring(name.length()-4,name.length()).equals(".jpg")) {

                        JsonElement je4 = all.get("media_info");
                        JsonObject media = je4.getAsJsonObject();
                        JsonElement je8 = media.get("metadata");
                        JsonObject metadata = je8.getAsJsonObject();
                        JsonElement je5 = metadata.get(".tag");
                        JsonElement je6 = metadata.get("dimensions");
                        JsonObject dimensions = je6.getAsJsonObject();
                        JsonElement je7 = dimensions.get("height");
                        JsonElement je11 = dimensions.get("width");
                        String type = je5.getAsString();
                        int height = je7.getAsInt();
                        int width = je11.getAsInt();
                        Long size = je3.getAsLong();
                        String lastMod = je10.getAsString();
                        DataBase.getDataBaseList().add(new DataBase(name, lastMod, size, type, height, width,0));
                    }
                    else if(name.substring(name.length()-4,name.length()).equals(".mp4")){
                        JsonElement je4 = all.get("media_info");
                        JsonObject media = je4.getAsJsonObject();
                        JsonElement je8 = media.get("metadata");
                        JsonObject metadata = je8.getAsJsonObject();
                        JsonElement je5 = metadata.get(".tag");
                        JsonElement je6 = metadata.get("dimensions");
                        JsonObject dimensions = je6.getAsJsonObject();
                        JsonElement je7 = dimensions.get("height");
                        JsonElement je11 = dimensions.get("width");
                        JsonElement je21 = metadata.get("duration");
                        long duration= je21.getAsLong();
                        String type = je5.getAsString();
                        int height = je7.getAsInt();
                        int width = je11.getAsInt();
                        Long size = je3.getAsLong();
                        String lastMod = je10.getAsString();
                        DataBase.getDataBaseList().add(new DataBase(name, lastMod, size, type, height, width, duration));

                    }
                    else{
                        Long size = je3.getAsLong();
                        String lastMod = je10.getAsString();

                        DataBase.getDataBaseList().add(new DataBase(name, lastMod, size, "file di testo", 0, 0, 0));

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}