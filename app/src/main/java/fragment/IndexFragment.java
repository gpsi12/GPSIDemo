package fragment;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.feature_login.LoginActivity;
import com.example.gps_i.gpsidemo.R;

import activity.BigviewActivity;

/**
 * 类描述：首页Fragment
 * Created by Gpsi on 2020-03-27.
 */
public class IndexFragment extends Fragment implements View.OnClickListener {

    private Button bt_gobigv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_index,container,false);
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        bt_gobigv = view.findViewById(R.id.bt_gobigv);
        bt_gobigv.setOnClickListener(this);
        return view;
    }

    public void openLogin(View view) {
        LoginActivity.newInstance(getActivity());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_gobigv){
            BigviewActivity.newInstance(getActivity());
        }
    }
}
