package com.nvt.mychatapplication.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.adapter.TalkListAdapter;
import com.nvt.mychatapplication.base.BaseFragment;
import com.nvt.mychatapplication.model.Talk;
import com.nvt.mychatapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;

public class TalkListFragment extends BaseFragment implements TalkListAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    private static final int MAX_RESULT_DISPLAY = 20;
    private List<Talk> talks = new ArrayList<>();
    private TalkListAdapter talkListAdapter ;
    @BindView(R.id.rcv_talk_list)
    RecyclerView rcvTalkList;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    private String queryString;
    private InputMethodManager mImm;
    @Override
    protected int setView() {
        return R.layout.fragment_talk_list;
    }

    @Override
    protected void implementUi() {
        talks.add(0,new Talk("01/01/2019","My Team",1));
        talks.add(1,new Talk("01/02/2019","My Love",0));
        talks.add(2,new Talk("01/03/2019","My Boss",0));
        talks.add(3,new Talk("01/012/2019","My Bosses",1));
        talks.add(4,new Talk("01/01/2019","My Teams",1));
        talks.add(5,new Talk("01/09/2019","My Loves",1));
        talks.add(6,new Talk("01/07/2019","My Self",0));
        talks.add(7,new Talk("01/08/2019","My Bosses",1));
        talkListAdapter = new TalkListAdapter(mActivity,talks);
        talkListAdapter.setOnItemClickListener(this);
        rcvTalkList.setLayoutManager(new LinearLayoutManager(mActivity));
        rcvTalkList.setAdapter(talkListAdapter);
        //Todo init search
        mSearchView.setOnQueryTextListener(this);


    }

    @Override
    protected int setTitle() {
        return R.string.app_name;
    }

    @Override
    public void onItemClick(int position, String roomName) {
        Toast.makeText(mActivity,"Join room: "+roomName,Toast.LENGTH_SHORT).show();
        openFragment(PrivateChatFragment.class,null,false,true);
    }
    @OnClick(R.id.btn_create_private_room)
    void gotoCreateRoom(){
        openFragment(CreatePrivateRoomFragment.class,null,false,true);
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
        talkListAdapter.getTalkFilter().filter(queryString);

        return true;
    }


}
