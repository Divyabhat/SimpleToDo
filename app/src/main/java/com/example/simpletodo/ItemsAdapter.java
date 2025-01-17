package com.example.simpletodo;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnClickListener{
        void OnItemClicked(int position);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);

    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;


    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener){
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        boolean attachToRoot;
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String item = items.get(position);
        holder.bind(item);
    }


    public int getItemCount(){
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        public void bind(String item){
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    clickListener.OnItemClicked(getAdapterPosition());

                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                public boolean onLongClick(View v){
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });

        }
    }
}
