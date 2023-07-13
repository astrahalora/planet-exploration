package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Arrays;
import java.util.List;

public class MapElementPlacerImpl implements MapElementPlacer {
    private CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }

    //    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
//        Iterable<Coordinate> coordinates = List.of(
//                new Coordinate(coordinate.x(), coordinate.y())
//        );
//        List<Coordinate> elementShape = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinates, element.getDimension());
//        if(element.getPreferredLocationSymbol() == null) {
//            return checkForEmptySpaces(elementShape, map);
//        }
//        return checkForPreferredSymbolAndEmptySpaceAvailability(coordinates, coordinate, map, element.getPreferredLocationSymbol());
//    }
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
//        System.out.println(element.getName() + coordinateCalculator.getAdjacentCoordinates(coordinate, element.getDimension()));

        if (element.getPreferredLocationSymbol() == null) {
            List<Coordinate> elementShape = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, element.getDimension());
            int emptySpaces = 0;
            for (Coordinate coord : elementShape) {
                if (map[coord.x()][coord.y()].equals("")) {
                    emptySpaces += 1;
                }
            }
            return getNumberOfSymbolsFromRepresentation(element) < emptySpaces;
        }

        return checkForPreferredSymbolAndEmptySpaceAvailability(element, coordinate, map, element.getPreferredLocationSymbol());
    }


    private boolean checkForEmptySpaces(List<Coordinate> elementShape, String[][] map) {
        for (Coordinate coordinate : elementShape) {
            if (!map[coordinate.x()][coordinate.y()].equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForPreferredSymbolAndEmptySpaceAvailability(MapElement element, Coordinate coordinate, String[][] map, String preferredSymbol) {

        Iterable<Coordinate> coordinates = List.of(
                new Coordinate(coordinate.x(), coordinate.y())
        );

        List<Coordinate> elementShape = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinates, element.getDimension());
        if (map[coordinate.x()][coordinate.y()].equals("")) {
            for (Coordinate coord : elementShape) {
                System.out.println(element.getName());
                System.out.println("cord" + coord);
                if (map[coord.x()][coord.y()].equals(preferredSymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        String[][] elementRepresentation = element.getRepresentation();
        int startX = coordinate.x();
        int startY = coordinate.y();
        int dimension = element.getDimension();

        System.out.println(element.getName() + coordinate);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                map[startY + i][startX + j] = elementRepresentation[i][j];
            }
        }

    }

    private int getNumberOfSymbolsFromRepresentation(MapElement element) {
        int occupiedSpaces = 0;
        for (int i = 0; i < element.getRepresentation().length; i++) {
            for (int j = 0; j < element.getRepresentation()[i].length; j++) {
                if (!element.getRepresentation()[i][j].equals("")) {
                    occupiedSpaces += 1;
                }
            }
        }
        return occupiedSpaces;
    }
}
