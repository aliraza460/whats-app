package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.whatsapp.Adapters.ChatAdapter;
import com.example.whatsapp.Model.message;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetail extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        final String senderid = auth.getUid();
        String recieveid = getIntent().getStringExtra("userId");
        String username = getIntent().getStringExtra("username");
        String Profile = getIntent().getStringExtra("Profilepic");

        binding.username.setText(username);
        Picasso.get().load(Profile).placeholder(R.drawable.profile).into(binding.profileImage);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChatDetail.this, MainActivity.class);
                startActivity(i);
            }
        });

        final ArrayList<message> messages = new ArrayList<>();

        final ChatAdapter chatAdapter = new ChatAdapter(messages, this);
        binding.recyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);


        final String Senderroom = senderid + recieveid;
        final String recieverroom = recieveid + senderid;


        database.getReference().child("chats").child(Senderroom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren())
                {
                    message model = snapshot1.getValue(message.class);
                    messages.add(model);
                }
                  chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String data =  binding.mess.getText().toString();
               final message model = new message(senderid, data);
               model.setTimestamp(new Date().getTime());
               binding.mess.setText("");

               database.getReference().child("chats").child(Senderroom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       database.getReference().child("chats").child(recieverroom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {

                           }
                       });
                   }
               });
            }
        });
    }
}