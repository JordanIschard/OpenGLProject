package com.petitgrand.myapplication.model.shapes.buttons;


import com.petitgrand.myapplication.model.shapes.Polygon;

public class Plus extends Polygon {

    private final static float[] COORDS = {
            -1.5f,   0.5f, 0.0f,
            -1.5f,  -0.5f, 0.0f,
            1.5f,   0.5f, 0.0f,
            1.5f,  -0.5f, 0.0f,
            -0.5f,  0.5f, 0.0f,
            -0.5f,  -0.5f, 0.0f,
            0.5f,  0.5f, 0.0f,
            0.5f,  -0.5f, 0.0f,
            -0.5f,  1.5f, 0.0f,
            0.5f,  1.5f, 0.0f,
            -0.5f,  -1.5f, 0.0f,
            0.5f,  -1.5f, 0.0f};

    private final static float[] COLORS = {
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f,
            0.819f, 0.450f, 0f, 1.0f};

    private final static short[] INDEXES = {
            0, 1, 2,
            1, 2, 3,
            4, 8, 9,
            4, 9, 6,
            10, 11, 7,
            10, 5, 7};

    public Plus(float PosX, float PosY, float scale) {
        super(PosX,PosY, scale,-1, COORDS, COLORS, INDEXES);
    }

    @Override
    public String toString(){
        return "Plus";
    }
}
