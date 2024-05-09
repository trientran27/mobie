package com.example.quanlysukiendat.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.quanlysukiendat.R;

import java.util.ArrayList;
import java.util.List;

public class GridDichvuAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mHours;
    private List<Integer> mSelectedPositions = new ArrayList<>();

    public GridDichvuAdapter(Context context, String[] hours) {
        mContext = context;
        mHours = hours;
    }

    @Override
    public int getCount() {
        return mHours.length;
    }

    @Override
    public Object getItem(int position) {
        return mHours[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        int position1 = position+1;
        if (convertView == null) {
            // If convertView is null, inflate a new button
            button = new Button(mContext);
            button.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setTextSize(10);
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.bg_gray_custom);
            button.setBackground(drawable);
            button.setPadding(16, 16, 16, 16);
        } else {
            // If convertView is not null, reuse it
            button = (Button) convertView;
        }

        // Set text for the button
        button.setText(mHours[position]);

        // Set background color based on selected position
        if (mSelectedPositions.contains(position)) {
            button.setBackgroundColor(Color.RED);
        } else {
            button.setBackgroundColor(Color.LTGRAY);
        }

        // Set OnClickListener to handle button click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the selected position and refresh the adapter
                if (mSelectedPositions.contains(position)) {
                    mSelectedPositions.remove(Integer.valueOf(position));
                } else {
                    mSelectedPositions.add(position);
                }
                notifyDataSetChanged();
            }
        });

        return button;
    }

    // Method to get the selected positions as a comma-separated string
    public String getSelectedPositionsString() {
        StringBuilder selectedPositionsString = new StringBuilder();
        for (int i = 0; i < mSelectedPositions.size(); i++) {
            selectedPositionsString.append(mSelectedPositions.get(i));
            if (i < mSelectedPositions.size() - 1) {
                selectedPositionsString.append(",");
            }
        }
        return selectedPositionsString.toString();
    }
}
