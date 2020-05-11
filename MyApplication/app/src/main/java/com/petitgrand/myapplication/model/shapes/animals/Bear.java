package com.petitgrand.myapplication.model.shapes.animals;


import com.petitgrand.myapplication.model.shapes.Polygon;

public class Bear extends Polygon {

    private final static float[] COORDS = {
            -2f,   1.5f, 0.0f,
            -2f,  -1.5f, 0.0f,
            2f,  -1.5f, 0.0f,
            2f,  1.5f, 0.0f,
            -1f, -1.5f, 0.0f,
            1f, -1.5f, 0.0f,
            -1f, -2.5f, 0.0f,
            1f, -2.5f, 0.0f,
            -1f, 1.5f, 0.0f,
            1f, 1.5f, 0.0f,
            -1f, 2.5f, 0.0f,
            1f, 2.5f, 0.0f};

    private final static float[] COLORS = {
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f};

    private final static short[] INDEXES = {
            0, 1, 3,
            1, 2, 3,
            4, 5, 6,
            6, 5, 7,
            8, 9, 11,
            8, 10, 11};

    public Bear(float PosX, float PosY, float scale) {
        super(PosX,PosY, scale, 6, COORDS, COLORS, INDEXES);
    }

    @Override
    public String toString(){
        return "Bear";
    }
}
