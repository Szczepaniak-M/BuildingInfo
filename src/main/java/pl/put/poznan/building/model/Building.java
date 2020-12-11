package pl.put.poznan.building.model;

import java.util.LinkedList;
import java.util.List;

public class Building extends Location {

    private List<Location> floors;

    public Building(int id, String name) {
        super(id, name);
        floors = new LinkedList<>();
    }

    @Override
    double getArea() {
        return 0;
    }

    @Override
    double getCube() {
        return 0;
    }

    @Override
    double getHeating() {
        return 0;
    }

    @Override
    double getLight() {
        return 0;
    }
}
