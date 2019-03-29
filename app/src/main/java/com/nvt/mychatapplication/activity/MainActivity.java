package com.nvt.mychatapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.nvt.mychatapplication.R;

import com.nvt.mychatapplication.application.ChatApplication;
import com.nvt.mychatapplication.base.BaseActivity;
import com.nvt.mychatapplication.base.BaseFragment;
import com.nvt.mychatapplication.fragment.LoginFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mApplication = (ChatApplication) getApplication();
        attachFragment(LoginFragment.class, null, false, false);

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

    @OnClick(R.id.btn_right_function)
    void implementFunction() {
        attachFragment(SettingFragment.class, null, false, true);
    }



    @OnClick(R.id.btn_right_function)
    void openSetting(){
        attachFragment(SettingFragment.class,null,false,true);
    }

    public void showToolBar(boolean isShow) {
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

}
