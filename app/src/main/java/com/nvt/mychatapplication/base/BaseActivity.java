package com.nvt.mychatapplication.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.nvt.mychatapplication.R;

public abstract class BaseActivity extends AppCompatActivity {
    private BaseFragment mCurrentFragment;

    public void attachFragment(Class<? extends BaseFragment> fragmentClass, Bundle bundles, boolean isAnimate,
                               boolean isAddToBackStack) {
        if (getBaseContext() == null) return;

        FragmentManager manager = getSupportFragmentManager();
        String tag = fragmentClass.getName();
        try {
            FragmentTransaction transaction = manager.beginTransaction();
            mCurrentFragment = fragmentClass.newInstance();
            mCurrentFragment.setRetainInstance(true);
            if (bundles == null) bundles = new Bundle();
            mCurrentFragment.setArguments(bundles);
            if (isAddToBackStack) {
                transaction.addToBackStack(tag);
            }
            transaction.replace(R.id.content, mCurrentFragment, tag);
            transaction.commitAllowingStateLoss();
            manager.executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void openActivity(Activity activity, boolean isClearStack) {
        Intent intent = new Intent(this, activity.getClass());
        if(isClearStack) intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
