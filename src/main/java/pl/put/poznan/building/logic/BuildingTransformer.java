package pl.put.poznan.building.logic;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Location;
import pl.put.poznan.building.model.Room;
import pl.put.poznan.building.rest.BuildingInfoController;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
@Service
public class BuildingTransformer {
    private static final Logger logger = LoggerFactory.getLogger(BuildingTransformer.class);
    public Location createLocation(JsonObject object){
        if(object.has("locations")){
            JsonArray locationArray = object.get("locations").getAsJsonArray();
            List<Location> locationList = new LinkedList<>();
            for(JsonElement location : locationArray) {
                locationList.add(createLocation(location.getAsJsonObject()));
            }
            logger.debug("Creating Location with id: {}", object.get("id").getAsInt());
            return new Construction(
                    object.get("id").getAsInt(),
                    object.get("name").getAsString(),
                    locationList);
        } else {
            logger.debug("Creating Location with id: {}", object.get("id").getAsInt());
            return new Room(
                    object.get("id").getAsInt(),
                    object.get("name").getAsString(),
                    object.get("area").getAsDouble(),
                    object.get("cube").getAsDouble(),
                    object.get("heating").getAsDouble(),
                    object.get("light").getAsDouble()
                    );
        }
    }

}
