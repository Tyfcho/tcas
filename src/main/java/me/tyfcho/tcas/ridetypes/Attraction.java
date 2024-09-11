package me.tyfcho.tcas.ridetypes;

public class Attraction {

    private final String name;
    private boolean power;
    private boolean dispatch;
    private boolean gates;
    private boolean restraints;
    private boolean marshall;
    private Mode mode;

    public Attraction(String name) {
        this.name = name;
        this.power = false;
        this.dispatch = false;
        this.gates = false;
        this.restraints = false;
        this.marshall = false;
        this.mode = Mode.MANUAL; // Default mode is manual
    }

    public String getName() {
        return name;
    }

    public boolean isPowerOn() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public boolean isDispatchOn() {
        return dispatch;
    }

    public void setDispatch(boolean dispatch) {
        this.dispatch = dispatch;
    }

    public boolean areGatesOpen() {
        return gates;
    }

    public void setGates(boolean gates) {
        this.gates = gates;
    }

    public boolean areRestraintsEngaged() {
        return restraints;
    }

    public void setRestraints(boolean restraints) {
        this.restraints = restraints;
    }

    public boolean isMarshallOn() {
        return marshall;
    }

    public void setMarshall(boolean marshall) {
        this.marshall = marshall;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}

