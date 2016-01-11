package uk.ac.tae.myapp.asosonlinebasic.model.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.ac.tae.myapp.asosonlinebasic.R;

/**
 * Created by Karma on 23/12/15.
 */
public class MyTestingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listing_item_row_layout, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
