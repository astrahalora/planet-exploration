package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.service.builder.*;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.mapelements.service.placer.*;

import java.util.List;

public class Application {
    // You can change this to any directory you like
    private static final String WorkDir = "src/main";

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");
        MapConfiguration mapConfig = getConfiguration();
        MapConfigurationValidator validator = new MapConfigurationValidatorImpl();
        boolean isValid = validator.validate(mapConfig);
        System.out.println(isValid);

        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        dimensionCalculator.calculateDimension(20,3);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapConfig);
        System.out.println(coordinateCalculator.getRandomCoordinate(dimensionCalculator.calculateDimension(20,3)));
//
//        MapElementBuilder mapElementFactory = null;
//        MapElementsGenerator mapElementsGenerator = null;
//
//        MapConfigurationValidator mapConfigValidator = null;
//        MapElementPlacer mapElementPlacer = null;
//
//        MapGenerator mapGenerator = null;
//
//        createAndWriteMaps(3, mapGenerator, mapConfig);
//
//        System.out.println("Mars maps successfully generated.");
    }

    private static void createAndWriteMaps(int count, MapGenerator mapGenerator, MapConfiguration mapConfig) {
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
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                mountainSymbol
        );

        MapElementConfiguration pocketsOfWaterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralsCfg, pocketsOfWaterCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
}

