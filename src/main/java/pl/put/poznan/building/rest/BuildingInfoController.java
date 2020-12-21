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

/**
 * This is the main rest controller
 */
@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private final BuildingTransformer buildingTransformer;

    /**
     * In constructor must be injected an instance of BuildingTransformer
     * @param buildingTransformer instance of BuildingTransformer
     */
    public BuildingInfoController(BuildingTransformer buildingTransformer) {
        this.buildingTransformer = buildingTransformer;
    }

    /**
     * This function returns area of location
     * 
     * @param jsonObject json with input data
     * @return JsonObject json with value of calculated area for given location
     */
    @PostMapping(value = "/area/root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateRootArea(@RequestBody JsonObject jsonObject) {
        logger.info(">> calculateRootArea: {}", jsonObject.toString());
        Location location = buildingTransformer.createLocation(jsonObject);
        Map<String, Double> responseBody = new HashMap<>();
        responseBody.put("sum", location.getArea());
        logger.info("<< calculateRootArea: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * This function returns area for every sublocation
     * 
     * @param jsonObject json with input data
     * @return JsonObject json with pairs of Location ID and Location area
     */
    @PostMapping(value = "/area/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Double>> calculateLocationsArea(@RequestBody JsonObject jsonObject) {
        logger.info(">> calculateLocationsArea: {}", jsonObject.toString());
        Map<Integer, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(jsonObject);
        location.getArea(responseBody);
        logger.info("<< calculateLocationsAreas: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * This function returns whether Location exceeds heat limit
     * 
     * @param jsonObject json with input data
     * @return JsonObject json with pairs: Location ID and boolean value
     */
    @PostMapping(value = "/heat/limit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Boolean>> calculateHeatOverLimit(@RequestBody JsonObject jsonObject) {
        logger.info(">> calculateHeatOverLimit: {}", jsonObject.toString());
        Map<Integer, Boolean> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(jsonObject.getAsJsonObject("locations"));
        location.isOverHeatLimit(responseBody, jsonObject.get("limit").getAsDouble());
        logger.info("<< calculateHeatOverLimit: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * This function returns cubage of Location
     * 
     * @param jsonObject json with input data
     * @return JsonObject json with value of calculated cubage for given Location
     */
    @PostMapping(value = "/cubage/root", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateRootCubage(@RequestBody JsonObject jsonObject) {
        logger.info(">> calculateRootCubage: {}", jsonObject.toString());
        Map<String, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(jsonObject);
        responseBody.put("sum", location.getCube());
        logger.info("<< calculateRootCubage: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * This function returns cubage for every sublocation
     * 
     * @param jsonObject json with input data
     * @return JsonObject json with pairs: Location ID and Location cubage
     */
    @PostMapping(value = "/cubage/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<Integer, Double>> calculateLocationsCubage(@RequestBody JsonObject jsonObject) {
        logger.info(">> calculateLocationsCubage {}", jsonObject.toString());
        Map<Integer, Double> responseBody = new HashMap<>();
        Location location = buildingTransformer.createLocation(jsonObject);
        location.getCube(responseBody);
        logger.info("<< calculateLocationsCubage: {}", responseBody.toString());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
