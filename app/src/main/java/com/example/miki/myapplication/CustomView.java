package com.example.miki.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Miki on 2015/8/9.
 */
public class CustomView extends View {
    private float pos = 0;
    private MyThread myThread;
    private Paint paint = new Paint();
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(30);
        canvas.drawText("hello world",pos,30,paint);
        if(myThread == null){
            myThread = new MyThread();
            myThread.start();
        }
    }
    private class MyThread extends Thread{
        @Override
        public void run() {
            while (true) {
                pos += 3;
                if(pos > getWidth()){
                    pos = 0 - paint.measureText("hello world");
                }
                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
