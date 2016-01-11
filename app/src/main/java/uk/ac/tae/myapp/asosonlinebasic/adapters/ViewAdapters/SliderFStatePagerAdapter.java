package uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import uk.ac.tae.myapp.asosonlinebasic.model.Fragments.ImageSliderFragment;

/**
 * Created by Karma on 23/12/15.
 */
public class SliderFStatePagerAdapter extends FragmentStatePagerAdapter {
    List<String> urls;

    public SliderFStatePagerAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        ImageSliderFragment imageSliderFragment = new ImageSliderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imageUri", urls.get(position));
        imageSliderFragment.setArguments(bundle);
        return imageSliderFragment;
    }

    @Override
    public int getCount() {
        return urls.size();
    }
}
