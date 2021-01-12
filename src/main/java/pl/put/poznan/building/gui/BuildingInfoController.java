package pl.put.poznan.building.gui;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.put.poznan.building.logic.BuildingTransformer;
import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Location;
import pl.put.poznan.building.model.Room;

/**
 * This is the main UI controller of application.
 */
@Controller
public class BuildingInfoController {

    private final BuildingTransformer buildingTransformer;

    /**
     * In constructor must be injected an instance of BuildingTransformer
     * 
     * @param buildingTransformer instance of BuildingTransformer
     */
    public BuildingInfoController(BuildingTransformer buildingTransformer) {
        this.buildingTransformer = buildingTransformer;
    }

    /**
     * This function returns webpage where user choose which activity want to perform
     */
    @GetMapping("/")
    public String requestSizeForm() {
        return "request-size-form";
    }

    /**
     * This function returns webpage with detailed informations
     * 
     * @return webpage
     */
    @PostMapping("/details")
    public String requestSizeSubmit(@ModelAttribute("locationType") LocationType locationType,
                                    @ModelAttribute("requestType") RequestType requestType,
                                    @ModelAttribute("floors") String floors,
                                    @ModelAttribute("rooms") String rooms,
                                    @ModelAttribute("limit") String limit,
                                    Model model) {
        Location location = null;
        switch (locationType) {
            case ROOM:
                location = new Room();
                break;
            case FLOOR:
                location = new Construction();
                for (int i = 0; i < Integer.parseInt(rooms); i++) {
                    location.getSublocation().add(new Room());
                }
                break;
            case BUILDING:
                String[] roomsArray = rooms.replaceAll("[^0-9,]", "").split(",");
                location = new Construction();
                for (int i = 0; i < Integer.parseInt(floors); i++) {
                    Location floor = new Construction();
                    for (int j = 0; j < Integer.parseInt(roomsArray[i]); j++) {
                        floor.getSublocation().add(new Room());
                    }
                    location.getSublocation().add(floor);
                }
                break;
        }
        model.addAttribute("location", location);
        model.addAttribute("locationType", locationType);
        model.addAttribute("requestType", requestType);
        model.addAttribute("limit", limit);
        return "request-details-form";
    }

    /**
     * This function returns webpage with general result
     * 
     * @return webpage
     */
    @PostMapping("/result")
    public String showInfo(@ModelAttribute("location") String locationString,
                         @ModelAttribute("requestType") RequestType requestType,
                         @ModelAttribute("limit") String limit,
                         Model model) {
        JsonObject locationJson = JsonParser.parseString(locationString).getAsJsonObject();
        Location location = buildingTransformer.createLocation(locationJson);

        double limitNumber = 0;
        if(!limit.isEmpty()) {
            limitNumber = Double.parseDouble(limit);
        }
        model.addAttribute("location", location);
        model.addAttribute("limit", limitNumber);
        model.addAttribute("requestType", requestType);
        return "result-view";
    }

}
