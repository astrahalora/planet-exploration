package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.MapModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapFileWriterImpl implements MapFileWriter{

    public void writeMapFile(MapModel map, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String[][] representation = map.getRepresentation();
            for (String[] row : representation) {
                for (String element : row) {
                    writer.write(element != null && !element.isEmpty() ? element : " ");
                }

                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the map file: " + e.getMessage());
        }
    }

}
