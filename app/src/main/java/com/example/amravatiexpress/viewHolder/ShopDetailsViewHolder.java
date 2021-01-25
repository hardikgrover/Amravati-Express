package com.example.amravatiexpress.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amravatiexpress.R;

public class ShopDetailsViewHolder extends RecyclerView.ViewHolder{
        public TextView shopName,callNow,review;
        public ImageView shopImage;

    public ShopDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        shopName = itemView.findViewById(R.id.shop_name);
        callNow = itemView.findViewById(R.id.call_now);
        shopImage = itemView.findViewById(R.id.shop_image);
        review = itemView.findViewById(R.id.write_review);
    }
}
