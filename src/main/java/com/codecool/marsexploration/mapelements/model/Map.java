package com.codecool.marsexploration.mapelements.model;

public class Map {
    private String[][] representation;

    private boolean successfullyGenerated;

    public Map(String[][] representation) {
        this.representation = representation;
    }

    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    public void setSuccessfullyGenerated(boolean successfullyGenerated) {
        this.successfullyGenerated = successfullyGenerated;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] innerArr : arr) {
            for (String element : innerArr) {
                if (!element.isEmpty()) {
                    stringBuilder.append(element);
                } else {
                    stringBuilder.append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }

    public String[][] getRepresentation() {
        return representation;
    }
}

