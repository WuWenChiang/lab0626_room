package com.example.aaclab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lombok.Getter;

public class BeverageViewHolder extends RecyclerView.ViewHolder {
    @Getter
    private final TextView textView1;
    public BeverageViewHolder(@NonNull View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.textView1);
    }
}
