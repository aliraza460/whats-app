package com.example.whatsapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.whatsapp.Adapters.UserAdapter;
import com.example.whatsapp.Model.User;
import com.example.whatsapp.R;
import com.example.whatsapp.databinding.FragmentChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends Fragment {

    public Chat() {
        // Required empty public constructor
    }

    FragmentChatBinding binding;
    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater, container, false);

        database = FirebaseDatabase.getInstance();

        UserAdapter adapter = new UserAdapter(list, getContext());

        binding.chatrecycler.setAdapter(adapter);

        LinearLayoutManager linear = new LinearLayoutManager(getContext());
        binding.chatrecycler.setLayoutManager(linear);

        database.getReference().child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot Snapshot1 : snapshot.getChildren())
                {
                    User users = Snapshot1.getValue(User.class);
                    users.getUserid(Snapshot1.getKey());
                    list.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return binding.getRoot();
    }
}