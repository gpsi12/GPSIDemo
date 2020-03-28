package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gps_i.gpsidemo.R;

/**
 * 底部导航栏
 * Created by Gpsi on 2020-03-27.
 */
public class BottomView extends LinearLayout implements View.OnClickListener {

    private ImageView iv_index,iv_class,iv_mv,iv_me = null;
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
        iv_index =findViewById(R.id.iv_index);
        iv_class =findViewById(R.id.iv_classify);
        iv_mv =findViewById(R.id.iv_mv);
        iv_me =findViewById(R.id.iv_me);

        iv_index.setOnClickListener(this);
        iv_class.setOnClickListener(this);
        iv_mv.setOnClickListener(this);
        iv_me.setOnClickListener(this);

        switcBtnState(iv_index.getId());

    }
    public void setOnPageSelectListener(IOnPageSelectedListener listener){
        mListener = listener;
    }

    public void onClick(View view){
        switcBtnState(view.getId());
        switch (view.getId()){
            case R.id.iv_index:
               mListener.onPageSelect(0);
                break;
            case R.id.iv_classify:
                mListener.onPageSelect(1);
                break;
            case R.id.iv_mv:
                mListener.onPageSelect(2);
            case R.id.iv_me:
                mListener.onPageSelect(3);
                break;
        }
//        if (view.getId() == com.example.feature_demo.R.id.tx_one){
//            mListener.onPageSelect(0);
//        }if (view.getId() == com.example.feature_demo.R.id.tx_two){
//            mListener.onPageSelect(1);
//        }if (view.getId() == com.example.feature_demo.R.id.tx_three){
//            mListener.onPageSelect(2);

    }
    /**
     * s刷新按钮状态
     * @param viewId
     */
    private void switcBtnState(int viewId){
        iv_index.setImageResource(viewId == iv_index.getId() ? R.mipmap.ic_bottom_index_e:R.mipmap.ic_bottom_index_d);
        iv_class.setImageResource(viewId == iv_class.getId() ? R.mipmap.ic_bottom_class_e:R.mipmap.ic_bottom_class_d);
        iv_mv.setImageResource(viewId == iv_mv.getId() ? R.mipmap.ic_bottom_mv_e:R.mipmap.ic_bottom_mv_d);
        iv_me.setImageResource(viewId == iv_me.getId() ? R.mipmap.ic_bottom_me_e:R.mipmap.ic_bottom_me_d);
    }
    public interface IOnPageSelectedListener{
        void onPageSelect(int index);
    }
}
