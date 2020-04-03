package activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.feature_common.BaseActivity;
import com.example.gps_i.gpsidemo.R;

import java.io.IOException;
import java.io.InputStream;

import widget.MyBigView;

/**
 * 类描述：展示超大图的activity
 * Created by Gpsi on 2020-04-03.
 */
public class BigviewActivity extends BaseActivity {
    private MyBigView myBigView;
    public static void newInstance(Context context) {
        context.startActivity(new Intent(context, BigviewActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigview);
        myBigView = findViewById(R.id.mbv_bigview);
        try {
            InputStream is = getAssets().open("qmsht.png");
            myBigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
