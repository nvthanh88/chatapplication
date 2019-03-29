package com.nvt.mychatapplication.fragment;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.base.BaseFragment;

public class CreateGroupChatFragment extends BaseFragment {
    @Override
    protected int setView() {
        return R.layout.fragment_create_group_chat_room;
    }

    @Override
    protected void implementUi() {


    }

    @Override
    protected int setTitle() {
        return R.string.app_name;
    }
}
