package com.petitgrand.myapplication.model.shapes.animals;

import com.petitgrand.myapplication.model.shapes.Polygon;

public class Frog extends Polygon {

    private final static float[] COORDS = {
            -1.5f,   0.5f, 0.0f,
            -1.5f,  -0.75f, 0.0f,
            1.5f,  -0.75f, 0.0f,
            1.5f,  0.5f, 0.0f,
            -2f, 0.5f, 0.0f,
            2f, 0.5f, 0.0f};

    private final static float[] COLORS = {
            1f,  0.714f, 0.0f, 1.0f,
            1f,  0.714f, 0.0f, 1.0f,
            1f,  0.714f, 0.0f, 1.0f,
            1f,  0.714f, 0.0f, 1.0f,
            1f,  0.714f, 0.0f, 1.0f,
            1f,  0.714f, 0.0f, 1.0f};

    private final static short[] INDEXES = {
            0, 1, 2,
            0, 4, 1,
            0, 2, 3,
            2, 5, 3};

    public Frog(float PosX, float PosY, float scale) {
        super(PosX,PosY,scale,2,COORDS, COLORS, INDEXES);
    }

    @Override
    public String toString(){
        return "Frog";
    }
}
