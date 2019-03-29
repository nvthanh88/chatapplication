package com.nvt.mychatapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvt.mychatapplication.R;

import com.nvt.mychatapplication.application.ChatApplication;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.base.BaseActivity;
import com.nvt.mychatapplication.fragment.LoginFragment;
import com.nvt.mychatapplication.fragment.MemberListFragment;
import com.nvt.mychatapplication.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChatApplication mApplication;
    @BindView(R.id.tool_bar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_right_function)
    ImageView rightFunction;
    @BindView(R.id.btn_member_management)
    TextView btnMemberManagement;
    Constant.FunctionType functionType = Constant.FunctionType.SETTING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mApplication = (ChatApplication) getApplication();
        attachFragment(LoginFragment.class, null, false, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToolbarTitle(int title) {
        if (toolbarTitle != null){
            if(title != 0){
                toolbarTitle.setText(title);
                showToolBar(true);
            }else showToolBar(false);
        }
    }

    public void setToolbarTitle(String title) {
        if (toolbarTitle != null){
            if(!TextUtils.isEmpty(title)){
                toolbarTitle.setText(title);
                showToolBar(true);
            }else showToolBar(false);
        }
    }

    @OnClick(R.id.btn_member_management)
    void openMemberManagmentScreen() {
        attachFragment(MemberListFragment.class, null, false, true);
    }



    @OnClick(R.id.btn_right_function)
    void openSetting(){
        attachFragment(SettingFragment.class,null,false,true);
    }

    public void showToolBar(boolean isShow) {
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setTopBarFunction(Constant.FunctionType function, boolean enableMemberManage){
        switch (function){
            case SEARCH:
                rightFunction.setImageResource(R.drawable.ic_search);
                functionType = Constant.FunctionType.SEARCH;
                break;
            case SETTING:
                rightFunction.setImageResource(R.drawable.ic_setting);
                functionType = Constant.FunctionType.SETTING;
                break;
        }
        btnMemberManagement.setVisibility(enableMemberManage?View.VISIBLE:View.INVISIBLE);
    }

}
