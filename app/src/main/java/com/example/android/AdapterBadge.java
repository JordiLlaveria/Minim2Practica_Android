package com.example.android;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class AdapterBadge extends RecyclerView.Adapter<AdapterBadge.ViewHolder> {
    private List<Badge> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nameBadge;
        public ImageView imgBadge;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            nameBadge = (TextView) v.findViewById(R.id.nameBadge);
            imgBadge = (ImageView) v.findViewById(R.id.imgBadge);
        }
    }

    public void setData(List<Badge> myDataset) {
        values = myDataset;
        notifyDataSetChanged();
    }

    public void add(int position, Badge item) {
        values.add(position, item);
        notifyItemInserted(position); // Used for informing that the list needs to be changed
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterBadge(List<Badge> myDataset) {
        values = myDataset;
    }

    public AdapterBadge() {
        values = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterBadge.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_badge, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterBadge.ViewHolder vh = new AdapterBadge.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Badge badge = values.get(position);
        String name = badge.getName();
        holder.nameBadge.setText(name);
        Picasso.get()
                .load(badge.getUrl())
                .resize(300,300)
                .centerCrop()
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.imgBadge);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
