package com.adnonstop.cameranew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.adnonstop.cameranew.R;


/**
 * Created by Xiejq on 2017/11/24.
 */

public class HomeAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private LayoutInflater mInflater;
    private String[] data = {
            "绘图",
            "图像处理",
            "图像变换",
            "相机",
            "相机 - 动画",
            "相机 - 美颜"
    };
    private ItemClickListener mItemClickListener;
    private View.OnClickListener mOnClickListener;

    public HomeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_home_type, parent, false);
        return CommonViewHolder.create(itemView);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        Button txtType = holder.getView(R.id.txt_home_type);
        txtType.setText(data[position]);
        initListener(holder, position, txtType);
    }

    private void initListener(CommonViewHolder holder, final int position, Button txtType) {
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClickListener(position, data[position]);
            }
        };
        holder.getConvertView().setOnClickListener(mOnClickListener);
        txtType.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public interface ItemClickListener {
        void onItemClickListener(int position, String type);
    }

}
