package pl.put.poznan.building.rest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import pl.put.poznan.building.logic.BuildingTransformer;
import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Room;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private final BuildingTransformer buildingTransformer;
    private final Gson gson;
    
    public BuildingInfoController(BuildingTransformer buildingTransformer) {
    	this.buildingTransformer = buildingTransformer;
    	gson = new Gson();
    }
    
//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
//    public String get(@PathVariable String text,
//                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug(Arrays.toString(transforms));
//
//        // perform the transformation, you should run your logic here, below is just a silly example
//        BuildingTransformer transformer = new BuildingTransformer(transforms);
//        return transformer.transform(text);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
//    public String post(@PathVariable String text,
//                      @RequestBody String[] transforms) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug(Arrays.toString(transforms));
//
//        // perform the transformation, you should run your logic here, below is just a silly example
//        BuildingTransformer transformer = new BuildingTransformer(transforms);
//        return transformer.transform(text);
//    }

    @PostMapping(value = "/calculate/building", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateBuildingArea(@RequestBody JsonObject data) {
    	Map<String, Double> responseBody = new HashMap<>();
    	List<Room> rooms = new ArrayList<>();
    	JsonArray floors = data.get("constructions").getAsJsonArray();
    	
    	for(JsonElement floor : floors) {
    		JsonArray roomsPerFloor = floor.getAsJsonObject().get("constructions").getAsJsonArray();
    		for(JsonElement r : roomsPerFloor) {
    			rooms.add(gson.fromJson(r, Room.class));
    		}
    	}
    	
    	Construction construction = gson.fromJson(data, Construction.class);
    	
    	responseBody.put("sum", buildingTransformer.calculateAreaOfRooms(rooms));
    	
    	return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping(value = "/calculate/floor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateFloorArea(@RequestBody JsonObject data) {
    	Map<String, Double> responseBody = new HashMap<>();
    	List<Room> rooms = new ArrayList<>();    	

		JsonArray roomsPerFloor = data.get("constructions").getAsJsonArray();
		for(JsonElement r : roomsPerFloor) {
			rooms.add(gson.fromJson(r, Room.class));
		}
    	
    	responseBody.put("sum", buildingTransformer.calculateAreaOfRooms(rooms));
    	
    	return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
    
    @PostMapping(value = "/calculate/room", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> calculateRoomArea(@RequestBody JsonObject data) {
    	Map<String, Double> responseBody = new HashMap<>();
    	List<Room> rooms = List.of(gson.fromJson(data, Room.class));    		
    	
    	responseBody.put("sum", buildingTransformer.calculateAreaOfRooms(rooms));
    	
    	return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}


