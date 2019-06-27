package com.example.aaclab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aaclab.com.example.aaclab.db.BeverageEntity;

import java.util.List;

public class BeverageViewAdapter extends RecyclerView.Adapter<BeverageViewHolder> {
    private final LayoutInflater inflater;
    private List<BeverageEntity> beverages;
    private View.OnClickListener mOnItemClickListener;

    public BeverageViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BeverageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.view_item, viewGroup, false);
        return new BeverageViewHolder(itemView, mOnItemClickListener); //add second parameter
    }

    void setBeverages(List<BeverageEntity> beverages) {
        this.beverages = beverages;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageViewHolder beverageViewHolder, int i) {
        if (beverages != null) {
            BeverageEntity currentEntity = beverages.get(i);
            beverageViewHolder.getTextView1()
                    .setText(String.format("[%s](%s)",
                            currentEntity.getTitle(), currentEntity.getDetail()));
        } else {
            beverageViewHolder.getTextView1().setText("------");
        }
    }

    @Override
    public int getItemCount() {
        return (beverages != null) ? beverages.size() : 0;
    }

    public BeverageEntity getBeverageAtPosition(int position) {
        return beverages.get(position);
    }

    public void setmOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
