
package com.example.quanlysukiendat.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Model.khachmoiModel;
import com.example.quanlysukiendat.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ListKhachMoiAdapter extends RecyclerView.Adapter<ListKhachMoiAdapter.CustomerViewholder> implements Filterable {
    private List<khachmoiModel> mlistuserchoose;
    private List<khachmoiModel> mlistuserchoose1;
    Context context;
    Activity mactivity;
    public ListKhachMoiAdapter(List<khachmoiModel> mlistuserchoose) {
        this.mlistuserchoose = mlistuserchoose;
        this.mlistuserchoose1=mlistuserchoose;
    }

    public ListKhachMoiAdapter(Context context, List<khachmoiModel> mListUser, Activity mactivity, String uid) {
        this.context = context;
        this.mlistuserchoose = mListUser;
        this.mlistuserchoose1=mListUser;
        this.mactivity = mactivity;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khach,parent,false);
        return new CustomerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, @SuppressLint("RecyclerView") int position) {
        khachmoiModel user=mlistuserchoose.get(position);
        if(user==null)
        {
            return;
        }

        holder.name.setText(user.getNameKM());
        holder.mobile.setText(user.getPhoneKM());


    }
    @Override
    public int getItemCount() {
        if(mlistuserchoose!=null)
        {
            return mlistuserchoose.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()) {
                    mlistuserchoose= mlistuserchoose1;
                } else {
                    List<khachmoiModel> list = new ArrayList<>();
                    for (khachmoiModel user : mlistuserchoose1) {
                        if (user.getNameKM().toLowerCase(Locale.ROOT).contains((strSearch.toLowerCase()))
                        || user.getPhoneKM().toLowerCase(Locale.ROOT).contains((strSearch.toLowerCase()))
                        || user.getEmailKM().toLowerCase(Locale.ROOT).contains((strSearch.toLowerCase()))) {
                            list.add(user);
                        }
                    }
                    mlistuserchoose = list;
                }
                FilterResults fiterresult = new FilterResults();
                fiterresult.values = mlistuserchoose;

                return fiterresult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mlistuserchoose = (List<khachmoiModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CustomerViewholder extends RecyclerView.ViewHolder {
        TextView name, email, mobile, address;
        ImageButton btnsearch;
        public CustomerViewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewUsername);
            mobile=itemView.findViewById(R.id.textMobileKM);
        }
    }
}
