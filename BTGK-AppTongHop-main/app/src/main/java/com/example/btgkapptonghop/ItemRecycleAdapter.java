package com.example.btgkapptonghop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRecycleAdapter extends RecyclerView.Adapter<ItemRecycleAdapter.ViewHolder> {

    ArrayList<Item> dataitems;
    Context context;
    IClickItemListener clickItemListener;

    public interface IClickItemListener{
        void onClickItem(Item item);
        void onLongCLickItem(int position);
    }

    public ItemRecycleAdapter(ArrayList<Item> dataitems, Context context,IClickItemListener listener) {
        this.dataitems = dataitems;
        this.context = context;
        this.clickItemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Item item = dataitems.get(position);
        holder.hoTen.setText(dataitems.get(position).getHoTen());
        holder.gioiThieu.setText(dataitems.get(position).getGioiThieu());
//        holder.logo.setImageResource(dataitems.get(position).getLogo());
        if (dataitems.get(position).getLogo() == 0)
        {
            holder.logo.setImageURI(dataitems.get(position).getLinkimg());
        }
        else {
            holder.logo.setImageResource(dataitems.get(position).getLogo());
        }
        holder.itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onClickItem(item);
            }
        });
        holder.itemclick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickItemListener.onLongCLickItem(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataitems.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView hoTen,gioiThieu ;
        ImageFilterView logo;
        ConstraintLayout itemclick;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logoava);
            hoTen = itemView.findViewById(R.id.textView2);
            gioiThieu = itemView.findViewById(R.id.textView3);
            itemclick= itemView.findViewById(R.id.itemclick);
        }
    }
}
