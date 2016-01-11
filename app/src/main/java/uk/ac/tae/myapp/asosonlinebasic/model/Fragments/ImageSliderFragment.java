package uk.ac.tae.myapp.asosonlinebasic.model.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import uk.ac.tae.myapp.asosonlinebasic.R;

/**
 * Created by Karma on 23/12/15.
 */
public class ImageSliderFragment extends Fragment {
    private String imageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_slider_image_layout, container, false);
        ImageView ivSliderImage = (ImageView) view.findViewById(R.id.ivASOSProductDetailSlider);
        imageUri = getArguments().getString("imageUri");
        Picasso.with(getContext()).setIndicatorsEnabled(true);
        Picasso.with(getContext()).load(imageUri).into(ivSliderImage);
        return view;
    }

}
