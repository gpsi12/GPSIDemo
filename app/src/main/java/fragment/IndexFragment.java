package fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.feature_common.BaseFragment;
import com.example.feature_login.LoginActivity;
import com.example.gps_i.gpsidemo.R;
import com.example.gps_i.gpsidemo.databinding.FragmentIndexBinding;

import activity.BigviewActivity;

/**
 * 类描述：首页Fragment
 * Created by Gpsi on 2020-03-27.
 */
public class IndexFragment extends BaseFragment implements View.OnClickListener {

    private FragmentIndexBinding fb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_index,container,false);
        fb = FragmentIndexBinding.inflate(LayoutInflater.from(mActivity));
//        View view = inflater.inflate(R.layout.fragment_index,container,false);
        fb.btGobigv.setOnClickListener(this);
        return fb.getRoot();
    }

    public void openLogin(View view) {
        LoginActivity.newInstance(mActivity);
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.bt_gobigv) {
//            //   BigviewActivity.newInstance(mActivity);
//            final EditText editText = new EditText(mActivity);
//            new AlertDialog.Builder(mActivity).setTitle("网络错误，请重新链接").
//                    setPositiveButton("重新链接", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            int i = 0;
//                            i++;
//                            if (i == 7){
//                                //TODO
//                                new AlertDialog.Builder(mActivity).setTitle("测试弹窗一").
//                                        setView(editText).
//                                        setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                Toast.makeText(mActivity, editText.getText().toString(), Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        }).setNegativeButton("取消",null).show();
//                            }
//
//                        } // 2065
//                    }).show();
//
//        }
        if (v.getId() == R.id.bt_openPopup) {
            Toast.makeText(mActivity, "点了", Toast.LENGTH_SHORT).show();
        }
    }

}
