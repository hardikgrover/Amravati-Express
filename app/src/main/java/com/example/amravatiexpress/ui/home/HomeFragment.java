package com.example.amravatiexpress.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amravatiexpress.ModelClasses.ServicesModal;

import com.example.amravatiexpress.R;
import com.example.amravatiexpress.ShowAllServices;
import com.example.amravatiexpress.adapter.serviceAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseReference shopRef;
    private LinearLayoutManager linearLayoutManager;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference serviceRef;
    private TextView viewAll1,viewAll2;
    private String ShopOrService = "";
    private ImageView searchItems;
    private EditText searchEditText;


    private RecyclerView recyclerView1,recyclerView2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        serviceRef = FirebaseDatabase.getInstance().getReference().child("1klI-chRZRKJKXiDczJtjBdlIv3sSGX5V4Hj7ObB7cYc")
                ;

        searchEditText = root.findViewById(R.id.search_edit_text);

        searchItems = root.findViewById(R.id.search_items);
        searchItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = searchEditText.getText().toString();
                Intent intent = new Intent(getContext(),ShowAllServices.class);
                intent.putExtra("service",editText);
                intent.putExtra("search","search");
                startActivity(intent);

//                showSearchItems(editText);
            }
        });

        recyclerView1 = root.findViewById(R.id.service_r1_view);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        recyclerView2 = root.findViewById(R.id.service_r2_view);
        recyclerView2.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView2.setLayoutManager(layoutManager);
//
//        searchItems.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        viewAll1 = root.findViewById(R.id.view_all);
        viewAll2 = root.findViewById(R.id.view_all_1);
        viewAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllServices.class);
                ShopOrService = "shop";
                intent.putExtra("veiw all",ShopOrService);
                intent.putExtra("search","notSearch");
//                Toast.makeText(getContext(), ShopOrService, Toast.LENGTH_SHORT).show();
                getContext().startActivity(intent);
            }
        });
        viewAll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ShowAllServices.class);
                ShopOrService ="Service";
                intent.putExtra("veiw all",ShopOrService);
                intent.putExtra("search","notSearch");

//                Toast.makeText(getContext(), ShopOrService, Toast.LENGTH_SHORT).show();


                startActivity(intent);
            }
        });


        return root;

    }

    private void showSearchItems(String editText) {
        if (editText.isEmpty()){

        }
        else
        {
            FirebaseRecyclerOptions<ServicesModal> services =
                    new FirebaseRecyclerOptions.Builder<ServicesModal>()
                    .setQuery(serviceRef.child("ShopDetails").orderByChild("Services").startAt(editText).endAt(editText),ServicesModal.class)
                    .build();
            serviceAdapter serviceAdapter = new serviceAdapter(services,getContext());

        }

    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<ServicesModal> options =
                new FirebaseRecyclerOptions.Builder<ServicesModal>()
                        .setQuery(serviceRef.child("Shops").orderByChild("ServiceName").startAt("Bank").endAt("Pharmacy"), ServicesModal.class)
                        .build();
        FirebaseRecyclerOptions<ServicesModal> options1 =
                new FirebaseRecyclerOptions.Builder<ServicesModal>()
                        .setQuery(serviceRef.child("Services"), ServicesModal.class)
                        .build();


    serviceAdapter serviceAdapter = new serviceAdapter(options,getContext());

    recyclerView2.setAdapter(serviceAdapter);

        serviceAdapter serviceAdapter1 = new serviceAdapter(options1,getContext());

    recyclerView1.setAdapter(serviceAdapter1);


        serviceAdapter.startListening();

        serviceAdapter1.startListening();




    }

}


