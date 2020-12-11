package pl.put.poznan.building.model;

public class Room extends Location {
    private double area;
    private double cube;
    private double heating;
    private double light;

    public Room(int id, String name, double area, double cube, double heating, double light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    public double getArea() {
        return area;
    }

    public double getCube() {
        return cube;
    }

    public double getHeating() {
        return heating;
    }

    public double getLight() {
        return light;
    }
}
