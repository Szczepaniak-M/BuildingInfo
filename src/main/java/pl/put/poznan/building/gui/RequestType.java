package pl.put.poznan.building.gui;

enum RequestType {
    AREA("Powierzchnię"),
    CUBE("Kubaturę"),
    LIMIT("Przekroczenie limitu zużycia energii cieplnej"),
	LIGHT("Oświetlenie"),
    HEAT("Zużycie energii cieplnej na jednostkę sześcienną");

    private final String text;

    RequestType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
