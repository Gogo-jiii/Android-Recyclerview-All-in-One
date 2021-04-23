package com.example.recyclerviewallinone;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                             int position) {
    }

    @Override public int getItemCount() {
        return 0;
    }

    @Override public Filter getFilter() {
        return null;
    }
}
