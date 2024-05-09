
package com.example.quanlysukiendat.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.ChitietdiadiemActivity;
import com.example.quanlysukiendat.MapsActivity;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;
import com.example.quanlysukiendat.R;
import com.example.quanlysukiendat.utils.utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListDiaDiemAdapter extends RecyclerView.Adapter<ListDiaDiemAdapter.CustomerViewholder>{
    private List<diadiemModel> mlistuserchoose;
    private List<diadiemModel> mlistuserchoose1;
    Context context;
    Activity mactivity;
    public ListDiaDiemAdapter(List<diadiemModel> mlistuserchoose) {
        this.mlistuserchoose = mlistuserchoose;
        this.mlistuserchoose1=mlistuserchoose;
    }

    public ListDiaDiemAdapter(Context context, List<diadiemModel> mListUser) {
        this.context = context;
        this.mlistuserchoose = mListUser;
        this.mlistuserchoose1=mListUser;
        this.mactivity = mactivity;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diadiem,parent,false);
        return new CustomerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, @SuppressLint("RecyclerView") int position) {
        diadiemModel user=mlistuserchoose.get(position);
        if(user==null)
        {
            return;
        }

        holder.name.setText(user.getTendiadiem());
        holder.address.setText(user.getDiachi());
        holder.giatien.setText(utils.convertToCurrency(user.getGiadiadiem()));
        holder.tieude.setText(user.getDecrision());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChitietdiadiemActivity.class);
            intent.putExtra("diadiem", user);
            context.startActivity(intent);
        });
        if (user.getImageURLs() != null && !user.getImageURLs().isEmpty()) {
            Picasso.get().load(user.getImageURLs().get(0)).into(holder.sphoto2);
        }else {
            // Hiển thị ImageView mặc định nếu URL hình ảnh là null hoặc rỗng
            holder.sphoto2.setImageResource(R.drawable.trungtam);
        }

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
        TextView name,address, giatien, tieude;
        ImageView sphoto2;
        ImageButton btnsearch;
        public CustomerViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            address = itemView.findViewById(R.id.tv_address);
            giatien=itemView.findViewById(R.id.tv_giatien);
            tieude=itemView.findViewById(R.id.tv_tieude);
            sphoto2 = itemView.findViewById(R.id.sphoto2);
        }
    }

    public void setFilter(List<diadiemModel> filteredList) {
        this.mlistuserchoose = filteredList;
        notifyDataSetChanged();
    }

}
