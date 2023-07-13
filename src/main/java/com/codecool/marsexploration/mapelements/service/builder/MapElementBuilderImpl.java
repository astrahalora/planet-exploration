package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Random;

public class MapElementBuilderImpl implements MapElementBuilder{
    private final DimensionCalculator dimensionCalculator;

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator){
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int squareSide = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] elementSpace = createEmptyStringArray(squareSide, squareSide);


        if (preferredLocationSymbol.equals("")){
            return new MapElement(
                    populateSpace(size, symbol, elementSpace),
                    name,
                    dimensionGrowth
            );
        }

        return new MapElement(
                populateSpace(size, symbol, elementSpace),
                name,
                dimensionGrowth,
                preferredLocationSymbol
        );
    }

    private String[][] createEmptyStringArray(int rows, int columns) {
        String[][] array = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = "";
            }
        }

        return array;
    }

    private String[][] populateSpace (int size, String symbol, String[][] elementSpace){
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int row = random.nextInt(elementSpace.length);
            int colum = random.nextInt(elementSpace[row].length);

            if (elementSpace[row][colum].equals("")){
                elementSpace[row][colum] = symbol;
            } else {
                i--;
            }
        }
        return elementSpace;
    }
}
