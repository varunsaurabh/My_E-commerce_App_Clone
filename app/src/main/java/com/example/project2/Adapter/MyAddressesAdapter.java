package com.example.project2.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.AddNewAddressActivity;
import com.example.project2.Model.MyAddressesModel;
import com.example.project2.R;

import java.util.List;

import static com.example.project2.DeliveryActivity.SELECT_ADDRESS;
import static com.example.project2.MyAddressesActivity.refreshItem;

public class MyAddressesAdapter extends RecyclerView.Adapter<MyAddressesAdapter.ViewHolder> {

    private List<MyAddressesModel> myAddressesModelList;
    private int preSelectedPosition;

    public MyAddressesAdapter(List<MyAddressesModel> myAddressesModelList) {
        this.myAddressesModelList = myAddressesModelList;
    }

    @NonNull
    @Override
    public MyAddressesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addressess_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressesAdapter.ViewHolder holder, int position) {

        String fullname = myAddressesModelList.get(position).getFullName();
        String address = myAddressesModelList.get(position).getAddress();
        String mobileno = myAddressesModelList.get(position).getMobileNo();
        Boolean selected = myAddressesModelList.get(position).getSelected();
        holder.setData(fullname, address, mobileno, selected, position);
    }

    @Override
    public int getItemCount() {
        return myAddressesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView selectAddressBtn;
        private TextView fullName, Address, MobileNo, editAddressBtn, removeAddressBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.address_fullname);
            Address = itemView.findViewById(R.id.address_address);
            MobileNo = itemView.findViewById(R.id.address_mobileno);
            editAddressBtn = itemView.findViewById(R.id.edit_address);
            removeAddressBtn = itemView.findViewById(R.id.remove_address);
            selectAddressBtn = itemView.findViewById(R.id.address_select_btn);
        }

        private void setData(String fullname, String address, String mobileno, final Boolean selected, final int position) {
            fullName.setText(fullname);
            Address.setText(address);
            MobileNo.setText(mobileno);

            removeAddressBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Address Removed", Toast.LENGTH_SHORT).show();
                }
            });

            editAddressBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), AddNewAddressActivity.class));
                }
            });

            if (selected) {
                selectAddressBtn.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successGreen)));
                preSelectedPosition = position;
            } else {
                selectAddressBtn.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorAccent)));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (preSelectedPosition != position) {
                        myAddressesModelList.get(position).setSelected(true);
                        myAddressesModelList.get(preSelectedPosition).setSelected(false);
                        refreshItem(preSelectedPosition, position);
                        preSelectedPosition = position;
                    }
                }
            });
        }
    }
}
