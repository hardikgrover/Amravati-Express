package com.example.amravatiexpress.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amravatiexpress.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class ServiceViewHolder extends RecyclerView.ViewHolder{
    public TextView service;
    public LinearLayout linearLayout;
    public ImageView serviceImg;
    public ShimmerFrameLayout shimmerFrameLayout;





    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        service = itemView.findViewById(R.id.services);
        serviceImg = itemView.findViewById(R.id.user_img);
//        shimmerFrameLayout = itemView.findViewById(R.id.shimmer);

   }
}
