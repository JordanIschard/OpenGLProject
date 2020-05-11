package com.petitgrand.myapplication.model.shapes.animals;

import com.petitgrand.myapplication.model.shapes.Polygon;

public class Doe extends Polygon {

    private final static float[] COORDS = {
            -2f,   1.25f, 0.0f,
            -2f,  -1.25f, 0.0f,
            2f,  -1.25f, 0.0f,
            2f,  1.25f, 0.0f,
            -1f, -1.25f, 0.0f,
            1f, -1.25f, 0.0f,
            -1f, -1.75f, 0.0f,
            1f, -1.75f, 0.0f,
            -1f, 1.25f, 0.0f,
            1f, 1.25f, 0.0f,
            -1f, 1.75f, 0.0f,
            1f, 1.75f, 0.0f};

    private final static float[] COLORS = {
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f,
            1f,  0.285f, 0.0f, 1.0f};

    private final static short[] INDEXES = {
            0, 1, 3,
            1, 2, 3,
            4, 5, 6,
            6, 5, 7,
            8, 9, 11,
            8, 10, 11,
            1, 4, 6,
            7, 5, 2,
            3, 9, 11,
            10, 8, 0};

    public Doe(float PosX, float PosY, float scale) {
        super(PosX,PosY, scale, 5,COORDS, COLORS, INDEXES);
    }

    @Override
    public String toString(){
        return "Doe";
    }
}