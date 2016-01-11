package uk.ac.tae.myapp.asosonlinebasic.model.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;


import uk.ac.tae.myapp.asosonlinebasic.R;
import uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters.SliderFStatePagerAdapter;
import uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters.SliderPagerAdapter;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductDetail.Product;

/**
 * Created by Karma on 22/12/15.
 */
public class ASOSProductDetailFragment extends Fragment {
    private ViewPager vpImageSlider;
    //    private SliderPagerAdapter adapter;
    private SliderFStatePagerAdapter adapter;
    private CirclePageIndicator circlePageIndicator;
    private TextView tvProductInfo;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_detail_fragment_layout, container, false);
        vpImageSlider = (ViewPager) v.findViewById(R.id.vpASOSProductDetail);
        circlePageIndicator = (CirclePageIndicator) v.findViewById(R.id.cpiASOSProductImageSlide);
        tvProductInfo = (TextView) v.findViewById(R.id.tvASOSProductInfo);
        tvProductInfo.setText(product.getTitle() + "\n Description :" + product.getDescription() + "\n Price :" + product.getCurrentPrice());
        Log.i("ProductDetailFragment", "Product Des :" + product.getDescription());
        adapter = new SliderFStatePagerAdapter(getFragmentManager(), product.getProductImageUrls());
        vpImageSlider.setAdapter(adapter);
        circlePageIndicator.setViewPager(vpImageSlider);
        return v;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
