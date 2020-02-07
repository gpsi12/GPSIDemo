package com.example.feature_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class BottomView extends LinearLayout implements View.OnClickListener {

    private TextView tx_one = null;
    private TextView tx_two = null;
    private TextView tx_three = null;
    private IOnPageSelectedListener mListener;

    public BottomView(Context context){
        super(context);
        init(context);
    }
    public BottomView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }
    public BottomView(Context context, AttributeSet attrs,int desStyleAttr){
        super(context,attrs,desStyleAttr);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bottom,this);
        tx_one =findViewById(R.id.tx_one);
        tx_two =findViewById(R.id.tx_two);
        tx_three =findViewById(R.id.tx_three);

        tx_one.setOnClickListener(this);
        tx_two.setOnClickListener(this);
        tx_three.setOnClickListener(this);

        switcBtnState(tx_one.getId());

    }
    public void setOnPageSelectListener(IOnPageSelectedListener listener){
        mListener = listener;
    }

    public void onClick(View view){
        switcBtnState(view.getId());
//        switch (view.getId()){
//            case R.id.tx_one:
//                mListener.onPageSelect(0);
//                break;
//            case R.id.tx_two:
//                mListener.onPageSelect(1);
//                break;
//            case R.id.tx_three:
//                mListener.onPageSelect(2);
//                break;
//        }
        if (view.getId() == R.id.tx_one){
            mListener.onPageSelect(0);
        }if (view.getId() == R.id.tx_two){
            mListener.onPageSelect(1);
        }if (view.getId() == R.id.tx_three){
            mListener.onPageSelect(2);
        }else {

        }
    }
    /**
     * s刷新按钮状态
     * @param viewId
     */
    private void switcBtnState(int viewId){

    }
    public interface IOnPageSelectedListener{
        void onPageSelect(int index);
    }
}
