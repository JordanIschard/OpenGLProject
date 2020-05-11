package com.petitgrand.myapplication.model.shapes.animals;

import com.petitgrand.myapplication.model.shapes.Polygon;

public class Fox extends Polygon {

    private final static float[] COORDS = {
            -1f,   1.25f, 0.0f,
            -1f,  -1.25f, 0.0f,
            1f,  -1.25f, 0.0f,
            1f,  1.25f, 0.0f,
            -3f, -1.25f, 0.0f,
            3f, 1.25f, 0.0f};

    private final static float[] COLORS = {
            1f,  0.428f, 0.0f, 1.0f,
            1f,  0.428f, 0.0f, 1.0f,
            1f,  0.428f, 0.0f, 1.0f,
            1f,  0.428f, 0.0f, 1.0f,
            1f,  0.428f, 0.0f, 1.0f,
            1f,  0.428f, 0.0f, 1.0f};

    private final static short[] INDEXES = {
            0, 1, 3,
            1, 2, 3,
            0, 1, 4,
            2, 3, 5};

    public Fox(float PosX, float PosY, float scale) {
        super(PosX,PosY, scale,4, COORDS, COLORS, INDEXES);
    }

    @Override
    public String toString(){
        return "Fox";
    }
}
