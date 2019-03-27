package com.nvt.mychatapplication.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.adapter.TalkListAdapter;
import com.nvt.mychatapplication.base.BaseFragment;
import com.nvt.mychatapplication.model.Talk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TalkListFragment extends BaseFragment implements TalkListAdapter.OnItemClickListener {
    private List<Talk> talkList = new ArrayList<>();
    private TalkListAdapter talkListAdapter ;
    @BindView(R.id.rcv_talk_list)
    RecyclerView rcvTalkList;
    @Override
    protected int setView() {
        return R.layout.fragment_talk_list;
    }

    @Override
    protected void implementUi() {
        talkList.add(0,new Talk("01/01/2019","My Team",1));
        talkList.add(1,new Talk("01/02/2019","My Love",0));
        talkList.add(2,new Talk("01/03/2019","My Boss",0));
        talkList.add(3,new Talk("01/03/2019","My Bosses",1));
        talkListAdapter = new TalkListAdapter(mActivity,talkList);
        talkListAdapter.setOnItemClickListener(this);
        rcvTalkList.setLayoutManager(new LinearLayoutManager(mActivity));
        rcvTalkList.setAdapter(talkListAdapter);
    }

    @Override
    protected int setTitle() {
        return 0;
    }

    @Override
    public void onItemClick(int position) {
        openFragment(PrivateChatFragment.class,null,false,true);
    }
    @OnClick(R.id.btn_create_private_room)
    void gotoCreateRoom(){
        openFragment(CreatePrivateRoomFragment.class,null,false,true);
    }
}
