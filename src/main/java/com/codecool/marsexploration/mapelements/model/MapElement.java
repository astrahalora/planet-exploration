package com.codecool.marsexploration.mapelements.model;

public class MapElement extends MapModel {
    private String[][] representation;
    private String name;
    private int dimension;
    private String preferredLocationSymbol;

    public MapElement(String[][] representation, String name, int dimension) {
        this(representation, name, dimension, null);
    }

    public MapElement(String[][] representation, String name, int dimension, String preferredLocationSymbol) {
        super(representation);
        this.name = name;
        this.dimension = dimension;
        this.preferredLocationSymbol = preferredLocationSymbol;
        this.representation = representation;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String[][] getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }

    public int getDimension() {
        return dimension;
    }

    public String getPreferredLocationSymbol() {
        return preferredLocationSymbol;
    }
}

