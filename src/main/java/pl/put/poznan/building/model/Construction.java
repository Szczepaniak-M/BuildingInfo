package pl.put.poznan.building.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is the model of Construction
 */
public class Construction extends Location {

    /**
     * Constructor with following parameters:
     *
     * @param id          id of Construction
     * @param name        name of Construction
     * @param sublocation list of sublocations
     */
    public Construction(int id, String name, List<Location> sublocation) {
        super(id, name);
        setSublocation(sublocation);
    }

    /**
     * Empty constructor
     */
    public Construction() {
        setSublocation(new LinkedList<>());
    }

    /**
     * This function returns total area of sublocations
     *
     * @return area of Construction
     */
    @Override
    public double getArea() {
        double area = 0;
        for (Location location : getSublocation()) {
            area += location.getArea();
        }
        return area;
    }

    /**
     * This function returns total area of sublocations and puts it in map with id as key
     *
     * @param map visitor
     * @return area of Construction
     */
    public double getArea(Map<Integer, Double> map) {
        double area = 0;
        for (Location location : getSublocation()) {
            area += location.getArea(map);
        }
        map.put(getId(), area);
        return area;
    }

    /**
     * This function returns total cubage of sublocations
     *
     * @return cubature of Construction
     */
    @Override
    public double getCube() {
        double cube = 0;
        for (Location location : getSublocation()) {
            cube += location.getCube();
        }
        return cube;
    }

    /**
     * This function returns total cubage of sublocations and puts it in map with id as key
     *
     * @param map visitor
     * @return cubature of Construction
     */
    @Override
    public double getCube(Map<Integer, Double> map) {
        double cube = 0;
        for (Location location : getSublocation()) {
            cube += location.getCube(map);
        }
        map.put(getId(), cube);
        return cube;
    }

    /**
     * This function returns total value of heating of sublocations
     *
     * @return heating of Construction
     */
    @Override
    public double getHeating() {
        double heating = 0;
        for (Location location : getSublocation()) {
            heating += location.getHeating();
        }
        return heating;
    }

    /**
     * This function returns value of light of Construction
     *
     * @return light of Construction
     */
    @Override
    public double getLight() {
        return 0.0;
    }

    /**
     * This function returns value of light per square meter
     *
     * @return light per square meter of Construction
     */
    @Override
    public double getLightPerSquareMeter() {
        double light = 0;
        double area = 0;
        for (Location location : getSublocation()) {
            light += location.getLight();
        }
        for (Location location : getSublocation()) {
            area += location.getArea();
        }
        return light / area;
    }

    /**
     * This function returns value of light per square meter and puts it in map with id as key
     *
     * @param map visitor
     * @return light per square meter of Construction
     */
    @Override
    public double getLightPerSquareMeter(Map<Integer, Double> map) {
        double light = 0;
        double area = 0;
        for (Location location : getSublocation()) {
            light += location.getLight();
            area += location.getArea();
            location.getLightPerSquareMeter(map);
        }
        map.put(getId(), light / area);
        return light / area;
    }

    /**
     * This function checks if exceed doesn't exceed heat limit
     *
     * @param limit value of limit
     * @return boolean if limit is exceed
     */
    @Override
    public boolean isOverHeatLimit(double limit) {
        return this.getHeating() / this.getCube() > limit;
    }

    /**
     * This function checks if Construction doesn't exceed heat limit and puts it in the map with id as key
     *
     * @param map   visitor
     * @param limit value of limit
     * @return boolean if limit is exceed
     */
    @Override
    public boolean isOverHeatLimit(Map<Integer, Boolean> map, double limit) {
        for (Location location : getSublocation()) {
            location.isOverHeatLimit(map, limit);
        }
        if (this.getHeating() / this.getCube() > limit) {
            map.put(this.getId(), true);
            return true;
        }
        map.put(this.getId(), false);
        return false;
    }
}
