package pl.put.poznan.building.model;

public class Room extends Location {
    private final double area;
    private final double cube;
    private final double heating;
    private final double light;

    public Room(int id, String name, double area, double cube, double heating, double light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double getCube() {
        return cube;
    }

    @Override
    public double getHeating() {
        return heating;
    }

    @Override
    public double getLight() {
        return light;
    }
}
