package com.placementcell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.placementcell")

public class PlacementCellWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacementCellWebsiteApplication.class, args);
	}
}
