package pl.put.poznan.building.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main starter class of application
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.building"})
public class BuildingInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingInfoApplication.class, args);
    }
}
