package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.MapModel;

public interface MapFileWriter
{
    void writeMapFile(MapModel map, String file);
}