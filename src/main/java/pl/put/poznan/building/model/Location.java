package pl.put.poznan.building.model;

import java.util.List;
import java.util.Map;

/**
 * This is the abstract model of Construction
 */
public abstract class Location {

    private List<Location> sublocation;
    private int id;
    private String name;

    /**
     * Empty constructor
     */
    protected Location() {
    }

    /**
     * Constructor with params:
     * 
     * @param id - int
     * @param name - name
     */
    protected Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * This function returns id
     * 
     * @return id - int
     */
    public int getId() {
        return id;
    }

    /**
     * This function returns name
     * 
     * @return name - String
     */
    public String getName() {
        return name;
    }

    /**
     * This function returns list of sublocations
     * 
     * @return sublocations - List<Location>
     */
    public List<Location> getSublocation() {
        return sublocation;
    }

    /**
     * This method set list of sublocations
     * 
     * @param sublocation - List<Location>
     */
    public void setSublocation(List<Location> sublocation) {
        this.sublocation = sublocation;
    }

    public abstract double getArea();

    public abstract double getArea(Map<Integer, Double> map);

    public abstract double getCube();

    public abstract double getCube(Map<Integer, Double> responseBody);

    public abstract double getHeating();

    public abstract double getLight();

    public abstract boolean isOverHeatLimit(double limit);

    public abstract boolean isOverHeatLimit(Map<Integer, Boolean> map, double limit);
}
