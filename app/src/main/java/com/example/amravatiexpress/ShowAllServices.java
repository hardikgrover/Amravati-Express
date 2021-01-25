package com.example.amravatiexpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amravatiexpress.ModelClasses.ServicesModal;
import com.example.amravatiexpress.adapter.serviceAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rey.material.widget.TextView;

public class ShowAllServices extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference serviceRef;
    private String refChild;
    private String shopOrService;
    private TextView ShopOrService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_services);
        serviceRef = FirebaseDatabase.getInstance().getReference().child("1klI-chRZRKJKXiDczJtjBdlIv3sSGX5V4Hj7ObB7cYc")
        ;
        recyclerView = findViewById(R.id.show_all_recycler_view);

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        String newString, newString1, newString2;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
                newString1 = null;
                newString2 = null;
            } else {
                newString = extras.getString("veiw all");
                newString1 = extras.getString("service");
                newString2 = extras.getString("search");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("view all");
            newString1 = (String) savedInstanceState.getSerializable("service");
            newString2 = (String) savedInstanceState.getSerializable("search");
        }
        Toast.makeText(this, newString1, Toast.LENGTH_SHORT).show();
        if (newString2.equals("search")) {
            FirebaseRecyclerOptions<ServicesModal> options1 = new FirebaseRecyclerOptions.Builder<ServicesModal>()
                    .setQuery(serviceRef.child("ShopDetails").orderByChild("Services")
                            .startAt(newString1).endAt(newString1), ServicesModal.class)

                    .build();
            serviceAdapter serviceAdapter1 = new serviceAdapter(options1,this);
            recyclerView.setAdapter(serviceAdapter1);
            serviceAdapter1.startListening();


        } else {
            if (newString.equals("shop")) {
                refChild = "Shops";
                shopOrService = "Shop";
            } else {
                refChild = "Services";
                shopOrService = "Services";
            }
            ShopOrService = findViewById(R.id.send_services);
            ShopOrService.setText(shopOrService);

            FirebaseRecyclerOptions<ServicesModal> options = new FirebaseRecyclerOptions.Builder<ServicesModal>()
                    .setQuery(serviceRef.child(refChild), ServicesModal.class)
                    .build();
            serviceAdapter  serviceAdapter= new serviceAdapter(options, this);
            recyclerView.setAdapter(serviceAdapter);
            recyclerView.setAdapter(serviceAdapter);
            serviceAdapter.startListening();


        }




    }


}