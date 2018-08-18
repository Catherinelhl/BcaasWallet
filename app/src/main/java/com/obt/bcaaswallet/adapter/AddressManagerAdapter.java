package com.obt.bcaaswallet.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.database.Address;
import com.obt.bcaaswallet.listener.OnItemSelectListener;

import java.util.List;


/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * <p>
 * 地址管理适配容器
 */
public class AddressManagerAdapter extends RecyclerView.Adapter<AddressManagerAdapter.viewHolder> {

    private Context context;
    private List<Address> addressBeans;

    private OnItemSelectListener onItemSelect;

    public AddressManagerAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<Address> addressBeans) {
        this.addressBeans = addressBeans;
        notifyDataSetChanged();
    }

    public void setItemSelectListener(OnItemSelectListener settingItemSelectListener) {
        this.onItemSelect = settingItemSelectListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        if (addressBeans == null) return;
        final Address addressBean = addressBeans.get(i);
        if (addressBean == null) return;
        viewHolder.tvSettingType.setText(addressBean.getAddress());
        viewHolder.tvAlias.setText(addressBean.getAlias());
        viewHolder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelect.onItemSelect(addressBean);
            }
        });
        viewHolder.rlSettingTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelect.onItemSelect(addressBean);


            }
        });

    }

    @Override
    public int getItemCount() {
        return addressBeans.size();
    }


    class viewHolder extends RecyclerView.ViewHolder {
        TextView tvSettingType;
        TextView tvAlias;
        ImageButton ibDelete;
        RelativeLayout rlSettingTypes;

        public viewHolder(View view) {
            super(view);
            tvSettingType = view.findViewById(R.id.tvSettingType);
            tvAlias = view.findViewById(R.id.tvAlias);
            ibDelete = view.findViewById(R.id.ibDelete);
            rlSettingTypes = view.findViewById(R.id.rlSettingTypes);
        }
    }

}
