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
   blockquote> <p><strong>POST</strong>/data/{filtro per il tipo}</blockquote>
    /data restituisce tutti gli elementi del database del tipo richiesto
      <blockquote><p><strong>POST</strong>/data/{filtro per il tipo}/sizes/{filtro per la dimensione}</blockquote>
         /data restituisce tutti gli elementi del database del tipo richiesto con le dimensioni indicate </blockquote>
   <blockquote><p><strong>POST</strong>/infofile/{/nomefile}</blockquote>
    /infofile restituisce le informazioni del file
  
  </div> </div>
  
  ## Utilizzo <br>
     ...
     Avviare il file jar tramite cmd, scegliere il path per selezionare quali elementi di Dropbox analizzare.
     Si avvierà il caricamento di springboot e si potranno effettuare delle chiamate alle rotte presenti nella descrizione sovrastante.
     ...
     
  
##UML

### Class diagram
![UML]()
### Use case diagram
![UML]()
### Sequence diagram
![UML]()
    
