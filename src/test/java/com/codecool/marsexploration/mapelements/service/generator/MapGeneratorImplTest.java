package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorImplTest {

    @Test
    void generateMapFor26Elements_returnsTrue() {
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementBuilder,dimensionCalculator);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(getConfiguration());
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);

        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, mapElementPlacer, coordinateCalculator);

        Map myMap = mapGenerator.generate(getConfiguration());
        System.out.println(Arrays.deepToString(myMap.getRepresentation()));
    }

    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(1, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(1, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(2, 1)
                ),
                0,
                mountainSymbol
        );

        MapElementConfiguration pocketsOfWaterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                List.of(
                        new ElementToSize(2, 1)
                ),
                0,
                pitSymbol
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, pocketsOfWaterCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
}