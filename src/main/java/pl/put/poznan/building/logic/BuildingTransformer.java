package pl.put.poznan.building.logic;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Location;
import pl.put.poznan.building.model.Room;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
@Service
public class BuildingTransformer {

    public Location createLocation(JsonObject object){
        if(object.has("locations")){
            JsonArray locationArray = object.get("locations").getAsJsonArray();
            List<Location> locationList = new LinkedList<>();
            for(JsonElement location : locationArray) {
                locationList.add(createLocation(location.getAsJsonObject()));
            }
            return new Construction(
                    object.get("id").getAsInt(),
                    object.get("name").getAsString(),
                    locationList);
        } else {
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
