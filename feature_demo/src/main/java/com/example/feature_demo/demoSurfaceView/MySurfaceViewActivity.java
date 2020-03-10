package com.example.feature_demo.demoSurfaceView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

public class MySurfaceViewActivity extends Activity {
    public boolean isRun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new MySurfaceView(this));
    }

    /**
     * 视图内部类，就不领建class了
     */
    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder holder;
        private MyThread myThread;

        public MySurfaceView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(this);
            myThread = new MyThread(holder);//创建一个绘图线程
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            isRun = true;
            myThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            isRun = false;
//            myThread.stop();
            //TODO  过时了，直接退出再次进入会卡1s，几率崩溃
        }
    }

    private boolean getIsRunning() {
        return isRun;
    }

    /**
     * 线程内部类
     */
    class MyThread extends Thread {
        private SurfaceHolder holder;
        private MyThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            int count = 0;
            while (getIsRunning()) {
                Log.i("GPSI--：", "进入循环" + getIsRunning());
                Canvas c = null;
                try {
//                    synchronized (holder) {
                        //锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，
                        // 在其上面画图等操作了。
                        c = holder.lockCanvas();
                        if (c != null){
                            c.drawColor(Color.BLACK);//设置画布背景颜色
                            Paint p = new Paint();//创建画笔
                            p.setColor(Color.WHITE);
                            Rect r = new Rect(100, 50, 800, 650);
                            c.drawRect(r, p);
                            p.setColor(Color.BLUE);
                            p.setTextSize(50f);
                            c.drawText("第" + (count++) + "秒", 200, 200, p);
                            Thread.sleep(1000);
                        }
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        //结束锁定画图，并提交修改
                        holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        isRun = false;
        Log.i("GPSI--：","onDestroy+"+isRun);
        super.onDestroy();

    }
}
