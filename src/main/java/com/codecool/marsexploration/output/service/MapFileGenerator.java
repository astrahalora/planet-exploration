package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidator;
import com.codecool.marsexploration.mapelements.service.generator.MapGenerator;

public class MapFileGenerator {
    MapConfiguration mapConfig;
    MapConfigurationValidator validator;
    MapGenerator mapGenerator;
    MapFileWriter mapFileWriter;

    public MapFileGenerator(MapConfiguration mapConfig, MapConfigurationValidator validator,
                            MapGenerator mapGenerator, MapFileWriter mapFileWriter) {
        this.mapConfig = mapConfig;
        this.validator = validator;
        this.mapGenerator = mapGenerator;
        this.mapFileWriter = mapFileWriter;
    }

    public void generateMapFiles(int count) {
        System.out.println("Mars Exploration Sprint 1");

        if (validator.validate(mapConfig)) {
            for (int i = 0; i < count; i++) {
                mapFileWriter.writeMapFile(mapGenerator.generate(mapConfig),
                        "src/main/resources/exploration-map-" + i + ".map");
            }
        }

        System.out.println("Mars maps successfully generated.");
    }
}
