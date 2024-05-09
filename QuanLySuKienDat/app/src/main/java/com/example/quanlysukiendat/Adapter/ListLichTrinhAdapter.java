
package com.example.quanlysukiendat.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Model.khachmoiModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;
import com.example.quanlysukiendat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListLichTrinhAdapter extends RecyclerView.Adapter<ListLichTrinhAdapter.CustomerViewholder>{
    private List<lichtrinhModel> mlistuserchoose;
    private List<lichtrinhModel> mlistuserchoose1;
    Context context;
    Activity mactivity;
    public ListLichTrinhAdapter(List<lichtrinhModel> mlistuserchoose) {
        this.mlistuserchoose = mlistuserchoose;
        this.mlistuserchoose1=mlistuserchoose;
    }

    public ListLichTrinhAdapter(Context context, List<lichtrinhModel> mListUser) {
        this.context = context;
        this.mlistuserchoose = mListUser;
        this.mlistuserchoose1=mListUser;
        this.mactivity = mactivity;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichtrinh,parent,false);
        return new CustomerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, @SuppressLint("RecyclerView") int position) {
        lichtrinhModel user=mlistuserchoose.get(position);
        if(user==null)
        {
            return;
        }

        holder.name.setText(user.getNamelt());
        holder.time.setText(user.getTimebd());


    }
    @Override
    public int getItemCount() {
        if(mlistuserchoose!=null)
        {
            return mlistuserchoose.size();
        }
        return 0;
    }


    public class CustomerViewholder extends RecyclerView.ViewHolder {
        TextView name, time;
        ImageButton btnsearch;
        public CustomerViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvname);
            time=itemView.findViewById(R.id.time);
        }
    }
}
