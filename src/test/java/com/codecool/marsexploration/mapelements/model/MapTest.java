package com.codecool.marsexploration.mapelements.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void testToString() {
        String[][] arr =
                {
                        {"0", "0"}, {"0", "1"}, {"0", "2"}, {"0", "3"},
                        {"1", "0"}, {"1", "1"}, {"1", "2"}, {"1", "3"},
                        {"2", "0"}, {"2", "1"}, {"2", "2"}, {"2", "3"},
                        {"3", "0"}, {"3", "1"}, {"3", "2"}, {"3", "3"}
                };
        Map map = new Map(arr);

        assertEquals("00010203101112132021222330313233", map.toString());
    }
}