# Progetto-Meo-Abrami
Progettofinle è un'applicazione Java per controllare il contenuto di un Dropbox e eseguire statistiche sugli elementi
## Formato dei dati restituiti
In seguito a una richiesta al servizio i risultati vengono rappresanti mediante un json come un array di oggetti che rappresentano i dati appartenenti al dataset.<br>
Nello specifico il formato di ogni singolo elemento è il seguente:</p>
<pre><code>{
        "nome":"img001.jpeg",
	"tipo":"photo",
	"size":86863,
	"modifica":"2019-10-12T09:57:15Z",
	"altezza":701,
	"lunghezza":1121,
	"durata":0
}</code></pre>

Un esempio di richiesta con filtro è il seguente:post /data/filter={"$eq":"photo"}</p>
<pre><code>{
        "nome":"img001.jpeg",
	"tipo":"photo",
	"size":86863,
	"modifica":"2019-10-12T09:57:15Z",
	"altezza":701,
	"lunghezza":1121,
	"durata":0
},
ecc..
</code></pre>
Altre opzioni di rotte con filtri sono:   post /data/filter={"$not":"photo"} , 
                                          post /data/foto/sizes/filter={"$gt":3}


<p> in particolare: </p>
<ul>
    <li><strong>nome</strong> rappresenta il nome del file</li>
     <li><strong>tipo</strong> rappresenta il tipo del file</li>
     <li><strong>size</strong rappresenta il peso del file</li>
     <li><strong>modifica</strong> rappresenta l'ultima modifica del file</li>
     <li><strong>altezza</strong> rappresenta l'altezza del'immagine</li>
     <li><strong>lunghezza</strong> rappresenta la lunghezza dell'immagine</li>
     <li><strong>durata</strong> rappresenta la durata del video</li>
     </ul>       
        
## Rotte
<blockquote><p><strong>POST</strong>/data</blockquote>
    /data restituisce tutti gli elementi del database 
    
   <blockquote> <p><strong>POST</strong>/data/{filtro per il tipo}</blockquote>
    /data restituisce tutti gli elementi del database del tipo richiesto
    
  <blockquote><p><strong>POST</strong>/data/photo/sizes/{filtrosize}</blockquote>
         /dataphotosize restituisce tutti gli elementi del database del tipo richiesto con le dimensioni indicate </blockquote>
	 
   <blockquote><p><strong>POST</strong>/infofile/{/nomefile}</blockquote>
    /infofile restituisce le informazioni del file
    
   <blockquote><p><strong>POST</strong>/statsfile/media/dimensioni</blockquote>
    /statsfile restituisce peso, altezza, lunghezza e durata degli elementi
    
   <blockquote><p><strong>POST</strong>/trovamaxemin</blockquote>
    /trovamaxemin restituisce il massimo e il minimo
    
   <blockquote><p><strong>POST</strong>/data/video</blockquote>
    /datavideo restituisce tutti i video del database 
    
   <blockquote><p><strong>POST</strong>/data/fileditesto</blockquote>
    /datafileditesto restituisce tutti i video del database
    
  </div> </div>
  
  ## Utilizzo <br>
  
      ...
      Avviare il file jar tramite cmd, scegliere il path per selezionare quali elementi di Dropbox analizzare.
     Si avvierà il caricamento di springboot e si potranno effettuare delle chiamate alle rotte presenti nella descrizione sovrastante.
      ...
  
  
  
  
   <b> UML </b>
   
### Class diagram
![UML](https://github.com/progettofinale/Progetto-Meo-Abrami/blob/master/class%20diagram%20JPG2.jpg)
### Sequence diagram
![UML](https://github.com/progettofinale/Progetto-Meo-Abrami/blob/master/sequence%20prgram%20%20JPG2.jpg)
### Us case diagram
![UML](https://github.com/progettofinale/Progetto-Meo-Abrami/blob/master/us%20case%20diagram2.jpg)
    
