package pl.put.poznan.building.model;

import java.util.List;

public class Construction extends Location {


    public Construction(int id, String name, List<Location> sublocation) {
        super(id, name);
        this.sublocation = sublocation;
    }

    public List<Location> getLocations() {
        return sublocation;
    }

    @Override
    public double getArea() {
        double area = 0;
        for (Location location : sublocation) {
            area += location.getArea();
        }
        return area;
    }

    @Override
    public double getCube() {
        return 0;
    }

    @Override
    public double getHeating() {
        return 0;
    }

    @Override
    public double getLight() {
        return 0;
    }
}
