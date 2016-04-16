package com.infinitydeltax.a20hours;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ProgressView extends View {

    private static final int totalTime = 1000*60;//*60*20;
    long timeSoFar;

    public int width;
    public int height;
    private Paint p;
    int boxesX = 12;
    int boxesY = 16;
    int boxDimX = 100;
    int boxDimY = 100;
    int boxSpaceX = 30;
    int boxSpaceY = 30;

    public ProgressView(Context context)
    {
        super(context);
        init();
    }
    public ProgressView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public ProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        p = new Paint();
        p.setColor(Color.GREEN);
        invalidate();
    }

    private void CalculateBoxInfo(int totalBoxes){ //fill out drawing params.

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldwidth, int oldheight) {
        this.width = w;
        this.height = h;
        super.onSizeChanged(w, h, oldwidth, oldheight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        boxDimX = (int) (width/boxesX * 0.9);
        boxDimY = (int) (height/boxesY * 0.9);
        boxSpaceX = (int) (width/boxesX * 0.1);
        boxSpaceY = (int) (height/boxesY * 0.1);


        super.onDraw(canvas);

        int perBoxX = (boxDimX + boxSpaceX);
        int perBoxY = (boxDimY + boxSpaceY);

        int xoffset = (int) (boxSpaceX/2.0);
        int yoffset = (int) (boxSpaceY/2.0);

        timeSoFar = ((ProgressActivity) getContext()).getTimeSoFar();
        Log.v(InProgressSkills.TAG, timeSoFar + "");
        int totalBoxesToDraw = (int) (timeSoFar/(float) totalTime*boxesX*boxesY);
        float remainder = (timeSoFar/(float) totalTime*boxesX*boxesY) - totalBoxesToDraw;
        int boxesSoFar = 0;

        outerloop: for (int y = 0; y < boxesY; y++) {
            for(int x = 0; x < boxesX; x++) {
                if(totalBoxesToDraw <= (boxesSoFar++)) break outerloop;
                canvas.drawRect(xoffset + x*perBoxX, yoffset + y*perBoxY, xoffset + x*perBoxX + boxDimX, yoffset + y*perBoxY + boxDimY, p);
                //Log.v(InProgressSkills.TAG, "drawing");
            }
        }

        invalidate();

    }
}