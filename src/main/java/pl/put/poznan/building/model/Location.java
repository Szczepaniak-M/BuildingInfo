package pl.put.poznan.building.model;

import java.util.List;
import java.util.Map;

public abstract class Location {

    private List<Location> sublocation;
    private final int id;
    private final String name;

    protected Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Location> getSublocation() {
        return sublocation;
    }

    public void setSublocation(List<Location> sublocation) {
        this.sublocation = sublocation;
    }

    public abstract double getArea();

    public abstract double getArea(Map<Integer, Double> map);

    public abstract double getCube();

    public abstract double getHeating();

    public abstract double getLight();


}
