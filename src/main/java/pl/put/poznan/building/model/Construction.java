package pl.put.poznan.building.model;

import java.util.List;
import java.util.Map;

public class Construction extends Location {

    public Construction(int id, String name, List<Location> sublocation) {
        super(id, name);
        setSublocation(sublocation);
    }

    @Override
    public double getArea() {
        double area = 0;
        for (Location location : getSublocation()) {
            area += location.getArea();
        }
        return area;
    }

    public double getArea(Map<Integer, Double> map) {
        double area = 0;
        for (Location location : getSublocation()) {
            area += location.getArea(map);
        }
        map.put(getId(), area);
        return area;
    }

    @Override
    public double getCube() {
        return 0;
    }

    @Override
    public double getHeating() {
        double heating = 0;
        for (Location location : getSublocation()) {
            heating += location.getHeating();
        }
        return heating;
    }

    @Override
    public double getLight() {
        return 0;
    }

    @Override
    public boolean isOverHeatLimit(Map<Integer, Boolean> map, double limit) {
        for (Location location : getSublocation()) {
            location.isOverHeatLimit(map, limit);
        }
        if(this.getHeating() > limit) {
            map.put(this.getId(), true);
            return true;
        }
        map.put(this.getId(), false);
        return false;
    }
}
