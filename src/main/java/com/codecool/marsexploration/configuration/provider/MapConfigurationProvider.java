package com.codecool.marsexploration.configuration.provider;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.List;

public class MapConfigurationProvider {

    public static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                List.of(
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        MapElementConfiguration mineralsCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                mountainSymbol
        );

        MapElementConfiguration pocketsOfWaterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                pitSymbol
        );

        List<MapElementConfiguration> elementsCfg = List.of(pitsCfg, mountainsCfg, mineralsCfg, pocketsOfWaterCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
}
