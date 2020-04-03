package com.example.feature_demo.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.feature_demo.R;
import com.example.feature_demo.demoSurfaceView.CircularSViewActivity;
import com.example.feature_demo.demoSurfaceView.MySurfaceViewActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生命周期、普通线程池、Handler简单使用、AsyncTask使用
 */
public class GoFragment extends GtFragment {

    private Button bt_insert_cp,bt_threadpe,bt_handler,bt_asynctask,bt_fail_fast,bt_SureFaceView01,bt_SureFaceV02;

    private TextView tv_test;

    private MyHandler myHandler;
    private MyAsyncTask myAsyncTask = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("GPSI_Fragment：", "当Fragment和Activity建立关联时调用");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("GPSI_Fragment：", "正在创建");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_go, container,false);
        bt_insert_cp = view.findViewById(R.id.bt_insert_cp);
        bt_threadpe = view.findViewById(R.id.bt_threadpe);
        bt_handler = view.findViewById(R.id.bt_handler);
        bt_asynctask = view.findViewById(R.id.bt_asynctask);
        bt_fail_fast = view.findViewById(R.id.bt_fail_fast);
        bt_SureFaceView01 = view.findViewById(R.id.bt_SureFaceView01);
        bt_SureFaceV02 = view.findViewById(R.id.bt_SureFaceV02);

        tv_test = view.findViewById(R.id.tv_test);
        myHandler = new MyHandler(getActivity());
        Log.i("GPSI_Fragment：", "创建视图");
        inits();

        return view;
    }

    @Override
    protected void loadDataStart2() {
        super.loadDataStart2();
        Log.i("GPSI_Fragment", "页面--");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("GPSI_Fragment：", "与关联的Activity完成onCreate()之后调用");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("GPSI_Fragment：", "正在启动");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("GPSI_Fragment：", "获得焦点");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("GPSI_Fragment：", "正在停止");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("GPSI_Fragment：", "即将停止");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("GPSI_Fragment：", "在Fragment中的布局被移除时");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("GPSI_Fragment：", "即将销毁");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("GPSI_Fragment：", "Fragment和Activity接触关联");
        if (myAsyncTask != null) {
            myAsyncTask.cancel(true);
        }
        //将myHandler的回调和发送的消息移除掉，彻底解决内存泄漏
        if (myHandler != null){
            myHandler.removeCallbacksAndMessages(null);
            myHandler = null;
        }
    }

    private void inits() {
        bt_insert_cp.setOnClickListener(this);
        bt_threadpe.setOnClickListener(this);
        bt_handler.setOnClickListener(this);
        bt_asynctask.setOnClickListener(this);
        bt_fail_fast.setOnClickListener(this);
        bt_SureFaceView01.setOnClickListener(this);
        bt_SureFaceV02.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.bt_insert_cp) {
            insertTest();
        }
        if (view.getId() == R.id.bt_threadpe) {
            for (int i = 0; i < 30; i++) {
                final int finali = i;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("GPSI", "Thread:" + finali);
                        Log.i("GPSI", "线程：" + Thread.currentThread().getName());
                    }
                };
                threadPoolExecutor.execute(runnable);
            }
        }
        if (view.getId() == R.id.bt_handler) {
            initstart();
        }
        if (view.getId() == R.id.bt_asynctask) {
            testAsyncTask();
        }
        if (view.getId() == R.id.bt_fail_fast){
            forfailfast();
        }
        if (view.getId() == R.id.bt_SureFaceView01){
            startActivity(new Intent(getActivity(), CircularSViewActivity.class));
        }
        if (view.getId() == R.id.bt_SureFaceV02){
            startActivity(new Intent(getActivity(), MySurfaceViewActivity.class));
        }
    }


    /**
     * ConcurrentModificationException 并发修改异常
     * ArrayList的源码909
     * final void checkForComodification() {
     *     if (modCount != expectedModCount)
     *         thrownew ConcurrentModificationException();
     * }
     * 也就是说，remove 的时候执行了 checkForComodification 方法，
     * 该方法对 modCount 和 expectedModCount 进行了比较，发现两者不等，
     * 就抛出了 ConcurrentModificationException 异常
     *
     * 为什么会执行 checkForComodification 方法呢？ 反编译for each 那段代码
     * Iterator var3 = list.iterator();
     *
     * while (var3.hasNext()) {
     * 	String str = (String) var3.next();
     * 	for each 是通过迭代器 Iterator 配合 while 循环实现的
     *
     */
    private void foreachbase(){
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        for (String string : list){
            if ("2".equals(string)){
                list.remove(string);
                //避开 fail-fast,但是有重复元素要删除时，break不太合适。
                //break;
            }
        }
        Log.i("GPSI",list.toString());
    }

    /**
     * 第一次循环的时候，i 为 0，list.size() 为 3，当执行完 remove 方法后，
     * i 为 1，list.size() 却变成了 2，因为 list 的大小在 remove 后发生了变化，
     * 也就意味着“沉默王三”这个元素被跳过了。能明白吗？
     *
     * remove 之前 list.get(1) 为“沉默王三”；
     * 但 remove 之后 list.get(1) 变成了“一个文章真特么有趣的程序员”，而 list.get(0) 变成了“沉默王三”。
     */
    private void forfailfast(){
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        for (int i = list.size()-1; i >= 0; i--){
            String s = list.get(i);
            if ("2".equals(s)){
                list.remove(s);
            }
        }
    }

    /**
     * 看remove的源码
     * 虽然删除元素依然使用的是 ArrayList 的 remove 方法，
     * 但是删除完会执行 expectedModCount = modCount，保证了 expectedModCount 与 modCount 的同步。
     */
    private void iteratorfailfast(){
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> str = list.iterator();
        while (str.hasNext()){
            String s = str.next();
            if ("2".equals(s)){
                str.remove();
            }
        }

    }

    /**
     *
     * @param view view
     */
    public void bt_fail_fast(View view){
        //TODO 在Fragment里面 布局生命onClick无效么？
            Log.i("GPSIIII","---");
        }

    /**
     *
     */
    private void insertTest() {
        ContentResolver resolver = getContext().getContentResolver();
        Uri uri = Uri.parse("content://com.gpsidemo.person/insert");
        ContentValues values = new ContentValues();
        values.put("name", "葛太师");
        values.put("age", "20");
        values.put("phone", "15236633096");
        values.put("address", "河南");

        Uri insert = resolver.insert(uri, values);
        Log.i("GPSI", insert.toString());
        long id = ContentUris.parseId(insert);
        Log.i("GPSI", "插入数据产生的id+" + id);
    }

    final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5
            , 7, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(100));

    /**
     * 定义Handler子类，设置为静态内部类防止内存泄漏
     * 同时，还可以使用WeakReference弱引用持有Activity实例
     * 原因：弱引用的对象拥有短暂的生命周期，在垃圾回收器线程扫描时，一旦发现了只具有弱引用的对象
     * 不管当前内存空间是否足够，都会回收它的内存
     */
    private static class MyHandler extends Handler {
        //定义弱引用实例
        private WeakReference<Activity> weakReference;

        //在构造方法中传入需持有的Activity实例
        private MyHandler(Activity activity) {
            //使用WeakReference弱引用持有Activity实例
            weakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //获取所引用的activity
                    weakReference.get();
                    Log.i("GPSI", "接收到GDemoloadThread的发送的消息：" + msg.arg1);
                    break;
                default:
                    break;
            }
        }
    }

    class GDemoloadThread extends Thread {

        @Override
        public void run() {
            super.run();
            Message message = new Message();    //实例化消息对象
//            Message message1 = Message.obtain();
//            Message message1 = myHandler.obtainMessage();
            message.what = 1;   //  消息标示
            message.arg1 = 1214;   //
            myHandler.sendMessage(message);
            myHandler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    private void initstart() {
        GDemoloadThread gDemoloadThread = new GDemoloadThread();
        gDemoloadThread.start();
    }

    /**
     * 步骤1：
     * 创建AsyncTask子类
     * 继承AsyncTask类，为三个范型参数制定类型，若不使用可以用lang.Void代替
     * ，根据需求，在Async Task子类内部实现核心方法
     *  第一个范型：doInBackground   入参类型
     *  第二个范型：onProgressUpdate 进度变化的参数类型
     *  第三个范型：onPostExecute    入参类型，同样是doInBackground的返回值类型
     */
    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private WeakReference<TextView> textView;

        private MyAsyncTask(WeakReference<TextView> textView){
            this.textView =textView;
        }

        //执行 线程任务前的操作，根据需求复写
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.get().setText("加载中");
        }

        /**
         * 接受输入参数，执行任务中的耗时操作、返回线程任务执行的结果
         * 必须复写，从而定义线程任务
         *
         * @param strings s
         * @return s
         */
        @Override
        protected String doInBackground(String... strings) {
            //自定义线程任务
            //可调用publishProgress()显示进度，之后将执行onProgressUpdate()

            publishProgress(10);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "GPSI" + strings[0] + strings[1] + strings[2];
        }

        /**
         * 在主线程 显示线程任务执行的进度
         * 据需求复写
         *
         * @param values v
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.get().setText(values[0] + "");
        }

        /**
         * 接受线程任务执行结果，将执行结果显示到UI组件
         * 必须复写，从而自定义UI操作
         *
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.get().setText(s + "完成");

        }

        /**
         * 将异步任务设置为：取消状态
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }


    private void testAsyncTask() {
        /**
         * 步骤2：
         * 创建AsyncTask子类的实例对象（即 任务实例）
         * AsyncTask子类的实例必须在UI线程中创建
         */
//        if (myAsyncTask == null){
            myAsyncTask = new MyAsyncTask(new WeakReference<>(tv_test));
//        }

        /**
         * 步骤3：
         * 手动调用execute(xxx...xxx)从而执行异步任务
         *  1.必须在UI线程中调用
         *  2.同一个AsyncTask实例对象只能执行1次，若执行第二次将会抛出异常
         *  3.执行任务中，系统会自动调用AsyncTask的一些列方法
         *      onPreExecute() 、doInBackground()、onProgressUpdate() 、onPostExecute()
         *  4.不能手动调用上述方法
         */
        String[] strArray = new String[]{"1", "2", "3","4","5"};
        //strArray doInBackground的入参
        myAsyncTask.execute(strArray);
    }


}
