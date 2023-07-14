package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

import java.util.List;

public class MapGeneratorImpl implements MapGenerator{
    private final MapElementsGenerator mapElementsGenerator;
    private final MapElementPlacer mapElementPlacer;
    private final CoordinateCalculator coordinateCalculator;

    public MapGeneratorImpl(MapElementsGenerator mapElementsGenerator, MapElementPlacer mapElementPlacer, CoordinateCalculator coordinateCalculator) {
        this.mapElementsGenerator = mapElementsGenerator;
        this.mapElementPlacer = mapElementPlacer;
        this.coordinateCalculator = coordinateCalculator;
    }

public Map generate(MapConfiguration mapConfig) {
    int mapSize = (int) Math.ceil((Math.sqrt(mapConfig.mapSize())));
    String[][] mapToFill = createEmptyStringArray(mapSize, mapSize);
    List<MapElement> mapElements = (List<MapElement>) mapElementsGenerator.createAll(mapConfig);

    for (MapElement mapElement : mapElements) {
        Coordinate randoCoordinate = coordinateCalculator.getRandomCoordinate(mapElement.getDimension());
        while(!mapElementPlacer.canPlaceElement(mapElement, mapToFill, randoCoordinate)) {
            System.out.println("Trying config...");
            randoCoordinate = coordinateCalculator.getRandomCoordinate(mapElement.getDimension());
        }
        mapElementPlacer.placeElement(mapElement, mapToFill, randoCoordinate);
        System.out.println(mapElement.getName() + " placed");
    }

    return new Map(mapToFill);
}

    public static String[][] createEmptyStringArray(int rows, int columns) {
        String[][] array = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = "";
            }
        }

        return array;
    }
}
