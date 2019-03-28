package com.nvt.mychatapplication.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.adapter.StaffAdapter;
import com.nvt.mychatapplication.base.BaseFragment;
import com.nvt.mychatapplication.model.Staff;
import com.nvt.mychatapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreatePrivateRoomFragment extends BaseFragment implements SearchView.OnQueryTextListener{
    private List<Staff> staffList = new ArrayList<>();
    private StaffAdapter staffAdapter ;
    @BindView(R.id.rcv_staff_list)
    RecyclerView rcvStaffList;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    private String queryString;

    @Override
    protected int setView() {
        return R.layout.fragment_create_private_chat_room;
    }

    @Override
    protected void implementUi() {
        staffList.add(0,new Staff("02938","高橋太郎","営業",1));
        staffList.add(1,new Staff("04938","山田花子","給与",1));
        staffList.add(2,new Staff("19387","鈴木夕子","人材",1));
        staffList.add(3,new Staff("29382","山川健二","営業",0));
        staffAdapter = new StaffAdapter(mActivity,staffList);
        rcvStaffList.setLayoutManager(new LinearLayoutManager(mActivity));
        rcvStaffList.setAdapter(staffAdapter);
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
        staffAdapter.getStaffFilter().filter(queryString);

        return true;
    }
}
