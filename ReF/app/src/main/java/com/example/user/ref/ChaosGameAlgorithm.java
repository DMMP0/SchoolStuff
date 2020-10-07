package com.example.user.ref;

import android.graphics.Canvas;

import java.util.Random;

public abstract class ChaosGameAlgorithm
{
    public int i,X,Y,p1x,p1y,p2x,p2y,p3x,p3y,p4x,p4y,p5x,p5y,buf;
    Canvas F1;
    Random r = new Random();
    public int DISTANCE_FRACTION = 2;

    public abstract void Draw();
    abstract void TrueDraw();
    abstract void DrawHelper();
}
