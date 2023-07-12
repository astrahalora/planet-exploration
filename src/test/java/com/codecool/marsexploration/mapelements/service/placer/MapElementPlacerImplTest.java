package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapElementPlacerImplTest {
    private final CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(getConfiguration());
    private final DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

    @Test
    void canPlaceElement_returnsTrue_forEmptyMap() {
        String[][] map = createEmptyStringArray(1000, 1000, "empty");
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(8);

        assertTrue(mapElementPlacer.canPlaceElement(mountain, map, randomCoordinate));
    }

    @Test
    void canPlaceElement_returnsFalse_forFullMap() {
        String[][] map = createEmptyStringArray(1000, 1000, "full");
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(8);

        assertFalse(mapElementPlacer.canPlaceElement(mountain, map, randomCoordinate));
    }

    @Test
    void placeElement_placesAMountain() {
        String[][] map = createEmptyStringArray(100, 100, "empty");
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);

        mapElementPlacer.placeElement(mountain, map, new Coordinate(4, 5));
    }


    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        List<MapElementConfiguration> elementsCfg = List.of();
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public static String[][] createEmptyStringArray(int rows, int columns, String type) {
        String[][] array = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(type.equals("empty")) {
                    array[i][j] = "";
                } else {
                    array[i][j] = "#";
                }
            }
        }
        return array;
    }
}