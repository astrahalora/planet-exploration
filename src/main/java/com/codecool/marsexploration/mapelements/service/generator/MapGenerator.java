package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapModel;


public interface MapGenerator {
    MapModel generate(MapConfiguration mapConfig);
    String[][] createEmptyMapRepresentation(int rows, int columns);
}
