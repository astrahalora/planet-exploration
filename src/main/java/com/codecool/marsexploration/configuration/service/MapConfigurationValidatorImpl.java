package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    public boolean validate(MapConfiguration mapConfig) {
        List<MapElementConfiguration> configElements = mapConfig.mapElementConfigurations();

        return hasElementWithSymbol(configElements, "#")
                && hasElementWithSymbol(configElements, "&")
                && hasElementWithSymbol(configElements, "%")
                && hasElementWithSymbol(configElements, "*")
                && elementIsSingleDimensional(configElements, "%")
                && elementIsSingleDimensional(configElements, "*")
                && elementIsMultiDimensional(configElements, "#")
                && elementIsMultiDimensional(configElements, "&")
                && elementHasRandomPlacement(configElements, "%")
                && elementHasRandomPlacement(configElements, "*")
                && !elementHasRandomPlacement(configElements, "#")
                && !elementHasRandomPlacement(configElements, "&")
                && elementHasCorrectDimensionalGrowth(configElements, "#", 3)
                && elementHasCorrectDimensionalGrowth(configElements, "&", 10)
                && elementHasCorrectDimensionalGrowth(configElements, "%", 0)
                && elementHasCorrectDimensionalGrowth(configElements, "*", 0)
                && elementsFitIntoMap(configElements, mapConfig.mapSize(), mapConfig.elementToSpaceRatio());
    }

    private boolean hasElementWithSymbol(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .anyMatch(element -> element.symbol().equals(symbol));
    }

    private boolean elementIsSingleDimensional(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.elementToSizes().size() == 1);
    }

    private boolean elementIsMultiDimensional(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.elementToSizes().size() > 1);
    }

    private boolean elementHasRandomPlacement(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .noneMatch(element -> element.preferredLocationSymbol().equals(""));
    }

    private boolean elementHasCorrectDimensionalGrowth(List<MapElementConfiguration> elementConfigurationList, String symbol, int growth) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.dimensionGrowth() == growth);
    }

    private boolean elementsFitIntoMap(List<MapElementConfiguration> elementConfigurationList, int mapSize, double elementToSpaceRatio) {
        int totalOccupiedSpace = elementConfigurationList.stream()
                .flatMap(elementConfiguration -> elementConfiguration.elementToSizes().stream())
                .mapToInt(elementToSize -> calculateDimension(elementToSize.size()) * elementToSize.elementCount())
                .sum();

        return totalOccupiedSpace < mapSize * elementToSpaceRatio;
    }

    private int calculateDimension(int dimension) {
        return dimension == 1 ? dimension : (int) Math.pow((int) Math.sqrt(dimension) + 1, 2);
    }
}
