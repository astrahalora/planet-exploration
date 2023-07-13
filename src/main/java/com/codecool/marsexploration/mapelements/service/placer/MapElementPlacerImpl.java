package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

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
        public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate){
            return true;
        }


    private boolean checkForEmptySpaces(List<Coordinate> elementShape, String[][] map) {
        for (Coordinate coordinate : elementShape) {
            if(!map[coordinate.x()][coordinate.y()].equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForPreferredSymbolAndEmptySpaceAvailability(Iterable<Coordinate> elementShape, Coordinate coordinate, String[][] map, String preferredSymbol) {
        if(map[coordinate.x()][coordinate.y()].equals("")) {
            for (Coordinate element : elementShape) {
                if(map[element.x()][element.y()].equals(preferredSymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        String[][] elementRepresentation = element.getRepresentation();
        for (int i = 0; i < elementRepresentation.length; i++) {
            for (int j = 0; j < elementRepresentation[i].length; j++) {
                map[coordinate.x()+i][coordinate.y()+j] = elementRepresentation[i][j];
            }
        }
//        System.out.println(Arrays.deepToString(map));
    }
}
