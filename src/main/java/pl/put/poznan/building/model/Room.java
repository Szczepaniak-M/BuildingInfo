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
     * @param id      id of Room
     * @param name    name of Room
     * @param area    area of Room
     * @param cube    cubature of Room
     * @param heating heating of Room
     * @param light   light of Room
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
     * @return area of Room
     */
    @Override
    public double getArea() {
        return area;
    }

    /**
     * This functions puts area of Room in map with id as a key
     *
     * @param map visitor
     * @return area of Room
     */
    @Override
    public double getArea(Map<Integer, Double> map) {
        map.put(getId(), area);
        return area;
    }

    /**
     * This function returns cubage of Room
     *
     * @return cubature of Room
     */
    @Override
    public double getCube() {
        return cube;
    }

    /**
     * This functions puts cubage of Room in map with id as a key
     *
     * @param map visitor
     * @return cubature of Room
     */
    @Override
    public double getCube(Map<Integer, Double> map) {
        map.put(getId(), cube);
        return cube;
    }

    /**
     * This function returns heating value of Room
     *
     * @return heating value of Room
     */
    @Override
    public double getHeating() {
        return heating;
    }

    /**
     * This function returns light value of Room
     *
     * @return light value of Room
     */
    @Override
    public double getLight() {
        return light;
    }

    /**
     * This function returns value of light per square meter of Room
     *
     * @return light per square meter of Room
     */
    @Override
    public double getLightPerSquareMeter() {
        return light / area;
    }

    /**
     * This function returns value of light per square meter of Room and puts it in map with id as key
     *
     * @param map visitor
     * @return light per square meter of Room
     */
    @Override
    public double getLightPerSquareMeter(Map<Integer, Double> map) {
        map.put(getId(), light / area);
        return light / area;
    }

    /**
     * This function checks if Room doesn't exceed heat limit
     *
     * @param limit value of limit
     * @return boolean if limit is exceed
     */
    @Override
    public boolean isOverHeatLimit(double limit) {
        return this.heating / this.cube > limit;
    }

    /**
     * This function checks if Room doesn't exceed heat limit and puts it in the map with id as key
     *
     * @param map   visitor
     * @param limit value of limit
     * @return boolean if limit is exceed
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
