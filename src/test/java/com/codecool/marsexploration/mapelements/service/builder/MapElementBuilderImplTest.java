package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapElementBuilderImplTest {

    @Test
    void testIfSize20Returns20Elements() {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);

        int mountainSize = 20;
        int mineralSize = 1;

        MapElement mapElement = mapElementFactory.build(mountainSize, "#", "mountain", 3, "");
        MapElement mapElement1 = mapElementFactory.build(mineralSize, "%", "mineral", 1, "#");

        assertEquals(mapElement.toString().length(), mountainSize);
        assertEquals(mapElement1.toString().length(), mineralSize);
    }

    @Test
    void testIfSize20Returns64TotalArea() {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);

        int mountainSize = 20;

        Map mapElement = mapElementFactory.build(mountainSize, "#", "mountain", 3, "");

        int rowNumber = mapElement.getRepresentation().length;
        int columNumber = mapElement.getRepresentation()[0].length;

        assertEquals(64, rowNumber * columNumber);
    }
}