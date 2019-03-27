package com.nvt.mychatapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.model.Staff;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ItemHolder> {
    private Context context;
    private List<Staff> staffList;

    public StaffAdapter(Context context, List<Staff> staffList) {
        this.context = context;
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_staff,viewGroup,false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        Staff staff = staffList.get(position);
        itemHolder.staffId.setText(staff.id);
        itemHolder.staffName.setText(staff.name);
        itemHolder.staffPos.setText(staff.position);
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.staff_id)
        TextView staffId;
        @BindView(R.id.staff_name)
        TextView staffName;
        @BindView(R.id.staff_pos)
        TextView staffPos;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
