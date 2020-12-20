package pl.put.poznan.building.rest;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.building.logic.BuildingTransformer;
import pl.put.poznan.building.model.Location;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private final BuildingTransformer buildingTransformer;

    public BuildingInfoController(BuildingTransformer buildingTransformer) {
        this.buildingTransformer = buildingTransformer;
    }


    @PostMapping(value = "/area/root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateRootArea(@RequestBody JsonObject data) {
        logger.info(">> calculateRootArea: {}", data.toString());
        Location location = buildingTransformer.createLocation(data);
        Map<String, Double> responseBody = new HashMap<>();
        responseBody.put("sum", location.getArea());
        logger.info("<< calculateRootArea: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping(value = "/area/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Double>> calculateLocationsArea(@RequestBody JsonObject data) {
        logger.info(">> calculateLocationsArea: {}", data.toString());
        Map<Integer, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(data);
        location.getArea(responseBody);
        logger.info("<< calculateLocationsAreas: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping(value = "/heat/limit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Boolean>> calculateHeatOverLimit(@RequestBody JsonObject data) {
        logger.info(">> calculateHeatOverLimit: {}", data.toString());
        Map<Integer, Boolean> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(data.getAsJsonObject("locations"));
        location.isOverHeatLimit(responseBody, data.get("limit").getAsDouble());
        logger.info("<< calculateHeatOverLimit: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping(value = "/cubage/root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateRootCubage(@RequestBody JsonObject data) {
        logger.info(">> calculateRootCubage: {}", data.toString());
        Map<String, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(data);
        responseBody.put("sum", location.getCube());
        logger.info("<< calculateRootCubage: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping(value = "/cubage/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Double>> calculateLocationsCubage(@RequestBody JsonObject data) {
        logger.info(">> calculateLocationsCubage {}", data.toString());
        Map<Integer, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(data);
        location.getCube(responseBody);
        logger.info("<< calculateLocationsCubage: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}


