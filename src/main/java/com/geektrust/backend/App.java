package com.geektrust.backend;

import java.io.IOException;
import com.geektrust.backend.services.MetroStationService;

public class App {

	
	public static void main(String[] args) throws IOException {
	//	System.out.println("Welcome to Geektrust Backend Challenge!");
		MetroStationService metroStationService = new MetroStationService();
        metroStationService.start(args);
	}

}
