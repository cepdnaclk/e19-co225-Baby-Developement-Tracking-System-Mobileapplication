package com.example.babyone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.NannyViewHolder> {

    List<ChatMessage> messageList;

    public ChatMessageAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public NannyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,null);
        return new NannyViewHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull NannyViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        if (message.getSentBy().equals(ChatMessage.SENT_BY_ME)){
            holder.chatViewLeft.setVisibility(View.GONE);
            holder.chatViewRight.setVisibility(View.VISIBLE);
            holder.chatViewRightText.setText(message.getMessage());
        }else {
            holder.chatViewRight.setVisibility(View.GONE);
            holder.chatViewLeft.setVisibility(View.VISIBLE);
            if (message.getMessage().equals("typing-anim...")){
                holder.chatViewLeftLAnim.setVisibility(View.VISIBLE);
            }else{
                holder.chatViewLeftLAnim.setVisibility(View.GONE);
                holder.chatViewLeftText.setText(message.getMessage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class NannyViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView chatViewLeft,chatViewRight;
        TextView chatViewLeftText,chatViewRightText;

        GifImageView chatViewLeftLAnim;

        public NannyViewHolder(@NonNull View itemView) {
            super(itemView);

            chatViewLeft = itemView.findViewById(R.id.chatViewLeft);
            chatViewRight = itemView.findViewById(R.id.chatViewRight);
            chatViewLeftText = itemView.findViewById(R.id.chatViewLeftText);
            chatViewRightText = itemView.findViewById(R.id.chatViewRightText);
            chatViewLeftLAnim = itemView.findViewById(R.id.chatViewLeftLAnim);
        }
    }
}
