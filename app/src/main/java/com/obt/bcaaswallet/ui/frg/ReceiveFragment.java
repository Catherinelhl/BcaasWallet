package com.obt.bcaaswallet.ui.frg;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.obt.bcaaswallet.R;
import com.obt.bcaaswallet.base.BaseFragment;
import com.obt.bcaaswallet.utils.StringU;
import com.obt.qrcode.encoding.EncodingUtils;

import butterknife.BindView;

/**
 * @author catherine.brainwilliam
 * @since 2018/8/15
 * 接收页面
 */
public class ReceiveFragment extends BaseFragment {
    @BindView(R.id.ivQRCode)
    ImageView ivQRCode;
    @BindView(R.id.tvMyAddress)
    TextView tvMyAddress;

    @Override
    public int getLayoutRes() {
        return R.layout.frg_receive;
    }


    @Override
    public void initViews(View view) {
        String addressOfUser = getAddressOfUser();
        if (StringU.isEmpty(addressOfUser)) {
            showToast("获取账户地址出现异常。");
        } else {
            tvMyAddress.setText(addressOfUser);
            makeQRCodeByAddress(addressOfUser);
        }

    }

    private void makeQRCodeByAddress(String address) {
        Bitmap qrCode = EncodingUtils.createQRCode(address, context.getResources().getDimensionPixelOffset(R.dimen.d200),
                context.getResources().getDimensionPixelOffset(R.dimen.d200), null);
        ;//mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) :
        ivQRCode.setImageBitmap(qrCode);
    }


    @Override
    public void initListener() {

    }
}
