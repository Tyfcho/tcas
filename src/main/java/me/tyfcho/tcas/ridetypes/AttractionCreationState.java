package me.tyfcho.tcas.ridetypes;

public class AttractionCreationState {

    private final String attractionName;
    private String type;  // Coaster or Flatrides
    private int sectors;  // Number of sectors if coaster

    public AttractionCreationState(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSectors() {
        return sectors;
    }

    public void setSectors(int sectors) {
        this.sectors = sectors;
    }
}
