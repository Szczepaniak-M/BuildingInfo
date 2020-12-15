package pl.put.poznan.building.model;

public abstract class Location {

    private int id;
    private String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    double getArea(){
        return 0;
    }

    double getCube(){
        return 0;
    }

     double getHeating(){
         return 0;
     }

     double getLight(){
         return 0;
     }
     
     
}
