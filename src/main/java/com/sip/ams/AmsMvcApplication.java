package com.sip.ams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.ams.controllers.FournisseurController;

@SpringBootApplication
public class AmsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmsMvcApplication.class, args);
		Path path = Paths.get(FournisseurController.uploadDirectory);
		try{
			Files.createDirectory(path);
		}
		catch(IOException ex){

		}
	}

}
