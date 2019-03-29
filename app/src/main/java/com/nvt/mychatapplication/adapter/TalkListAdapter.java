package com.nvt.mychatapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.model.Talk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalkListAdapter extends RecyclerView.Adapter<TalkListAdapter.ViewHolder> {
    private Context context;
    private List<Talk> talkList;
    private OnItemClickListener mOnItemClickListener;
    private List<Talk> orig;

    public TalkListAdapter(Context context, List<Talk> talkList) {
        this.context = context;
        this.talkList = talkList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_talk_room, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Talk talk = talkList.get(position);
        viewHolder.dateTime.setText(talk.time);
        viewHolder.talkName.setText(talk.chatName);
        Picasso.with(context).load(talk.avatarId == 0 ? R.drawable.ic_personal : R.drawable.ic_group).into(viewHolder.talkAvatar);
        viewHolder.talkRoomItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position,talk.chatName, talk.avatarId == 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return talkList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date_time)
        TextView dateTime;
        @BindView(R.id.talk_avatar)
        ImageView talkAvatar;
        @BindView(R.id.talk_name)
        TextView talkName;
        @BindView(R.id.ll_talk_item)
        LinearLayout talkRoomItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String roomName, boolean isGroupChat);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public Filter getTalkFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Talk> results = new ArrayList<>();
                if (orig == null)
                    orig = talkList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Talk talk : orig) {
                            if (talk.chatName.toLowerCase().contains(constraint.toString()))
                                results.add(talk);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                talkList = (List<Talk>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
