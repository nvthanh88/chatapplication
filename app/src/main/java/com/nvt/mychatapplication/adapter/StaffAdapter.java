package com.nvt.mychatapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.nvt.mychatapplication.R;
import com.nvt.mychatapplication.model.Staff;
import com.nvt.mychatapplication.model.Talk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ItemHolder> {
    private Context context;
    private List<Staff> staffList;
    private List<Staff> orig;

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
    public Filter getStaffFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final List<Staff> results = new ArrayList<>();
                if (orig == null)
                    orig = staffList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Staff staff : orig) {
                            if (staff.name.toLowerCase().contains(constraint.toString()))
                                results.add(staff);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                staffList = (List<Staff>) results.values;
                notifyDataSetChanged();

            }
        };
    }
}
