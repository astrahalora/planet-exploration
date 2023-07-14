package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.configuration.model.MapConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator{
    private final MapConfiguration mapConfiguration;

    public CoordinateCalculatorImpl(MapConfiguration mapConfiguration){
        this.mapConfiguration = mapConfiguration;
    }

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        Random random = new Random();
        int mapSize = (int) Math.ceil((Math.sqrt(mapConfiguration.mapSize())));
        return new Coordinate(random.nextInt(1, mapSize - dimension),
                                random.nextInt(1, mapSize - dimension));
    }

    @Override
        public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
            if(dimension > 1){
                int xStart = coordinate.x();
                int yStart = coordinate.y();
                int xFinal = xStart + (dimension -1);
                int yFinal = yStart + (dimension -1);

                return getCoordinates(xStart, yStart, xFinal, yFinal);
            }
            return Collections.singleton(coordinate);
        }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> elementOccupiedSpace = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            elementOccupiedSpace.add(coordinate);
        }
        Coordinate firstCoordinate = elementOccupiedSpace.get(0);
        int xStart = firstCoordinate.x() - 1;
        int yStart = firstCoordinate.y() - 1;
        int xFinal = xStart + dimension  + 1;
        int yFinal = yStart + dimension  + 1;
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
