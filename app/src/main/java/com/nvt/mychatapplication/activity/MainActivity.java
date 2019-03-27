package com.nvt.mychatapplication.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.BaseActivity;

import com.nvt.mychatapplication.fragment.TalkListFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tool_bar_title)
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachFragment(TalkListFragment.class, null, false, false);

    }


    public void setToolbarTitle(int title) {
        if (toolbarTitle != null) toolbarTitle.setText(title);
    }

}
