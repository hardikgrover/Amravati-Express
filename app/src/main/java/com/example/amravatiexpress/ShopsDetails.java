package com.example.amravatiexpress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amravatiexpress.ModelClasses.ShopDetailsModal;
import com.example.amravatiexpress.viewHolder.ShopDetailsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ShopsDetails extends AppCompatActivity {
    private DatabaseReference shopRef,serviceRef;
    private String service;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView ServiceName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);


        serviceRef = FirebaseDatabase.getInstance().getReference()
                .child("1klI-chRZRKJKXiDczJtjBdlIv3sSGX5V4Hj7ObB7cYc").child("ShopDetails");

        service  = getIntent().getStringExtra("Service Name").toString();
//        Toast.makeText(this,service , Toast.LENGTH_SHORT).show();

        ServiceName = findViewById(R.
                id.service_name);
        ServiceName.setText(service);
        recyclerView = findViewById(R.id.shop_r_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        shopRef.orderByChild("Services")

        FirebaseRecyclerOptions<ShopDetailsModal> options =
                new FirebaseRecyclerOptions.Builder<ShopDetailsModal>()
                .setQuery(serviceRef.orderByChild("Services").startAt(service).endAt(service), ShopDetailsModal.class)
                .build();

        FirebaseRecyclerAdapter<ShopDetailsModal, ShopDetailsViewHolder> adapter =
                new FirebaseRecyclerAdapter<ShopDetailsModal, ShopDetailsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ShopDetailsViewHolder shopViewHolder, int i, @NonNull final ShopDetailsModal shops) {

                        shopViewHolder.shopName.setText(shops.getName());
                        serviceRef.addValueEventListener(new ValueEventListener() {
                            @Override

                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists() && dataSnapshot.hasChild("AccessToken")){
                                    Picasso.get().load(shops.getAccessToken()).into(shopViewHolder.shopImage);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        shopViewHolder.shopName.setText(shops.getName());
                         final String contact = shops.getContact().toString();
                        shopViewHolder.callNow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                               call(contact);
                            }
                        });
                        shopViewHolder.review.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ShopsDetails.this,Review.class);
                                startActivity(intent);
                            }
                        });







                    }

                    @NonNull
                    @Override
                    public ShopDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_layout,parent,false);
                       ShopDetailsViewHolder shopViewHolder = new ShopDetailsViewHolder(view);
                        return shopViewHolder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void call(String contact) {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ShopsDetails.this, new String[]{
                            Manifest.permission.CALL_PHONE}, 101);
                    return;
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact));

                startActivity(callIntent);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + contact));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}

