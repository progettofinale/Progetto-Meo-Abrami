package com.ric.fab;

import com.ric.fab.fileReader.ClasseFile;
import com.ric.fab.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.util.Scanner;

@SpringBootApplication
public class ProgettoApplication {

	public static void main(String[] args) throws MalformedURLException {
		System.out.println("Inserisci il path della Cartella da esaminare: ");
		Scanner input=new Scanner(System.in);
		String path=input.nextLine();
		Utils.setPath(path);
		new ClasseFile();
		SpringApplication.run(ProgettoApplication.class, args);

	}

}
