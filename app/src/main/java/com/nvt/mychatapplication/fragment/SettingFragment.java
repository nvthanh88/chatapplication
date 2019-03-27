package com.nvt.mychatapplication.fragment;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.BaseFragment;

public class SettingFragment extends BaseFragment {
    @Override
    protected int setView() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void implementUi() {

    }

    @Override
    protected int setTitle() {
        return R.string.app_name;
    }

}
