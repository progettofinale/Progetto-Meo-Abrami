package com.ric.fab.utils;

import com.google.gson.*;
import com.ric.fab.data.FileData;
import com.ric.fab.data.FolderData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Utils {
    static String path;
    public static String noCharFound(String str,char c, int i){
        return "Stringa Malformata, manca il simbolo "+c+" nella posizione "+i+" di "+str;
    }
    public static boolean matchCharInStr(String str,char c,int i){
        return str.charAt(i) == c;
    }
    public static String fileNotFoundOutput(String fileName){
        return("Non è stato possibile trovare un file on il nome richiesto "+fileName+" all' " +
                "interno della cartella "+Utils.getPath()+", ne nelle sue sottocartelle");
    }
    /**restituisce il numero di cartelle trovate
     * @return restituisce il valore della variabile
     */
    public static int getFolderFinded() {
        return folderFinded;
    }

    /**incrementa di 1 il contatore di elementi trovati
     *
     */
    public static void addFinded() {
        Utils.folderFinded = Utils.folderFinded+1;
    }

    static  int folderFinded=0;

    /**imposta il valore di path
     * @param path imposta il valore della variabile
     */
    public static void setPath(String path) {
        Utils.path = path;
    }

    /**restituisce il valore di path
     * @return restituisce il valore della variabile
     */
    public static String getPath() {
        return path;
    }




    /**metodo che esamina il contenuto del javadoc e dopo filtri ne salva il risultato in un database
     * @param buf: bufferreader
     */
    public static void commonReader(BufferedReader buf) {
        JsonElement je1 = new JsonParser().parse(buf);
        JsonObject all=je1.getAsJsonObject();

        if(all.size()!=1) {
            JsonElement element = all.get("entries");
            JsonArray entries = element.getAsJsonArray();
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

    /**ottiene un json tramite la chiamata all' api di dropbox contenente le informazioni richieste
     * @param http rotta da chiamare
     * @param jsonBody body associato alla chiamata dell' api
     * @throws MalformedURLException ecceione che conferma la valodità dell' url
     */
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
        } catch(IOException e){
            e.printStackTrace();

        }
    }

    public static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

}