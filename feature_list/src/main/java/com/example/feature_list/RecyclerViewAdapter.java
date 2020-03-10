package com.example.feature_list;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ItemBean> mList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        LinearLayout linearLayout;
        TextView goodsTView;
        TextView license;

        public ViewHolder(View view) {
            super(view);
            goodsTView = view.findViewById(R.id.goods);
            license = view.findViewById(R.id.license);
            titleTextView = (TextView) view.findViewById(R.id.name);
            linearLayout = (LinearLayout) view.findViewById(R.id.layout);

        }
    }

    public RecyclerViewAdapter(List<ItemBean> List) {
        mList = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    //对RecyclerView的子项数据进行赋值
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        SpannableString spannableString = new SpannableString("轮胎 1000G");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(foregroundColorSpan,0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        viewHolder.goodsTView.setText(spannableString);

        final ItemBean itemBean = mList.get(i);
        viewHolder.titleTextView.setText(itemBean.getName());
        viewHolder.license.setText(itemBean.getOrder());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                ItemBean itemBean1 = mList.get(position);
                Toast.makeText(v.getContext(), itemBean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    //获取RecyclerView有多少子项
    public int getItemCount() {
        return mList.size();
    }
}
//    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
//    ViewHolder holder = new ViewHolder(view);
//        return holder;