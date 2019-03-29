package com.nvt.mychatapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
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
    private OnCheckBoxClickListener mOnCheckBoxClickListener;
    private List<String> chooseMember = new ArrayList<>();
    private boolean isSearching =false;

    public MemberAdapter(Context context, List<Member> memberList , Constant.MemberSelectType memberSelectType) {
        this.context = context;
        this.memberList = memberList;
        this.memberSelectType = memberSelectType;
        initCheckBoxView(memberSelectType);
    }

    private void initCheckBoxView(Constant.MemberSelectType memberSelectType) {
        switch (memberSelectType){
            case NONE:
                break;
            case ONE:
                break;
            case MULTI:
                break;
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_member,viewGroup,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder itemHolder, final int position) {
        final Member member = memberList.get(position);
        itemHolder.staffId.setText(member.id);
        itemHolder.staffName.setText(member.name);
        itemHolder.staffPos.setText(member.position);
        itemHolder.mCheckBox.setVisibility(memberSelectType != Constant.MemberSelectType.NONE?View.VISIBLE:View.GONE);
        if (memberSelectType == Constant.MemberSelectType.MULTI) {
            itemHolder.mCheckBox.setChecked(member.isChoose);
            TypedValue value = new TypedValue();
            itemHolder.mCheckBox.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
            int checkMarkDrawableResId = value.resourceId;
            itemHolder.mCheckBox.setCheckMarkDrawable(checkMarkDrawableResId);
            itemHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseMember.clear();
                    member.setChoose(!member.isChoose);
                    for(Member m : isSearching?orig:memberList){
                        if(m.isChoose) chooseMember.add(m.name);
                    }
                    mOnCheckBoxClickListener.onCheck(itemHolder.getAdapterPosition(),chooseMember);
                    notifyDataSetChanged();
                }
            });
        } else {
            if(chooseMember.size()>0){
                for(String s:chooseMember){
                    if(s.equals(member.name))itemHolder.mCheckBox.setChecked(true);
                    else itemHolder.mCheckBox.setChecked(false);
                }
            }else itemHolder.mCheckBox.setChecked(false);
            TypedValue value = new TypedValue();
            itemHolder.mCheckBox.getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorSingle, value, true);
            int checkMarkDrawableResId = value.resourceId;
            itemHolder.mCheckBox.setCheckMarkDrawable(checkMarkDrawableResId);
            itemHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseMember.clear();
                    for(int i = 0; i< memberList.size();i++){
                        if(i == itemHolder.getAdapterPosition()) {
                            memberList.get(i).setChoose(!member.isChoose);
                            if(memberList.get(i).isChoose) chooseMember.add(memberList.get(i).name);
                            mOnCheckBoxClickListener.onCheck(itemHolder.getAdapterPosition(),chooseMember);
                        }else{
                            memberList.get(i).setChoose(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }


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
        CheckedTextView mCheckBox;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public Filter getMemberFilter() {
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
                            if (member.id.toLowerCase().contains(constraint.toString()))
                                results.add(member);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                isSearching = true;
                memberList = (List<Member>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public interface OnCheckBoxClickListener{
        void onCheck(int pos, List<String> names);
    }
    public void setOnCheckBoxClickListener(OnCheckBoxClickListener listener){
        mOnCheckBoxClickListener = listener;
    }
}
