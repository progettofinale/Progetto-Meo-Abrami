package com.ric.fab;

/**
 * eccezione personalizzata
 */
public class FolderNotFoundException extends Exception {
    public final String path;
    /**eccezione personalizzata
     * @param path String:variabile del costruttore dell'eccezione
     */
    public FolderNotFoundException(String path){
        super (String.format("il testo inserito " +path + "non è un path valido" ));
        this.path=path;

    }
}

