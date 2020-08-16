package com.example.project2.Adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.Model.CartItemModel;
import com.example.project2.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.CART_TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewHolder(cartItemView);
            case CartItemModel.CART_TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewHolder(cartTotalView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProduct_image();
                String title = cartItemModelList.get(position).getProduct_title();
                String price = cartItemModelList.get(position).getProduct_price();
                String cut_price = cartItemModelList.get(position).getProduct_cut_price();
                ((CartItemViewHolder)holder).setItemData(resource,title,price,cut_price);
                break;
            case CartItemModel.CART_TOTAL_AMOUNT:
                String totalItem = cartItemModelList.get(position).getTotalItems();
                String totalItemPrice = cartItemModelList.get(position).getTotal_item_price();
                String deliveryCharge = cartItemModelList.get(position).getDelivery_price();
                String grandTotal = cartItemModelList.get(position).getGrand_total();
                String saveAmount = cartItemModelList.get(position).getSaved_amount();
                ((CartTotalAmountViewHolder)holder).setCartTotalAmountData(totalItem, totalItemPrice, deliveryCharge, grandTotal, saveAmount);
               break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView product_image;
        private TextView product_title, product_price, product_cut_price, product_quantity;
        //private Button remove_item_btn;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.cart_list_product_image);
            product_title = itemView.findViewById(R.id.cart_list_product_title);
            product_price = itemView.findViewById(R.id.cart_list_product_price);
            product_cut_price = itemView.findViewById(R.id.cart_list_product_cutted_price);
            product_quantity = itemView.findViewById(R.id.cart_list_product_quantity);
            //remove_item_btn = itemView.findViewById(R.id.cart_list_product_remove_item_btn);
        }

        private void setItemData(int resource, String title, String price, String cut_price) {
            product_image.setImageResource(resource);
            product_title.setText(title);
            product_price.setText(price);
            product_cut_price.setText(cut_price);

            product_quantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantity_dialog);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    quantityDialog.setCancelable(false);

                    final EditText quantity = quantityDialog.findViewById(R.id.quantity_no);
                    Button cancel = quantityDialog.findViewById(R.id.cancel_btn);
                    Button done = quantityDialog.findViewById(R.id.done_btn);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });

                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            product_quantity.setText("Qty: " + quantity.getText().toString());
                            quantityDialog.dismiss();
                        }
                    });

                    quantityDialog.show();
                }
            });
        }

    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {
        private TextView total_item, total_item_price, delivery_charge, grand_total_amount, saved_amount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
            total_item = itemView.findViewById(R.id.cart_total_num_items);
            total_item_price = itemView.findViewById(R.id.cart_total_num_items_price);
            delivery_charge = itemView.findViewById(R.id.delivery_charge);
            grand_total_amount = itemView.findViewById(R.id.cart_grand_total_amount);
            saved_amount = itemView.findViewById(R.id.saved_amount);
        }

        private void setCartTotalAmountData(String totalItem, String totalItemPrice, String deliveryCharge, String grandTotal, String saveAmount) {
            total_item.setText(totalItem);
            total_item_price.setText(totalItemPrice);
            delivery_charge.setText(deliveryCharge);
            grand_total_amount.setText(grandTotal);
            saved_amount.setText(saveAmount);
        }

    }


}
