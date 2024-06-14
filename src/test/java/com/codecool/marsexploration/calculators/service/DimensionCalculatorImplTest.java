package com.codecool.marsexploration.calculators.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionCalculatorImplTest {
    private final DimensionCalculator calculator = new DimensionCalculatorImpl();
    @Test
    void testCasesDimension20AndGrowth3_returns8(){
        assertEquals(8,calculator.calculateDimension(20,3));
    }
    @Test
    void testCasesDimension20AndGrowth3_notReturns8(){
        assertNotEquals(9,calculator.calculateDimension(20,3));
    }

    @Test
    void testCaseDimension10AndGrowth3_returns7(){
        assertEquals(7,calculator.calculateDimension(10,3));
    }
    @Test
    void testCaseGrowth0(){
        assertEquals(1,calculator.calculateDimension(1,0));
    }
}