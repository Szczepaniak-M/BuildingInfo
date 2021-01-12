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
     * @param id - type int
     * @param name - type String
     * @param sublocations - List<Location>
     */
    public Construction(int id, String name, List<Location> sublocation) {
        super(id, name);
        setSublocation(sublocation);
    }

    /**
     * Empty constructor
     */
    public Construction(){
        setSublocation(new LinkedList<>());
    }

    /**
     * This function returns total area of sublocations
     * 
     * @return area - double
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
     * @param mao - Map<Intger, Double.
     * @return area - double
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
     * This function returns total cube of sublocations
     * 
     * @return cube - double
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
     * This function returns total cube of sublocations and puts it in map with id as key
     * 
     * @param mao - Map<Intger, Double.
     * @return cube - double
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
     * @return heating - double
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
     * This function returns 0 as value of light
     * 
     * @return 0.0 - double
     */
    @Override
    public double getLight() {
        return 0;
    }

    /**
     * This function checks if exceed doesn't exceed heat limit
     * 
     * @return boolean
     */
    @Override
    public boolean isOverHeatLimit(double limit) {
        return this.getHeating() / this.getCube() > limit;
    }

    /**
     * This function checks if Construction doesn't exceed heat limit and puts it in the map with id as key
     * 
     * @param map - Map<Intger, Boolean>
     * @param limit - double
     * @return boolean
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
