package com.nvt.mychatapplication.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.adapter.MemberAdapter;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.base.BaseFragment;
import com.nvt.mychatapplication.model.Member;
import com.nvt.mychatapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MemberListFragment extends BaseFragment implements SearchView.OnQueryTextListener{
    private List<Member> memberList = new ArrayList<>();
    private MemberAdapter adapter ;
    @BindView(R.id.rcv_staff_list)
    RecyclerView rcvStaffList;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    private String queryString;

    @Override
    protected int setView() {
        return R.layout.fragment_member_list;
    }

    @Override
    protected void implementUi() {
        memberList.add(0,new Member("02938","高橋太郎","営業",1));
        memberList.add(1,new Member("04938","山田花子","給与",1));
        memberList.add(2,new Member("19387","鈴木夕子","人材",1));
        memberList.add(3,new Member("29382","山川健二","営業",0));
        adapter = new MemberAdapter(mActivity, memberList, Constant.MemberSelectType.NONE);
        rcvStaffList.setLayoutManager(new LinearLayoutManager(mActivity));
        rcvStaffList.setAdapter(adapter);
        //Todo init search
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    protected int setTitle() {
        return R.string.app_name;
    }

    /*
     * Talk list real time searching
     * */

    @Override
    public boolean onQueryTextSubmit(final String query) {
        onQueryTextChange(query);
        Utils.hideInputManager(mSearchView,mActivity);
        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {

        if (newText.equals(queryString)) {
            return true;
        }
        queryString = newText;
        adapter.getStaffFilter().filter(queryString);

        return true;
    }
    @OnClick(R.id.btn_add_member)
    void gotoAddMember(){
        openFragment(AddMemberFragment.class,null,false,true);
    }
}
