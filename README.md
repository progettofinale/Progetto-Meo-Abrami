# Progetto-Meo-Abrami
Progettofinle è un'applicazione Java per controllare il contenuto di un Dropbox e eseguire statistiche sugli elementi
## formato dei dati restituiti
In seguito a una richiesta al servizio i risultati vengono rappresanti mediante un json come un array di oggetti che rappresentano i dati appartenenti al dataset.<br>
Nello specifico il formato di ogni singolo elemento è il seguente:</p>
<pre><code>{
}</code><pre>

## rotte
<blockquote>
  <p><strong>POST</strong>/data
    /data restituisce tutti gli elementi del database 
     <p><strong>POST</strong>/data/{filtro per il tipo}
    /data restituisce tutti gli elementi del database del tipo richiesto
       <p><strong>POST</strong>/data/{filtro per il tipo}/sizes/{filtro per la dimensione}
    /data restituisce tutti gli elementi del database del tipo richiesto con le dimensioni indicate </blockquote>
        
