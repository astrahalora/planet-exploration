package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoordinateCalculatorImplTest {
    private CoordinateCalculator coordinateCalculator;
    private MapConfiguration mapConfiguration;

    @BeforeEach
    public void setUp() {
        mapConfiguration = mock(MapConfiguration.class);
        when(mapConfiguration.mapSize()).thenReturn(10);
        coordinateCalculator = new CoordinateCalculatorImpl(mapConfiguration);
    }

    @Test
    public void testGetRandomCoordinate() {
        int dimension = 3;
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(dimension);

        // Verificați dacă coordonata generată se încadrează în limitele hărții configurate
        assertEquals(true, randomCoordinate.x() >= 0 && randomCoordinate.x() <= 10 - dimension);
        assertEquals(true, randomCoordinate.y() >= 0 && randomCoordinate.y() <= 10 - dimension);
    }

    @Test
    public void testGetAdjacentCoordinatesWithCoordinate() {
        Coordinate coordinate = new Coordinate(3, 4);
        int dimension = 2;

        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);

        assertEquals(9, getCoordinateListSize(adjacentCoordinates));
    }

    @Test
    public void testGetAdjacentCoordinatesWithIterable() {
        Iterable<Coordinate> coordinates = Arrays.asList(
                new Coordinate(1, 2)

        );
        int dimension = 1;

        Iterable<Coordinate> adjacentCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinates, dimension);
        System.out.println(getCoordinateListSize(adjacentCoordinates));
        assertEquals(16, getCoordinateListSize(adjacentCoordinates));
    }

    private int getCoordinateListSize(Iterable<Coordinate> coordinates) {
        int count = 0;
        for (Coordinate coordinate : coordinates) {
            System.out.println(coordinate);
            count++;
        }
        System.out.println(count);
        return count;
    }
}
