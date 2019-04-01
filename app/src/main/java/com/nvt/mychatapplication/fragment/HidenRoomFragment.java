package com.nvt.mychatapplication.fragment;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.BaseFragment;

public class HidenRoomFragment extends BaseFragment {

    @Override
    protected int setView() {
        return R.layout.fragment_hiden_room;
    }

    @Override
    protected void implementUi() {

    }

    @Override
    protected int setTitle() {
        return R.string.hidden_room;
    }
}
