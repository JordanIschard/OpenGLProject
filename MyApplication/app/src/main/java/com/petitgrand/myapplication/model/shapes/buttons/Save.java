package com.petitgrand.myapplication.model.shapes.buttons;

import com.petitgrand.myapplication.model.shapes.Polygon;

public class Save extends Polygon {

    private final static float[] COORDS = {
            -10f,   1f, 0.0f,
            -10f,  -0.5f, 0.0f,
            10f,   1f, 0.0f,
            10f,  -0.5f, 0.0f};

    private final static float[] COLORS = {
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f,
            1f,  0.142f, 0.0f, 1.0f
    };

    private final static short[] INDEXES = {
            0, 1, 2,
            1, 2, 3};

    public Save(float PosX, float PosY, float scale){
        super(PosX,PosY,scale,-1,COORDS,COLORS,INDEXES);
    }

    @Override
    public String toString(){
        return "Minus";
    }
}
