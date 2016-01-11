package uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uk.ac.tae.myapp.asosonlinebasic.MainActivity;
import uk.ac.tae.myapp.asosonlinebasic.R;
import uk.ac.tae.myapp.asosonlinebasic.api.ItemClickedListener;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ListingForCat;

/**
 * Created by Karma on 22/12/15.
 */
public class ASOSListingAdapter extends RecyclerView.Adapter<ASOSListingAdapter.ViewHolder> {
    private Context context;
    private List<ListingForCat> listingItems;
    private int listingRowLayout;

    public ASOSListingAdapter(Context context, int listingRowLayout, List<ListingForCat> listingItems) {
        this.context = context;
        this.listingRowLayout = listingRowLayout;
        this.listingItems = listingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//completed
        View view = LayoutInflater.from(context).inflate(listingRowLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListingForCat listingItem = listingItems.get(position);
        holder.tvListingItemTitle.setText(listingItem.getTitle());
        holder.tvListingItemsPrice.setText(listingItem.getCurrentPrice());
        Picasso.with(context).setIndicatorsEnabled(true);
        Picasso.with(context).load(listingItem.getProductImageUrl().get(0))
                .into(holder.ivListignItemImage);
        holder.setItemClickedListener(new ItemClickedListener() {
            @Override
            public void onItemClicked(View view, int position) {
                ((MainActivity) context).showtProductDetail(listingItem.getProductId().toString());// FIXME: 23/12/15 
            }
        });

    }

    @Override
    public int getItemCount() {
        return listingItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvListingItemTitle, tvListingItemsPrice;
        ImageView ivListignItemImage;
        ItemClickedListener itemClickedListener;

        public ViewHolder(View itemView) {
            super(itemView);
            tvListingItemTitle = (TextView) itemView.findViewById(R.id.tvASOSListingItemTitle);
            tvListingItemsPrice = (TextView) itemView.findViewById(R.id.tvASOSListingItemPrice);
            ivListignItemImage = (ImageView) itemView.findViewById(R.id.ivASOSListingItemImage);
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
