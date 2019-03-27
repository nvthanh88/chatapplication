package com.nvt.mychatapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.BaseActivity;
import com.nvt.mychatapplication.fragment.LoginFragment;

public class AccountManagementActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        attachFragment( LoginFragment.class,null,false,false);

    }
}
