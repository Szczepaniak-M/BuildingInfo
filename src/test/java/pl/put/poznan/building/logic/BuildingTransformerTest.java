package pl.put.poznan.building.logic;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.building.model.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTransformerTest {
    BuildingTransformer buildingTransformer;
    Location location;

    @BeforeEach
    public void setUp() {
        buildingTransformer = new BuildingTransformer();
        String jsonString = "{  'id': 1,\n" +
                "  'name': 'testBuilding',\n" +
                "  'locations':[{\n" +
                "    'id': 2,\n" +
                "    'name': 'testLvL',\n" +
                "    'locations': [{\n" +
                "      'id': 3,\n" +
                "      'name': 'testRoom',\n" +
                "      'area': 123.0,\n" +
                "      'cube': 400.0,\n" +
                "      'heating': 156.0,\n" +
                "      'light': 210.0\n" +
                "    },\n" +
                "      {\n" +
                "        'id': 4,\n" +
                "        'name': 'testRoom',\n" +
                "        'area': 65.0,\n" +
                "        'cube': 190.0,\n" +
                "        'heating': 90.0,\n" +
                "        'light': 100.0\n" +
                "      }]\n" +
                "  }]\n" +
                "}";
        JsonObject object = JsonParser.parseString(jsonString).getAsJsonObject();
        location = buildingTransformer.createLocation(object);
    }
    @Test
    public void testCreateLocationRoot() {
        assertEquals(location.getId(), 1);
        assertEquals(location.getName(), "testBuilding");
    }

    @Test
    public void testCreateLocationChild() {
        Location child = location.getSublocation().get(0);
        assertEquals(child.getId(), 2);
        assertEquals(child.getName(), "testLvL");
    }

    @Test
    public void testCreateLocationFirstRoom() {
        Location room = location.getSublocation().get(0).getSublocation().get(0);
        assertEquals(room.getId(), 3);
        assertEquals(room.getName(), "testRoom");
        assertEquals(room.getArea(), 123);
        assertEquals(room.getCube(), 400);
        assertEquals(room.getHeating(), 156);
        assertEquals(room.getLight(), 210);
    }

    @Test
    public void testCreateLocationSecondRoom() {
        Location room = location.getSublocation().get(0).getSublocation().get(1);
        assertEquals(room.getId(), 4);
        assertEquals(room.getName(), "testRoom");
        assertEquals(room.getArea(), 65);
        assertEquals(room.getCube(), 190);
        assertEquals(room.getHeating(), 90);
        assertEquals(room.getLight(), 100);
    }
}