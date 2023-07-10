package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
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
                && isSingleDimensional(configElements, "%")
                && isSingleDimensional(configElements, "*")
                && isMultiDimensional(configElements, "#")
                && isMultiDimensional(configElements, "&")
                && hasRandomPlacement(configElements, "%")
                && hasRandomPlacement(configElements, "*")
                && !hasRandomPlacement(configElements, "#")
                && !hasRandomPlacement(configElements, "&")
                && hasCorrectDimensionalGrowth(configElements, "#", 3)
                && hasCorrectDimensionalGrowth(configElements, "&", 10)
                && hasCorrectDimensionalGrowth(configElements, "%", 0)
                && hasCorrectDimensionalGrowth(configElements, "*", 0)
                && elementsFitIntoMap(configElements, mapConfig.mapSize(), mapConfig.elementToSpaceRatio());
    }

    private boolean hasElementWithSymbol(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .anyMatch(element -> element.symbol().equals(symbol));
    }

    private boolean isSingleDimensional(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.elementToSizes().size() == 1);
    }

    private boolean isMultiDimensional(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.elementToSizes().size() > 1);
    }

    private boolean hasRandomPlacement(List<MapElementConfiguration> elementConfigurationList, String symbol) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .noneMatch(element -> element.preferredLocationSymbol().equals(""));
    }

    private boolean hasCorrectDimensionalGrowth(List<MapElementConfiguration> elementConfigurationList, String symbol, int growth) {
        return elementConfigurationList.stream()
                .filter(elements -> elements.symbol().equals(symbol))
                .allMatch(element -> element.dimensionGrowth() == growth);
    }

    private boolean elementsFitIntoMap(List<MapElementConfiguration> elementConfigurationList, int mapSize, double elementToSpaceRatio) {
        int totalOcuppiedSpace = 0;
        for (MapElementConfiguration elementConfiguration : elementConfigurationList) {
            for (ElementToSize elementToSize : elementConfiguration.elementToSizes()) {
                totalOcuppiedSpace += calculateDimension(elementToSize.size()) * elementToSize.elementCount();
            }
        }
        return totalOcuppiedSpace < mapSize * elementToSpaceRatio;
    }

    private int calculateDimension(int dimension) {
        if(dimension == 1) {
            return dimension;
        } else {
            return (int) Math.pow((int) Math.sqrt(dimension) + 1, 2);
        }
    }
}
