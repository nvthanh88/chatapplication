package com.nvt.mychatapplication.fragment;

import android.widget.EditText;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.activity.MainActivity;
import com.nvt.mychatapplication.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment {
    @BindView(R.id.edt_user_name)
    EditText edtUserName;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected int setView() {
        return R.layout.fragment_login;
    }

    @Override
    protected void implementUi() {

    }

    @Override
    protected int setTitle() {
        return 0;
    }

    @OnClick(R.id.btn_login)
    void openTalkList() {
        String username = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        openFragment(TalkListFragment.class,null,false,false);

    }
    @OnClick(R.id.btn_forgot_password)
    void openFgPw(){
        openFragment(ForgotPasswordFragment.class,null,false,true);
    }
}
