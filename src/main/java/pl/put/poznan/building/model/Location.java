package pl.put.poznan.building.model;

import java.util.List;

public abstract class Location {

    protected List<Location> sublocation;
    private final int id;
    private final String name;

    protected Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Location> getSublocation() {
        return sublocation;
    }

    public void setSublocation(List<Location> sublocation) {
        this.sublocation = sublocation;
    }

    public String getName() {
        return name;
    }

    public abstract double getArea();

    public abstract double getCube();

    public abstract double getHeating();

    public abstract double getLight();


}
