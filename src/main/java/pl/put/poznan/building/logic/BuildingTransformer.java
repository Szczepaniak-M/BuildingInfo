package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Room;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
@Service
public class BuildingTransformer {

    private final String[] transforms;

    public BuildingTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
    
    public Double calculateAreaOfFloor(Construction construction) {    	
    	return construction.getLocations().parallelStream().map(l -> (Room) l).mapToDouble(Room::getArea).sum();
    }
    
    public Double calculateAreaOfBuilding(Construction construction) {
    	List<Room> rooms = new ArrayList<>();    	
    	
    	construction.getLocations().forEach(l -> {
    		((Construction) l).getLocations().parallelStream().map(c -> (Room) c).forEach(r -> rooms.add(r));  	
    	});
    	
    	return rooms.parallelStream().mapToDouble(Room::getArea).sum();
    }
}
