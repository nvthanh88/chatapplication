package com.nvt.mychatapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.activity.MainActivity;
import com.nvt.mychatapplication.application.ChatApplication;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.fragment.GroupChatFragment;
import com.nvt.mychatapplication.fragment.PrivateChatFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected MainActivity mActivity;
    protected View mView;
    protected Unbinder unbinder;
    boolean mIsViewInitialized;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Todo implement Socket?

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initTopBarView();
        int mFragmentView = setView();
        if (mView == null) {
            mView = inflater.inflate(mFragmentView, container, false);
            unbinder = ButterKnife.bind(this, mView);
            mIsViewInitialized = false;
        } else {
            mIsViewInitialized = true;
            if (mView.getParent() != null) {
                ((ViewGroup) mView.getParent()).removeView(mView);
            }
        }
        if (!mIsViewInitialized) {
            initView();
            mIsViewInitialized = true;
        }
        return mView;
    }

    private void initTopBarView() {
        int toolbarTitle = setTitle();
        mActivity.setToolbarTitle(toolbarTitle);
        if(this instanceof PrivateChatFragment) mActivity.setTopBarFunction(Constant.FunctionType.SEARCH,false);
        else if(this instanceof GroupChatFragment) mActivity.setTopBarFunction(Constant.FunctionType.SEARCH,true);
        else mActivity.setTopBarFunction(Constant.FunctionType.SETTING,false);

    }

    protected void initView(){
        implementUi();
    }

    protected abstract int setView();
    protected abstract void implementUi();
    protected abstract int setTitle();

    public void openFragment(Class<? extends BaseFragment> fragmentClass, Bundle bundles, boolean isAnimate,
                             boolean isAddToBackStack) {
       mActivity.attachFragment(fragmentClass,bundles,isAnimate,isAddToBackStack);
    }




}
