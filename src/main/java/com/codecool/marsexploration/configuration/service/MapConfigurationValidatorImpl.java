package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;
import java.util.stream.Collectors;

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
                && elementsFitIntoMap(configElements);
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

    private boolean elementsFitIntoMap(List<MapElementConfiguration> elementConfigurationList) {
        int totalOcuppiedSpace = 0;
        for (MapElementConfiguration elementConfiguration : elementConfigurationList) {
            for (ElementToSize elementToSize : elementConfiguration.elementToSizes()) {
                totalOcuppiedSpace += calculateDimension(elementToSize.size(), elementConfiguration.dimensionGrowth());
            }
        }
        System.out.println(totalOcuppiedSpace);
        return true;
    }

    private int calculateDimension(int dimension, int growth) {
        System.out.println("dimension: " + dimension);
        System.out.println("growth: " + growth);
//        System.out.println((int) Math.sqrt(dimension) + 1);
        if(growth == 0) {
            return 1;
        } else {
            return (int) Math.pow((int) Math.sqrt(dimension) + 1 + growth, 2);
        }
//        System.out.println((int) Math.pow((int) Math.sqrt(dimension) + 1 + growth, 2));

    }
}
