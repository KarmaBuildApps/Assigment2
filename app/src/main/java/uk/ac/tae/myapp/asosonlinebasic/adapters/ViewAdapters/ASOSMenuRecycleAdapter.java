package uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.ac.tae.myapp.asosonlinebasic.R;
import uk.ac.tae.myapp.asosonlinebasic.api.AdapterItemClickedListener;
import uk.ac.tae.myapp.asosonlinebasic.api.ItemClickedListener;
import uk.ac.tae.myapp.asosonlinebasic.model.Listing;

/**
 * Created by Karma on 21/12/15.
 */
public class ASOSMenuRecycleAdapter extends RecyclerView.Adapter<ASOSMenuRecycleAdapter.ViewHolder> {
    private Context context;
    private int row_layout;
    private List<Listing> navMenuItems;

    public ASOSMenuRecycleAdapter(Context context, int row_layout, List<Listing> navMenuItems) {
        this.context = context;
        this.row_layout = row_layout;
        this.navMenuItems = navMenuItems;
        Log.i("Adapter Start", "List Value:" + navMenuItems);
    }

    @Override
    public ASOSMenuRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ASOSMenuRecycleAdapter.ViewHolder holder, int position) {
        final Listing menuItem = navMenuItems.get(position);
        holder.tvNavItemTitle.setText(menuItem.getName());
        holder.tvNavItemCounter.setText(menuItem.getProductCount().toString());
        holder.setItemClickedListener(new ItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                ((AdapterItemClickedListener) context).startListing(menuItem.getCategoryId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return navMenuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNavItemTitle, tvNavItemCounter;
        ItemClickedListener itemClickedListener;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNavItemTitle = (TextView) itemView.findViewById(R.id.tvASOSNavItemTitle);
            tvNavItemCounter = (TextView) itemView.findViewById(R.id.tvASOSNavItemCounter);
            itemView.setOnClickListener(this);
        }

        public void setItemClickedListener(ItemClickedListener itemClickedListener) {
            this.itemClickedListener = itemClickedListener;
        }

        @Override
        public void onClick(View v) {
            itemClickedListener.onItemClicked(v, getLayoutPosition());
        }
    }
}
