package com.nvt.mychatapplication.activity;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.nvt.mychatapplication.R;

import com.nvt.mychatapplication.application.ChatApplication;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.base.BaseActivity;
import com.nvt.mychatapplication.fragment.LoginFragment;
import com.nvt.mychatapplication.fragment.MemberListFragment;
import com.nvt.mychatapplication.fragment.SettingFragment;
import com.nvt.mychatapplication.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ChatApplication mApplication;
    @BindView(R.id.tool_bar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    LinearLayout mToolbar;
    @BindView(R.id.btn_right_function)
    ImageView rightFunction;
    @BindView(R.id.btn_member_management)
    TextView btnMemberManagement;
    @BindView(R.id.search_view)
    SearchView searchView;
    Constant.FunctionType functionType = Constant.FunctionType.SETTING;
    private OnSearchingListener mOnSearchingListener;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("messaging_private");


        mApplication = (ChatApplication) getApplication();
        attachFragment(LoginFragment.class, null, false, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToolbarTitle(int title) {
        if (toolbarTitle != null) {
            if (title != 0) {
                toolbarTitle.setText(title);
                showToolBar(true);
            } else showToolBar(false);
        }
    }

    public void setToolbarTitle(String title) {
        if (toolbarTitle != null) {
            if (!TextUtils.isEmpty(title)) {
                toolbarTitle.setText(title);
                showToolBar(true);
            } else showToolBar(false);
        }
    }
    public void showSearchView(boolean isShow) {
        searchView.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    @OnClick(R.id.btn_member_management)
    void openMemberManagementScreen() {
        attachFragment(MemberListFragment.class, null, false, true);
    }


    @OnClick(R.id.btn_right_function)
    void implementFunction() {
        switch (functionType) {
            case SETTING:
                attachFragment(SettingFragment.class, null, false, true);
                break;
            case SEARCH:
                implementSearch();
                break;
        }
    }

    private void implementSearch() {
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Utils.hideInputManager(searchView,MainActivity.this);
                searchView.setVisibility(View.GONE);
                return true;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                onQueryTextChange(query);
                Utils.hideInputManager(searchView,MainActivity.this);
                searchView.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.equals(queryString)) {
                    return true;
                }
                queryString = newText;
                mOnSearchingListener.search(queryString);
                return true;
            }
        });
    }

    public void showToolBar(boolean isShow) {
        mToolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setTopBarFunction(Constant.FunctionType function, boolean enableMemberManage) {
        switch (function) {
            case SEARCH:
                rightFunction.setImageResource(R.drawable.ic_search);
                functionType = Constant.FunctionType.SEARCH;
                break;
            case SETTING:
                rightFunction.setImageResource(R.drawable.ic_setting);
                functionType = Constant.FunctionType.SETTING;
                break;
        }
        btnMemberManagement.setVisibility(enableMemberManage ? View.VISIBLE : View.INVISIBLE);
    }
    public interface OnSearchingListener{
        void search(String key);
    }
    public void setOnSearchingListener(OnSearchingListener listener){
        mOnSearchingListener = listener;
    }

    @OnClick(R.id.btn_back)
    void backToPrevious(){
        super.onBackPressed();
    }

}
