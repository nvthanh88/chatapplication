package com.nvt.mychatapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.base.Message;
import com.nvt.mychatapplication.utils.DateUtils;
import com.nvt.mychatapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<Message> mMessages;
    private List<Message> orig;
    private int[] mUsernameColors;
    private Context context;
    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        mMessages = messages;
        mUsernameColors = context.getResources().getIntArray(R.array.username_colors);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType) {
            case Message.TYPE_MESSAGE:
                layout = R.layout.item_message;
                break;
            case Message.TYPE_LOG:
                layout = R.layout.item_log;
                break;
            case Message.TYPE_ACTION:
                layout = R.layout.item_action;
                break;
        }
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Message message = mMessages.get(position);
        viewHolder.setMessage(message.getMessage());
        viewHolder.setUsername(message.getUsername());
        viewHolder.setAlign(message.getUsername());
        viewHolder.setMessageDateTime(System.currentTimeMillis());

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUsernameView;
        private TextView mMessageView;
        private LinearLayout chatContent;
        private RelativeLayout chatLayout;
        private TextView mMessageDateTime;
        ViewHolder(View itemView) {
            super(itemView);
            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mMessageView = (TextView) itemView.findViewById(R.id.message);
            chatContent = (LinearLayout) itemView.findViewById(R.id.chat_content);
            chatLayout = (RelativeLayout) itemView.findViewById(R.id.chat_layout);
            mMessageDateTime = (TextView) itemView.findViewById(R.id.message_date_time);
        }

        void setUsername(String username) {
            if (null == mUsernameView) return;
            mUsernameView.setText(username);
            mUsernameView.setTextColor(getUsernameColor(username));
        }

        void setMessage(String message) {
            if (null == mMessageView) return;
            mMessageView.setText(message);
        }

        void setMessageDateTime(long time) {
            if (null == mMessageDateTime) return;
            mMessageDateTime.setText(new DateUtils().format(time));
        }

        void setAlign(String userName) {
            //Todo display align right if my self
            if (null == chatContent || null == mMessageDateTime) return;
            if(userName.equals(Utils.getDeviceName())) {
                chatLayout.setGravity(Gravity.END);
                chatContent.setBackgroundColor(context.getResources().getColor(R.color.cmn_seek_bar_active));

            }
            else {
                chatLayout.setGravity(Gravity.START);
                chatContent.setBackgroundColor(context.getResources().getColor(R.color.cmn_tab_indicator));
            }
        }

        private int getUsernameColor(String username) {
            int hash = 7;
            for (int i = 0, len = username.length(); i < len; i++) {
                hash = username.codePointAt(i) + (hash << 5) - hash;
            }
            int index = Math.abs(hash % mUsernameColors.length);
            return mUsernameColors[index];
        }
    }
    public Filter getMessageFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Message> results = new ArrayList<>();
                if (orig == null)
                    orig = mMessages;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Message message : orig) {
                            if (message.getMessage().toLowerCase().contains(constraint.toString()))
                                results.add(message);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mMessages = (List<Message>) results.values;
                if(mMessages.size() ==0)  mMessages.add(new Message.Builder(Message.TYPE_LOG)
                        .message("No result for: "+constraint).build());
                notifyDataSetChanged();

            }
        };
    }
}
