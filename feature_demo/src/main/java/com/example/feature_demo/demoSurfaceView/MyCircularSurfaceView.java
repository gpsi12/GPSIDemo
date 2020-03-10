package com.example.feature_demo.demoSurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyCircularSurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private SurfaceHolder mHolder;//用于控制SurfaceView
    private Thread t;//声明一条线程
    private boolean flag; // 线程运行的标识，用于控制线程
    private Canvas mCanvas; // 声明一张画布
    private Paint p; // 声明一支画笔
    private int x = 50, y = 50, r = 100; // 圆的坐标和半径


    public MyCircularSurfaceView(Context context) {
        super(context);
        mHolder = getHolder();//获取SurfaceHolder对象
        mHolder.addCallback(this);//为SurfaceView添加状态监听
        p = new Paint();//创建一个画笔对象
        p.setColor(Color.WHITE);//设置画笔颜色 白色
        setFocusable(true);//设置焦点
    }

    /**
     * 自定义方法，在画布上画一个圆
     */
    public void Draw(){
        mCanvas = mHolder.lockCanvas();//获得画布对象，开始对画布画画
        mCanvas.drawRGB(0,0,0);//填充画布颜色 黑色
        mCanvas.drawCircle(x,y,r,p);
        mHolder.unlockCanvasAndPost(mCanvas);//完成花花，把画布显示在屏幕上
    }

    /**
     * 当SurfaceView创建时。调用此函数
     * @param holder SurfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this);//创建一个线程对象
        flag = true;//把线程运行的标识设置为true
        t.start();//启动线程
    }

    /**
     * 当SurfaceView的视图发生改变的时候，调用此函数
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * SurfaceView销毁时，调用此函数
     * @param holder SurfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;//把线程运行的标识设置为false
    }

    /**
     * 屏幕背触摸时调用
     * @param event MotionEvent
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();//获得屏幕被触摸时对应的X轴坐标
        y = (int) event.getY();//获得屏幕被触摸时对应的Y轴坐标
        return true;
    }

    /**
     * 当用户按键时调用
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //当用户点击⬆️
//        if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
//            y--;//设置Y坐标减1
//        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public void run() {
        while (flag){
            Draw();//调用自定义画画方法

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
