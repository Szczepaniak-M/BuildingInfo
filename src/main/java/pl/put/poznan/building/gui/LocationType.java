package pl.put.poznan.building.gui;

enum LocationType {
    ROOM("Pokój"),
    FLOOR("Piętro"),
    BUILDING("Budynek");

    private final String text;

    LocationType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
