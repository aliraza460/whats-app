package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.Model.message;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<message> messages;
    Context context;

    int Sender_view_type = 1;
    int Reciever_view_type = 2;


    public ChatAdapter(ArrayList<message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == Sender_view_type)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return new SenderrViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (messages.get(position).getUid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return Sender_view_type;
        }
        else {
            return Reciever_view_type;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        message messagemodel = messages.get(position);
        if(holder.getClass() == SenderrViewHolder.class)
        {
            ((SenderrViewHolder)holder).textView3.setText(messagemodel.getMeessage());
        }
        else {
            ((RecieverViewHolder)holder).reciever.setText(messagemodel.getMeessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class RecieverViewHolder extends RecyclerView.ViewHolder {

        TextView reciever, recievertime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

            reciever = itemView.findViewById(R.id.reciever);
            recievertime = itemView.findViewById(R.id.recievertime);

        }
        }

    public class SenderrViewHolder extends RecyclerView.ViewHolder {

        TextView textView3, textView4;
        public SenderrViewHolder(@NonNull View itemView) {
            super(itemView);

            textView4 = itemView.findViewById(R.id.textView4);
            textView3 = itemView.findViewById(R.id.textView3);

        }
    }
}
