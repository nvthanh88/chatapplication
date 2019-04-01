package com.nvt.mychatapplication.fragment;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.BaseFragment;

import butterknife.OnClick;

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
        return R.string.setting_screen_title;
    }

    @OnClick(R.id.btn_goto_hide_room)
    void gotoHiddenRoom(){
        openFragment(HidenRoomFragment.class,null,false,true);
    }
}
