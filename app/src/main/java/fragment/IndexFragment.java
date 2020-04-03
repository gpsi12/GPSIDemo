package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    private Button bt_gobigv;
    private FragmentIndexBinding fb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_index,container,false);
        fb = FragmentIndexBinding.inflate(LayoutInflater.from(mActivity));
//        View view = inflater.inflate(R.layout.fragment_index,container,false);
//        bt_gobigv = view.findViewById(R.id.bt_gobigv);
//        bt_gobigv.setOnClickListener(this);
        fb.btGobigv.setOnClickListener(this);
        return fb.getRoot();
    }

    public void openLogin(View view) {
        LoginActivity.newInstance(mActivity);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_gobigv){
            BigviewActivity.newInstance(mActivity);
        }
    }
}
