package com.nvt.mychatapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.application.Constant;
import com.nvt.mychatapplication.model.Member;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ItemHolder> {
    private Context context;
    private List<Member> memberList;
    private List<Member> orig;
    private Constant.MemberSelectType memberSelectType;

    public MemberAdapter(Context context, List<Member> memberList , Constant.MemberSelectType memberSelectType) {
        this.context = context;
        this.memberList = memberList;
        this.memberSelectType = memberSelectType;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_member,viewGroup,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        Member member = memberList.get(position);
        itemHolder.staffId.setText(member.id);
        itemHolder.staffName.setText(member.name);
        itemHolder.staffPos.setText(member.position);
        itemHolder.mCheckBox.setVisibility(memberSelectType != Constant.MemberSelectType.NONE?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.staff_id)
        TextView staffId;
        @BindView(R.id.staff_name)
        TextView staffName;
        @BindView(R.id.staff_pos)
        TextView staffPos;
        @BindView(R.id.check_box)
        CheckBox mCheckBox;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public Filter getStaffFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Member> results = new ArrayList<>();
                if (orig == null)
                    orig = memberList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Member member : orig) {
                            if (member.name.toLowerCase().contains(constraint.toString()))
                                results.add(member);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                memberList = (List<Member>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
