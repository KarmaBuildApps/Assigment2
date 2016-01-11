package uk.ac.tae.myapp.asosonlinebasic.model.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.ac.tae.myapp.asosonlinebasic.R;
import uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters.ASOSListingAdapter;
import uk.ac.tae.myapp.asosonlinebasic.api.InterfaceASOS;
import uk.ac.tae.myapp.asosonlinebasic.constants.ASOSConstants;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ListingForCat;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ProductsByCategory;

/**
 * Created by Karma on 22/12/15.
 */
public class ASOSListingFragment extends Fragment {
    private RecyclerView rcListing;
    private ASOSListingAdapter adapter;
    private List<ListingForCat> listingItems;
    private String catId;
//    private InterfaceASOS interfaceASOS;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_fragment, container, false);
        rcListing = (RecyclerView) view.findViewById(R.id.rcASOSListing);
        rcListing.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        rcListing.setLayoutManager(new LinearLayoutManager(getContext()));
        catId = getArguments().getString("catId");
        adapter = new ASOSListingAdapter(getContext(), R.layout.listing_item_row_layout, listingItems);
        rcListing.setAdapter(adapter);
//
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint(ASOSConstants.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
//        interfaceASOS = restAdapter.create(InterfaceASOS.class);
//        interfaceASOS.getProductsWithCatID(catId, new Callback<ProductsByCategory>() {
//            @Override
//            public void success(ProductsByCategory productsByCategory, Response response) {
//                adapter = new ASOSListingAdapter(getActivity(), R.layout.listing_item_row_layout, productsByCategory.getListings());
//                rcListing.setAdapter(adapter);
//                Log.d("ASOSListingFragment", "Values :" +
//                        productsByCategory.getListings().get(0).getProductImageUrl());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });

        return view;
    }

    public void setListingItems(List<ListingForCat> listingItems) {
        this.listingItems = listingItems;
    }
}
