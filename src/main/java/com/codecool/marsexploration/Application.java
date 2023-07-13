package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.builder.*;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.mapelements.service.placer.*;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;

import java.util.Arrays;
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
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapConfig);

        int rows = mapConfig.mapSize();
        int columns = mapConfig.mapSize();
        String[][] firstMap = createEmptyStringArray(rows, columns);
        Map map = new Map(firstMap);

//        System.out.println(map.toString());


       // System.out.println(coordinateCalculator.getAdjacentCoordinates(startCoordinate,8));

        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);

        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);
        System.out.println(mapElementsGenerator.createAll(mapConfig));

        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);

        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, mapElementPlacer, coordinateCalculator);

        MapFileWriter mapFileWriter = new MapFileWriterImpl();

        String workDir = "src/main/resources/exploration-test.map";

        mapFileWriter.writeMapFile(mapGenerator.generate(mapConfig), workDir);

        createAndWriteMaps(3, mapGenerator, mapConfig);

        System.out.println("Mars maps successfully generated.");
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

