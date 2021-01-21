package pl.put.poznan.building.rest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.put.poznan.building.logic.BuildingTransformer;
import pl.put.poznan.building.model.Construction;
import pl.put.poznan.building.model.Location;
import pl.put.poznan.building.model.Room;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BuildingInfoRestControllerTest {
    BuildingTransformer buildingTransformerMock = Mockito.mock(BuildingTransformer.class);
    BuildingInfoRestController buildingInfoRestController;

    @BeforeEach
    public void setUp() {
        List<Location> child = new LinkedList<>();
        child.add(new Room(3, "testRoom", 123, 400, 156, 210));
        child.add(new Room(4, "testRoom", 65, 190, 90, 100));
        Location location2 = new Construction(2, "testLvL", child);
        List<Location> sublocation = new LinkedList<>();
        sublocation.add(location2);
        Location location = new Construction(1, "testBuilding", sublocation);
        when(buildingTransformerMock.createLocation(any(JsonObject.class))).thenReturn(location);
        buildingInfoRestController = new BuildingInfoRestController(buildingTransformerMock);
    }

    @Test
    public void testCalculateRootArea() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<String, Double>> responseEntity = buildingInfoRestController.calculateRootArea(new JsonObject());
        Map<String, Double> map = Map.of("sum", 188.0);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateLocationArea() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<Integer, Double>> responseEntity = buildingInfoRestController.calculateLocationsArea(new JsonObject());
        Map<Integer, Double> map = new HashMap<>();
        map.put(1,188.0);
        map.put(2, 188.0);
        map.put(3, 123.0);
        map.put(4, 65.0);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateHeatOverLimit() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String jsonString = "{'limit': 0.4, 'locations':{'id':1, 'name':'abc'}}";
        JsonObject object = JsonParser.parseString(jsonString).getAsJsonObject();
        ResponseEntity<Map<Integer, Boolean>> responseEntity = buildingInfoRestController.calculateHeatOverLimit(object);
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, true);
        map.put(2, true);
        map.put(3, false);
        map.put(4, true);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateRootCubage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<String, Double>> responseEntity = buildingInfoRestController.calculateRootCubage(new JsonObject());
        Map<String, Double> map = Map.of("sum", 590.0);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateLocationsCubage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<Integer, Double>> responseEntity = buildingInfoRestController.calculateLocationsCubage(new JsonObject());
        Map<Integer, Double> map = new HashMap<>();
        map.put(1, 590.0);
        map.put(2, 590.0);
        map.put(3, 400.0);
        map.put(4, 190.0);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateRootLightPerSquareMeter() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<String, Double>> responseEntity = buildingInfoRestController.calculateRootLightPerSquareMeter(new JsonObject());
        Map<String, Double> map = Map.of("sum", (210.0 + 100.0)/(123 + 65));
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateLocationsLightPerSquareMeter() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<Integer, Double>> responseEntity = buildingInfoRestController.calculateLocationsLightPerSquareMeter(new JsonObject());
        Map<Integer, Double> map = new HashMap<>();
        map.put(1, (210.0 + 100.0)/(123 + 65));
        map.put(2, (210.0 + 100.0)/(123 + 65));
        map.put(3, 210.0/123);
        map.put(4, 100.0/65);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateRootEnergyUsagePerCubedMeter() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<String, Double>> responseEntity = buildingInfoRestController.calculateRootEnergyUsagePerCubedMeter(new JsonObject());
        Map<String, Double> map = Map.of("sum", (156.0 + 90.0)/(400 + 190));
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateLocationsEnergyUsagePerCubedMeter() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Map<Integer, Double>> responseEntity = buildingInfoRestController.calculateLocationsEnergyUsagePerCubedMeter(new JsonObject());
        Map<Integer, Double> map = new HashMap<>();
        map.put(1, (156.0 + 90.0)/(400 + 190));
        map.put(2, (156.0 + 90.0)/(400 + 190));
        map.put(3, 156.0/400);
        map.put(4, 90.0/190);
        assertEquals(responseEntity.getBody(), map);
    }

    @Test
    public void testCalculateLocationsEnergyUsagePerCubedMeterForSingleRoom() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Location testRoom = new Room(1, "testRoom", 81, 729, 300, 215);
        when(buildingTransformerMock.createLocation(any(JsonObject.class))).thenReturn(testRoom);
        buildingInfoRestController = new BuildingInfoRestController(buildingTransformerMock);
        ResponseEntity<Map<Integer, Double>> responseEntity = buildingInfoRestController.calculateLocationsEnergyUsagePerCubedMeter(new JsonObject());
        Map<Integer, Double> map = new HashMap<>();
        map.put(1, 300.0/729);
        assertEquals(responseEntity.getBody(), map);
    }
}