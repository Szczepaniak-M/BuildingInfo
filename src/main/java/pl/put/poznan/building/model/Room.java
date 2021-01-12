package pl.put.poznan.building.model;

import java.util.Map;

/**
 * This is the model of Room
 */
public class Room extends Location {
    private double area;
    private double cube;
    private double heating;
    private double light;

    /**
     * Empty constructor
     */
    public Room() {
    }

    /**
     * Constructor with following parameters:
     * 
     * @param id - type int
     * @param name - type String
     * @param area - type double
     * @param cube - type double
     * @param heating - type double
     * @param light - type double
     */
    public Room(int id, String name, double area, double cube, double heating, double light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    /**
     * This function returns area of Room
     * 
     * @return area - double
     */
    @Override
    public double getArea() {
        return area;
    }

    /**
     * This functions puts area of Room in map with id as a key
     * 
     * @param map - Map<Integer, Double>
     * @return area - double
     */
    @Override
    public double getArea(Map<Integer, Double> map) {
        map.put(getId(), area);
        return area;
    }

    /**
     * This function returns cube of Room
     * 
     * @return cube - double
     */
    @Override
    public double getCube() {
        return cube;
    }

    /**
     * This functions puts cube of Room in map with id as a key
     * 
     * @param map - Map<Integer, Double>
     * @return cube - double
     */
    @Override
    public double getCube(Map<Integer, Double> map) {
        map.put(getId(), cube);
        return cube;
    }

    /**
     * This function returns heating value of Room
     * 
     * @return heating - double
     */
    @Override
    public double getHeating() {
        return heating;
    }

    /**
     * This function returns light value of Room
     * 
     * @return light - double
     */
    @Override
    public double getLight() {
        return light;
    }

    /**
     * This function checks if Room doesn't exceed heat limit
     * 
     * @return boolean
     */
    @Override
    public boolean isOverHeatLimit(double limit) {
        return this.heating / this.cube > limit;
    }

    /**
     * This function checks if Room doesn't exceed heat limit and puts it in the map with id as key
     * 
     * @param map - Map<Intger, Boolean>
     * @param limit - double
     * @return boolean
     */
    @Override
    public boolean isOverHeatLimit(Map<Integer, Boolean> map, double limit) {
        if (this.heating / this.cube > limit) {
            map.put(this.getId(), true);
            return true;
        }
        map.put(this.getId(), false);
        return false;
    }
}
