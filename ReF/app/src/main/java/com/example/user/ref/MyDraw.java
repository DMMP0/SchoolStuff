package com.example.user.ref;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.LinkedList;

public class MyDraw extends SurfaceView implements SurfaceHolder.Callback {

    //private MyThread myThread;
    private SurfaceHolder holder;
    private Paint paint;
    Path path;
    public LinkedList<Integer> list;

    {
        list = new LinkedList<Integer>();
    }

    public MyDraw(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GREEN);
        path = new Path();
    }

   /* public void surfaceCreated(SurfaceHolder holder) {
        myThread = new MyThread(holder, this);
        myThread.setFlag(true);
        myThread.start();
    }*/

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

   /* public void surfaceDestroyed(SurfaceHolder holder) {
        myThread.setFlag(false);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        path.rewind();
        path.reset();

        if (canvas != null) {
            canvas.drawColor(Color.BLACK);

            if (list != null && list.size() > 0) {
                path.moveTo(0, list.get(0));

                int sec;
                for (sec = 1; sec < list.size(); sec++) {
                    path.lineTo(sec, (list.get(sec) / divFactor));
                }

                canvas.drawPath(path, paint);
            }
        }
    }
}