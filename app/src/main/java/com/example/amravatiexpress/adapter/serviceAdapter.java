package com.example.amravatiexpress.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.amravatiexpress.R;
import com.example.amravatiexpress.ModelClasses.ServicesModal;
import com.example.amravatiexpress.ShopsDetails;
import com.example.amravatiexpress.viewHolder.ServiceViewHolder;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class serviceAdapter extends FirebaseRecyclerAdapter<ServicesModal, ServiceViewHolder> {
    private Context context;


    public serviceAdapter(@NonNull FirebaseRecyclerOptions<ServicesModal> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ServiceViewHolder serviceViewHolder, int i, @NonNull final ServicesModal services) {
//        serviceViewHolder.shimmerFrameLayout.startShimmer();
        serviceViewHolder.service.setText(services.getServiceName());
        Picasso.get().load(services.getAccessToken()).into(serviceViewHolder.serviceImg);
//        serviceViewHolder.shimmerFrameLayout.stopShimmer();
        serviceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShopsDetails.class);
                intent.putExtra("Service Name",services.getServiceName());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_services_layout,parent,false);
        ServiceViewHolder holder = new ServiceViewHolder(view);
        return holder;

    }
}
