package com.ric.fab;

import com.ric.fab.fileReader.ClasseFile;
import com.ric.fab.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.util.Scanner;

/**@author macbookpro
 * @since 2020/09/15
 * @version 3
 * progetto application
 * il programma progettofinale implementa un' applicaione che controlla il contenuto di un dropbox e esegue operazioni di filtri e stats su di esso
 */
@SpringBootApplication
public class ProgettoApplication{

	/**metodo principale del programma dal quale viene eseguito springboot
	 * @param args contiene gli argomenti forniti insieme all' esecuzione del jar
	 *
	 */
	public static void main(String[] args) throws MalformedURLException{
		System.out.println("Inserisci il path della Cartella da esaminare: ");
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		try {
			if(path.charAt(0)!='/')
				throw new FolderNotFoundException(path);
		else{Utils.setPath(path);

			new ClasseFile();
			SpringApplication.run(ProgettoApplication.class, args);}
		} catch(FolderNotFoundException e){
			System.out.println("il percorso inserito non è valido: " + e.path);
		}
		catch(MalformedURLException e ){
			e.printStackTrace();
		}

	}

}
