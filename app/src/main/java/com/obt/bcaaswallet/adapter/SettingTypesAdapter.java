package com.obt.bcaaswallet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.bean.SettingTypeBean;
import com.obt.bcaaswallet.listener.OnSettingItemSelectListener;
import com.obt.bcaaswallet.utils.StringU;

import java.util.List;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 显示设置里面所有的选项
 */
public class SettingTypesAdapter extends RecyclerView.Adapter<SettingTypesAdapter.viewHolder> {

    private Context context;
    private List<SettingTypeBean> settingTypes;

    private OnSettingItemSelectListener settingItemSelectListener;

    public SettingTypesAdapter(Context context, List<SettingTypeBean> settingTypes) {
        this.context = context;
        this.settingTypes = settingTypes;
    }

    public void setSettingItemSelectListener(OnSettingItemSelectListener settingItemSelectListener) {
        this.settingItemSelectListener = settingItemSelectListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        if (settingTypes == null) return;
        final SettingTypeBean types = settingTypes.get(i);
        if (types == null) return;
        viewHolder.tvSettingType.setText(types.getType());
        viewHolder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingItemSelectListener.onItemSelect(types);
            }
        });
        viewHolder.rlSettingTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingItemSelectListener.onItemSelect(types);


            }
        });

    }

    @Override
    public int getItemCount() {
        return settingTypes.size();
    }


    class viewHolder extends RecyclerView.ViewHolder {
        TextView tvSettingType;
        AppCompatImageView ivDetail;
        RelativeLayout rlSettingTypes;

        public viewHolder(View view) {
            super(view);
            tvSettingType = view.findViewById(R.id.tvSettingType);
            ivDetail = view.findViewById(R.id.ivDetail);
            rlSettingTypes = view.findViewById(R.id.rlSettingTypes);
        }
    }

}
