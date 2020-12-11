package pl.put.poznan.building.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingTransformer {

    private final String[] transforms;

    public BuildingTransformer(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }
}