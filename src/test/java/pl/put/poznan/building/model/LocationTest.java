package pl.put.poznan.building.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    public void testRoomGetArea() {
        Room room = new Room(1, "name", 100.0, 150.0, 80.0, 60.0);
        Map<Integer, Double> map = new HashMap<>();
        room.getArea(map);
        assertEquals(map.get(1), 100);
    }

    @Test
    public void testRoomGetCube() {
        Room room = new Room(1, "name", 100.0, 150.0, 80.0, 60.0);
        Map<Integer, Double> map = new HashMap<>();
        room.getCube(map);
        assertEquals(map.get(1), 150);
    }

    @Test
    public void testRoomIsOverHeatLimit() {
        Room room = new Room(1, "name", 100.0, 150.0, 80.0, 60.0);
        Map<Integer, Boolean> map = new HashMap<>();
        room.isOverHeatLimit(map, 0.5);
        assertEquals(map.get(1), true);
    }

    @Test
    public void testGetArea() {
        List<Location> sublocation = new LinkedList<>();
        sublocation.add(new Room(2, "name2", 100.0, 150.0, 80.0, 60.0));
        sublocation.add(new Room(3, "name3", 200.0, 250.0, 180.0, 160.0));
        Construction construction = new Construction(1, "name", sublocation);
        Map<Integer, Double> map = new HashMap<>();
        construction.getArea(map);
        assertEquals(map.get(1), 300);
    }

    @Test
    public void testGetCube() {
        List<Location> sublocation = new LinkedList<>();
        sublocation.add(new Room(2, "name2", 100.0, 150.0, 80.0, 60.0));
        sublocation.add(new Room(3, "name3", 200.0, 250.0, 180.0, 160.0));
        Construction construction = new Construction(1, "name", sublocation);
        Map<Integer, Double> map = new HashMap<>();
        construction.getCube(map);
        assertEquals(map.get(1), 400);
    }

    @Test
    public void testIsOverHeatLimit() {
        List<Location> sublocation = new LinkedList<>();
        sublocation.add(new Room(2, "name2", 100.0, 150.0, 80.0, 60.0));
        sublocation.add(new Room(3, "name3", 200.0, 250.0, 180.0, 160.0));
        Construction construction = new Construction(1, "name", sublocation);
        Map<Integer, Boolean> map = new HashMap<>();
        construction.isOverHeatLimit(map, 0.6);
        assertEquals(map.get(1), true);
    }
}