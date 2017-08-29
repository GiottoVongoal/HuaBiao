package com.huabiao.aoiin.ui.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.ALog;
import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.RegisterCommitBean;
import com.huabiao.aoiin.constant.FlagBase;
import com.huabiao.aoiin.picview.BitmapUtil;
import com.huabiao.aoiin.picview.MediaView;
import com.huabiao.aoiin.ui.ottobus.AppBus;
import com.huabiao.aoiin.ui.ottobus.ChangeRegisterIndexEvent;
import com.huabiao.aoiin.wedgit.SelectPicPopupWindow;
import com.squareup.otto.Produce;

import java.io.File;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-08-03 18:09
 * @description 自主注册第三张卡片(资料提交)
 */
public class RegisterCardThreeView extends RegisterCardBaseView {
    private TextView title_tv;
    private ImageView trade_logo_iv, proxy_iv, business_licence_iv;//商标图样;委托书+公章;营业执照+空白处盖公章
    private TextView next_tv;

    private RegisterCommitBean commitBean;

    //图片相关
    private SelectPicPopupWindow picWindow;
    private String Path = FlagBase.SDCARD_FJ_PATH;// 附件存放的路径
    private MediaView mMediaView;// 多媒体处理类
    private String folderName;// 文件夹名

    private View view;
    private Context context;

    public RegisterCardThreeView(Context context) {
        super(context);
        view = inflate(context, R.layout.register_card_three_layout, this);
        this.context = context;
        title_tv = (TextView) view.findViewById(R.id.register_card_four_title_tv);
        trade_logo_iv = (ImageView) view.findViewById(R.id.register_data_preview_trade_logo_iv);
        proxy_iv = (ImageView) view.findViewById(R.id.register_data_preview_proxy_iv);
        business_licence_iv = (ImageView) view.findViewById(R.id.register_data_preview_business_licence_iv);
        next_tv = (TextView) view.findViewById(R.id.register_card_four_next_tv);

        commitBean = RegisterCommitBean.getInstance();
        int personType = commitBean.getPersonType();
        switch (personType) {
            case 1:
                title_tv.setText("自然人注册资料提交");
                break;
            case 2:
                title_tv.setText("个体工商户注册资料提交");
                break;
            case 3:
                title_tv.setText("公司或其他组织注册资料提交");
                break;
            default:
                title_tv.setText("资料提交");
                break;
        }

        mMediaView = MediaView.getInstance(context);
        folderName = "logo";

        trade_logo_iv.setOnClickListener(new ViewOnClickListener(0));
        proxy_iv.setOnClickListener(new ViewOnClickListener(0));
        business_licence_iv.setOnClickListener(new ViewOnClickListener(0));
        next_tv.setOnClickListener(new ViewOnClickListener(0));

        // 内外置sd卡写权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7);
        }
        // 处理拍照选照相关权限
        if (ContextCompat.checkSelfPermission((Activity) context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    private class ViewOnClickListener implements OnClickListener {
        private int flag = 0;

        public ViewOnClickListener(int flag) {
            this.flag = flag;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_take_photo:
                    //拍照
                    mMediaView.takePhoto(folderName, folderName + flag, flag);
                    break;
                case R.id.btn_pick_photo:
                    //选照
                    mMediaView.selectPhoto(flag + FlagBase.REGISTER_PHOTO);
                    break;
                case R.id.register_data_preview_trade_logo_iv:
                    //商标图样
                    selectPic(view, FlagBase.TRADE_LOGO);
                    break;
                case R.id.register_data_preview_proxy_iv:
                    //委托书+公章
                    selectPic(view, FlagBase.PROXY);
                    break;
                case R.id.register_data_preview_business_licence_iv:
                    //营业执照+空白处盖公章
                    selectPic(view, FlagBase.BUSINESS_LICENCE);
                    break;
                case R.id.register_card_four_next_tv:
                    //下一步
                    AppBus.getInstance().post(produceChangeIndex());
                    break;
            }
        }
    }

    public void onActivityResult(int requestCode, Intent data) {
        if (picWindow != null && picWindow.isShowing()) {
            picWindow.dismiss();
        }
        switch (requestCode) {
            // 拍照
            case FlagBase.TRADE_LOGO:
                //商标图样
                showTakePhotoResult(FlagBase.TRADE_LOGO, trade_logo_iv);
                break;
            case FlagBase.PROXY:
                //委托书+公章
                showTakePhotoResult(FlagBase.PROXY, proxy_iv);
                break;
            case FlagBase.BUSINESS_LICENCE:
                //营业执照+空白处盖公章
                showTakePhotoResult(FlagBase.BUSINESS_LICENCE, business_licence_iv);
                break;
            // 选照
            case FlagBase.TRADE_LOGO + FlagBase.REGISTER_PHOTO:
                //商标图样
                showSelectPhotoResult(data, FlagBase.TRADE_LOGO, trade_logo_iv);
                break;
            case FlagBase.PROXY + FlagBase.REGISTER_PHOTO:
                //委托书+公章
                showSelectPhotoResult(data, FlagBase.PROXY, proxy_iv);
                break;
            case FlagBase.BUSINESS_LICENCE + FlagBase.REGISTER_PHOTO:
                //营业执照+空白处盖公章
                showSelectPhotoResult(data, FlagBase.BUSINESS_LICENCE, business_licence_iv);
                break;
        }
    }

    @Override
    public void save() {
        super.save();
        //保存图片地址
//        commitBean.setLogoImg(Path + folderName + File.separator + folderName + FlagBase.TRADE_LOGO + ".jpg");
//        commitBean.setProxyImg(Path + folderName + File.separator + folderName + FlagBase.PROXY + ".jpg");
//        commitBean.setBusinessLicenceImg(Path + folderName + File.separator + folderName + FlagBase.BUSINESS_LICENCE + ".jpg");
    }

    //开启拍照选照弹框
    private void selectPic(View view, int flag) {
        picWindow = new SelectPicPopupWindow(context, new ViewOnClickListener(flag));
        picWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //拍照回调结果
    private void showTakePhotoResult(int flag, ImageView iv) {
        String filePath = Path + folderName + File.separator + folderName + flag + ".jpg";
        ALog.i("filePath = " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            Bitmap mBitmap = BitmapUtil.createImageThumbnail(filePath);
            int degree = mMediaView.loadImageDegree(filePath);
            Bitmap bitmap = mMediaView.rotateBitmap(mBitmap, degree);
            mMediaView.saveBitmap(bitmap, filePath);
            iv.setImageBitmap(bitmap);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    //选照回调结果
    private void showSelectPhotoResult(Intent data, int flag, ImageView iv) {
        if (data != null && data.getData() != null) {
            Bitmap bitmap = mMediaView.selectPhotoSave(data, folderName, folderName + flag);
            iv.setImageBitmap(bitmap);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /**
     * 产生事件
     *
     * @return
     */
    @Produce
    public ChangeRegisterIndexEvent produceChangeIndex() {
        return new ChangeRegisterIndexEvent(3);
    }
}
