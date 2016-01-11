package uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import uk.ac.tae.myapp.asosonlinebasic.model.Fragments.ImageSliderFragment;

/**
 * Created by Karma on 23/12/15.
 */
public class SliderPagerAdapter extends FragmentPagerAdapter {
    List<String> imageUrls;

    public SliderPagerAdapter(FragmentManager fm, List<String> uris) {
        super(fm);
        this.imageUrls = uris;
    }

    @Override
    public Fragment getItem(int position) {
        ImageSliderFragment fragment = new ImageSliderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("imageUri", imageUrls.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


}
