package com.ric.fab.myController;
import com.ric.fab.Statistiche.Max_min_size;
import com.ric.fab.data.DataBase;
import com.ric.fab.Statistiche.Statsmedie;
import com.ric.fab.utils.Utils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * crea le rotte
 * controller dell' applicazione, gestisce tutte le rotte del rest api
 */
@RestController
public class FinalController {

    @PostMapping(value = "/data", produces = "application/jason")
    String getData(@RequestBody(required = false) String filter) {
        return Utils.gson.toJson(DataBase.getDataBaseList());
    }

    @PostMapping(value = "/data/{type}", produces = "application/jason")
    public String statistiche(@PathVariable("type") String type) {
        DataBase.getsubdatalist().clear();
        if (type.contains("filter=")) {
            String filter = type.substring(type.indexOf("filter={\"" ) + "filter={\"".length(), type.indexOf("}"));
            type = type.substring(type.indexOf(":") + 2, type.indexOf("}")-1);
            if(!Utils.matchCharInStr(filter,'$',0))
                return Utils.noCharFound(filter,'$',0);
            switch (filter.substring(0, filter.indexOf("\"", 2))) {
                case "$eq":
                    for (DataBase data : DataBase.getDataBaseList()) {
                        if (type.equals(data.getTipo())) {
                            DataBase.getsubdatalist().add(data);
                        }

                    }
                    return Utils.gson.toJson(DataBase.getsubdatalist());
                case "$not":
                    for (DataBase data : DataBase.getDataBaseList()) {
                        if (!type.equals(data.getTipo())) {
                            DataBase.getsubdatalist().add(data);
                        }
                    }
                    return Utils.gson.toJson(DataBase.getsubdatalist());
                default:
                    System.out.println("Unexpected value: " + filter.substring(0, filter.indexOf("\"", 2)));
                    return "sequenza di caratteri non riconosciuta";
            }
        } else {
            for (DataBase data : DataBase.getDataBaseList()) {
                DataBase.getsubdatalist().add(data);
            }
            return Utils.gson.toJson(DataBase.getsubdatalist());
        }
    }
    @PostMapping(value= "/infofile/{nome_file}",produces="application/jason")
    public String getfilestats(@PathVariable ("nome_file") String nome){
        for(DataBase data : DataBase.getDataBaseList() ){
            if (data.getNome().equalsIgnoreCase(nome)){
                return (data.toString());
            }
        }
        return Utils.fileNotFoundOutput(nome);
    }
    @PostMapping(value= "/statsfile/media/dimensioni",produces="application/jason")
    public String stats_files(){
        long[] vett1= new long[DataBase.getDataBaseList().size()];
        long[] vett2= new long[DataBase.getDataBaseList().size()];
        long[] vett3= new long[DataBase.getDataBaseList().size()];
        long[] vett4= new long[DataBase.getDataBaseList().size()];
        int contsize=0;
        int contlunghezza=0;
        int contaltezza=0;
        int contdurata=0;

        for (DataBase data:DataBase.getDataBaseList()) {
            if(data.getSize()!=0){
                vett1[contsize]= data.getSize();
                contsize++;
                if(data.getLunghezza()!=0){
                    vett2[contlunghezza]=data.getLunghezza();
                    contlunghezza++;
                    if(data.getAltezza()!=0){
                        vett3[contaltezza]=data.getAltezza();
                        contaltezza++;
                        if(data.getDurata()!=0){
                            vett4[contdurata]= data.getDurata();
                            contdurata++;
                        }
                    }
                }

            }
        }
        long mediaaltezza=0;
        long mediadurata=0;
        long mediasize=0;
        long medialunghezza=0;

        for (long el : vett1) {
            mediasize=(mediasize+el);
        }
        for (long el : vett2) {
            medialunghezza=(medialunghezza+el);
        }
        for (long el : vett3) {
            mediaaltezza=(mediaaltezza+el);
        }
        for (long el : vett4) {
            mediadurata=(mediadurata+el);
        }
        mediasize=mediasize/ vett1.length;
        medialunghezza=medialunghezza/ vett2.length;
        mediaaltezza=mediaaltezza/ vett3.length;
        mediadurata=mediadurata/vett4.length;
        Statsmedie.getLista().add( new Statsmedie(mediasize,medialunghezza,mediaaltezza,mediadurata));
        return(Statsmedie.getLista().toString());
    }
    @PostMapping (value="/trovamaxemin", produces="application/jason")
    public String maxemin(){
        new Max_min_size(DataBase.getDataBaseList());
        return("la dimensione massima trovata è: "+ Max_min_size.getTempmax() + "\n" + "la dimensione minima trovata è: " + Max_min_size.getTempmin() + "\n");
    }
}
/*
    @PostMapping(value = "/data/{type}/sizes/{sizes}", produces = "application/jason")
    public String getfilesize(@PathVariable("type") String type, @PathVariable("sizes") String sizes) {
        DataBase.getsubdatalist().clear();
        int min = 0;
        int max = 0;
        System.out.println(type);
        if (type.contains("filter=")) {
            String filterT = type.substring(type.indexOf("filter={\"") + "filter={\"".length(), type.indexOf("}"));
            type = type.substring(type.indexOf(":") + 2, type.indexOf("}")-1);
            System.out.println(filterT);
            if(!Utils.matchCharInStr(filterT,'$',0))
                return Utils.noCharFound(filterT,'$',0);
            if (sizes.contains("filter=")) {
                String filterS = sizes.substring(sizes.indexOf("filter={\"") + "filter={\"".length(), sizes.indexOf("}"));
                sizes = sizes.substring(sizes.indexOf(":") +1, sizes.indexOf("}"));
                 if(!Utils.matchCharInStr(filterS,'$',0))
                return Utils.noCharFound(filterS,'$',0);
               System.out.println(filterS.substring(0, filterS.indexOf("\"", 2)));
                if (filterT != null) {
                    if (filterS != null) {
                        System.out.println(filterS.substring(0, filterS.indexOf("\"", 2)));
                        switch (filterT.substring(0, filterT.indexOf("\"", 2))) {
                            case "$eq":

                                switch (filterS.substring(0, filterS.indexOf("\"", 2))) {
                                    case "$gt":
                                        System.out.println(sizes);
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() > Integer.parseInt(sizes)) {
                                                DataBase.getsubdatalist().add(data);
                                            }
                                        }

                                        return Utils.gson.toJson(DataBase.getsubdatalist()).toString();
                                    case "$gte":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() >= Integer.parseInt(sizes)) {
                                                DataBase.getsubdatalist().add(data);
                                            }
                                        }return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$lt":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() < Integer.parseInt(sizes)) {
                                               DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$lte":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() <= Integer.parseInt(sizes)) {
                                              DataBase.getsubdatalist().add(data);
                                            }
                                        }return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$bt":
                                        min = Integer.parseInt(sizes.substring(sizes.indexOf("[" , sizes.indexOf(",") )));
                                        max = Integer.parseInt(sizes.substring(sizes.indexOf("," , sizes.indexOf("]") )));
                                        System.out.println(min + "-" + max);
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() < max && data.getSize() > min) {
                                                DataBase.getsubdatalist().add(data);
                                            }

                                        }
                                        return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$bte":
                                        min = Integer.parseInt(sizes.substring(sizes.indexOf("[" + 1, sizes.indexOf(",") - 1)));
                                        max = Integer.parseInt(sizes.substring(sizes.indexOf("," + 1, sizes.indexOf("]") - 1)));
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (type.equals(data.getTipo()) && data.getSize() <= max && data.getSize() >= min) {
                                              DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    default:
                                        return "Filtro Sizes non ha una sequenza di caratteri riconosciuta";
                                }

                            case "$not":
                                switch (filterS.substring(0, filterS.indexOf("\"", 2))) {
                                    case "$gt":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() > Integer.parseInt(sizes)) {
                                               DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$gte":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() >= Integer.parseInt(sizes)) {
                                                DataBase.getsubdatalist().add(data);
                                            }
                                        }return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$lt":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() < Integer.parseInt(sizes)) {
                                               DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$lte":
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() <= Integer.parseInt(sizes)) {
                                                DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$bt":
                                        min = (int) Long.parseLong(sizes.substring(sizes.indexOf("[" + 1, sizes.indexOf(",") - 1)));
                                        max = (int) Long.parseLong(sizes.substring(sizes.indexOf("," + 1, sizes.indexOf("]") - 1)));
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() < max && data.getSize() > min) {
                                                DataBase.getsubdatalist().add(data);
                                            }
                                        }    return Utils.gson.toJson(DataBase.getsubdatalist());
                                    case "$bte":
                                        min = Integer.parseInt(sizes.substring(sizes.indexOf("[" + 1, sizes.indexOf(",") - 1)));
                                        max = Integer.parseInt(sizes.substring(sizes.indexOf("," + 1, sizes.indexOf("]") - 1)));
                                        for (DataBase data : DataBase.getDataBaseList()) {
                                            if (!type.equals(data.getTipo()) && data.getSize() <= max && data.getSize() >= min) {
                                               DataBase.getsubdatalist().add(data);
                                            }
                                        } return Utils.gson.toJson(DataBase.getsubdatalist());
                                    default:
                                        return "Filtro Sizes non ha una sequenza di caratteri riconosciuta";
                                }
                            default:
                                System.out.println("Unexpected value: " + filterT.substring(0, filterT.indexOf("\"", 2)));
                                return "Filtro Tipo non valido";
                        }
                    }
                } else {
                    System.out.println("Filter non rispetta i requisiti");
                    return "Filtro vuoto";
                }
            }
        }else {
            for (DataBase data : DataBase.getDataBaseList()) {
              DataBase.getsubdatalist().add(data);
            }
            return Utils.gson.toJson(DataBase.getsubdatalist());
        }
        return "ERROR";
    }*/