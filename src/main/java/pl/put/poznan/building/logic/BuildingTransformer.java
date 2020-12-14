package pl.put.poznan.building.logic;

import java.util.List;

import org.springframework.stereotype.Service;

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
    
    public Double calculateAreaOfRooms(List<Room> rooms) {
    	return rooms.stream().mapToDouble(r -> r.getArea()).sum();
    }
}
