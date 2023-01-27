package com.ash.StreamerManager;

import com.ash.listOfStreamer.listOfStreamer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamerManagerApplication.class, args);
		System.out.println("La aplicacion");
		listOfStreamer los = new listOfStreamer();
		try {
			los.starts();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
