
package com.example.quanlysukiendat.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.R;

import java.util.List;

public class ListTenDiaDiemAdapter extends RecyclerView.Adapter<ListTenDiaDiemAdapter.CustomerViewholder>{
    private List<diadiemModel> mlistuserchoose;
    private List<diadiemModel> mlistuserchoose1;
    Context context;
    Activity mactivity;
    public ListTenDiaDiemAdapter(List<diadiemModel> mlistuserchoose) {
        this.mlistuserchoose = mlistuserchoose;
        this.mlistuserchoose1=mlistuserchoose;
    }

    public ListTenDiaDiemAdapter(Context context, List<diadiemModel> mListUser) {
        this.context = context;
        this.mlistuserchoose = mListUser;
        this.mlistuserchoose1=mListUser;
        this.mactivity = mactivity;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdiadiem1,parent,false);
        return new CustomerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, @SuppressLint("RecyclerView") int position) {
        diadiemModel user=mlistuserchoose.get(position);
        Log.d("ngu","size là:"+mlistuserchoose.size());
        if(user==null)
        {
            return;
        }

        holder.name.setText(user.getTendiadiem());


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
        TextView name;
        public CustomerViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_tendiadiem);
        }
    }
}
