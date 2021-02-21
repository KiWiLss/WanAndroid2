package com.kiwilss.wanandroid.dialog.loading;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kiwilss.wanandroid.R;
import com.lxj.xpopup.core.CenterPopupView;

/**
 * @author : Lss kiwilss
 * @FileName: CustomPopup
 * @e-mail : kiwilss@163.com
 * @time : 2019-05-31
 * @desc : {DESCRIPTION}白色圆环带字
 */
public class LoadingPopupTwo extends CenterPopupView {
    private TextView tv_title;

    public LoadingPopupTwo(@NonNull Context context) {
        super(context);
    }

   @Override
    protected int getImplLayoutId() {
        return R.layout.popup_center_loading_two;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        tv_title = findViewById(com.lxj.xpopup.R.id.tv_title);
        if(title!=null){
            tv_title.setVisibility(VISIBLE);
            tv_title.setText(title);
        }else if (title2 != 0){
            tv_title.setVisibility(VISIBLE);
            tv_title.setText(title2);
        }
    }

    private String title;
    public LoadingPopupTwo setTitle(String title){
        this.title = title;
        return this;
    }
    private int title2 = 0;
    public LoadingPopupTwo setTitle(int title){
        this.title2 = title;
        return this;
    }
}
