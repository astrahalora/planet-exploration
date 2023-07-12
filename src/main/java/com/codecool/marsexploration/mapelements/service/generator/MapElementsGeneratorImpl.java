package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator{
    private final MapElementBuilder mapElementBuilder;

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementBuilder = mapElementBuilder;
    }

    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElementConfiguration> mapElementConfigurationList = mapConfig.mapElementConfigurations();
        List<MapElement> mapElementList = new ArrayList<>();
        for (MapElementConfiguration mapElementConfiguration : mapElementConfigurationList) {
            for (int i = 0; i < mapElementConfiguration.elementToSizes().size(); i++) {
                for (int j = 0; j < mapElementConfiguration.elementToSizes().get(i).elementCount(); j++) {
                    MapElement mapElement = mapElementBuilder.build(
                            mapElementConfiguration.elementToSizes().size(),
                            mapElementConfiguration.symbol(),
                            mapElementConfiguration.name(),
                            mapElementConfiguration.dimensionGrowth(),
                            mapElementConfiguration.preferredLocationSymbol());
                    mapElementList.add(mapElement);
                }
            }
        }
        return mapElementList;
    }
}
