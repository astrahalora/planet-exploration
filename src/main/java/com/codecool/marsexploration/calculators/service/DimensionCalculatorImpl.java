package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator{
    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        return dimensionGrowth == 0 ? size : (int) Math.sqrt(size) + 1 + dimensionGrowth;
    }
}
