package com.petitgrand.myapplication.model.shapes.animals;

import com.petitgrand.myapplication.model.shapes.Polygon;

public class Snail extends Polygon {

    private final static float[] COORDS = {
            0.0f,   0.5f, 0.0f,
            -0.75f,  -0.75f, 0.0f,
            0.75f,  -0.75f, 0.0f };

    private final static float[] COLORS = {
            1.0f,  0.857f, 0.0f, 1.0f,
            1.0f,  0.857f, 0.0f, 1.0f,
            1.0f,  0.857f, 0.0f, 1.0f};

    private final static short[] INDEXES = { 0, 1, 2 };

    public Snail(float PosX, float PosY, float scale){
        super(PosX,PosY,scale,1,COORDS,COLORS,INDEXES);
    }

    @Override
    public String toString(){
        return "Snail";
    }
}
