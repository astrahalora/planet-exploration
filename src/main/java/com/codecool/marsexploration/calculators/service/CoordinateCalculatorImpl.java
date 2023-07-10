package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.configuration.model.MapConfiguration;

import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator{
    private final MapConfiguration mapConfiguration;

    public CoordinateCalculatorImpl(MapConfiguration mapConfiguration){
        this.mapConfiguration = mapConfiguration;
    }
    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        Random random = new Random();
        Coordinate coordinate = new Coordinate(random.nextInt(mapConfiguration.mapSize() - dimension),
                                random.nextInt(mapConfiguration.mapSize() - dimension));
        return coordinate;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        return null;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        return null;
    }
}
