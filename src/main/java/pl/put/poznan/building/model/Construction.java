package pl.put.poznan.building.model;

import java.util.LinkedList;
import java.util.List;

public class Construction extends Location {

    private List<Location> locations;

    public Construction(int id, String name) {
        super(id, name);
        locations = new LinkedList<>();
    }

//    @Override
//    double getArea() {
//        return 0;
//    }
//
//    @Override
//    double getCube() {
//        return 0;
//    }
//
//    @Override
//    double getHeating() {
//        return 0;
//    }
//
//    @Override
//    double getLight() {
//        return 0;
//    }
}
