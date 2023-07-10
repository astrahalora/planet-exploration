package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator{
    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        if(dimensionGrowth == 0){
            return size;
        }
        return(int) Math.sqrt(size) + 1 +dimensionGrowth;
    }
}
