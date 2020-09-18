package com.ric.fab.Statistiche;

import java.util.ArrayList;
import java.util.List;

public class Statsmedie {
    long media1;
    long media2;
    long media3;
    long media4;

    public Statsmedie(long media1, long media2, long media3, long media4) {
        this.media1 = media1;
        this.media2 = media2;
        this.media3 = media3;
        this.media4 = media4;
    }
    private static List<Statsmedie> lista= new ArrayList(){};

    public static List<Statsmedie> getLista() {
        return lista;
    }

    @Override
    public String toString() {
        return "\nMedie{" +
                "\n\t\"dimensione_media\":" + media1 +
                ",\n\t\"lunghezza_media\":" + media2 +
                ",\n\t\"altezza_media\":" + media3 +
                ",\n\t\"durata_media\":" + media4 + "\n}\n";

    }
}
