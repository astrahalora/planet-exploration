package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.configuration.model.MapConfiguration;

import java.util.ArrayList;
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
        int xStart = coordinate.x();
        int yStart = coordinate.y();
        int xFinal = xStart + dimension;
        int yFinal = yStart + dimension;
        return getCoordinates(xStart, yStart, xFinal, yFinal);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        ArrayList<Coordinate> elementOccupiedSpace = (ArrayList<Coordinate>) coordinates;
        Coordinate firstCoordinate = elementOccupiedSpace.get(0);
        int xStart = firstCoordinate.x() - 1;
        int yStart = firstCoordinate.y() - 1;
        int xFinal = xStart + dimension + 2;
        int yFinal = yStart + dimension + 2;
        return getCoordinates(xStart, yStart, xFinal, yFinal);
    }

    private Iterable<Coordinate> getCoordinates(int xStart, int yStart, int xFinal, int yFinal) {
        ArrayList<Coordinate> elementAndSpaceOccupiedSpace = new ArrayList<>();
        for(int i = xStart; i<= xFinal; i++){
            for(int j = yStart; j<=yFinal; j++){
                Coordinate currentCoordinate = new Coordinate(i, j);
                elementAndSpaceOccupiedSpace.add(currentCoordinate);
            }
        }
        return elementAndSpaceOccupiedSpace;
    }

}
