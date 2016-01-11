package uk.ac.tae.myapp.asosonlinebasic;

import android.app.ProgressDialog;
import android.content.Context;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.ac.tae.myapp.asosonlinebasic.adapters.ViewAdapters.ASOSMenuRecycleAdapter;
import uk.ac.tae.myapp.asosonlinebasic.api.AdapterItemClickedListener;
import uk.ac.tae.myapp.asosonlinebasic.api.InterfaceASOS;
import uk.ac.tae.myapp.asosonlinebasic.constants.ASOSConstants;
import uk.ac.tae.myapp.asosonlinebasic.constants.GENDER;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSMen;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSWomen;
import uk.ac.tae.myapp.asosonlinebasic.model.Fragments.ASOSListingFragment;
import uk.ac.tae.myapp.asosonlinebasic.model.Fragments.ASOSProductDetailFragment;
import uk.ac.tae.myapp.asosonlinebasic.model.Listing;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ListingForCat;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ProductsByCategory;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductDetail.Product;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, AdapterItemClickedListener {
    private List<Listing> asosMenMenu, asosWomenMenu;
    private Toolbar toolbar;
    private DrawerLayout dlayout;
    private FrameLayout frameLayout;
    private ViewPager vpMenu;
    private RecyclerView rcMenu;
    private ASOSMenuRecycleAdapter menMenuAdapter, womenMenuAdapter;
    private ActionBarDrawerToggle toggle;
    private ProgressDialog progressDialog;
    private RestAdapter restAdapter;
    private InterfaceASOS interfaceASOS;
    private String defaultStarterCatId;
    private Context context;
    private Set<String> setOfString = new HashSet<String>(1);
    private MixpanelAPI mixpanel;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asos_main);
        context = this;
//        fm = getSupportFragmentManager();
//        editor = fm.beginTransaction();

        startRestAdapter();
        initiateMixpanel();
        instantiateViews();
//        startListingFragment(defaultStarterCatId);
        startListByCatId(defaultStarterCatId);
    }

    private void initiateMixpanel() {
        mixpanel = MixpanelAPI.getInstance(this, ASOSConstants.MIXPANEL_TOKEN);
        try {
            JSONObject props = new JSONObject();
            props.put("Gender", "Female");
            props.put("Logged in", false);
            mixpanel.track("MainActivity - onCreate called", props);
        } catch (JSONException e) {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
    }

    private void startListByCatId(String catId) {
        requestProductsByCatId(catId);
    }

    private void instantiateViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbarASOS);
        toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(this);
        dlayout = (DrawerLayout) findViewById(R.id.dlASOS);
        frameLayout = (FrameLayout) findViewById(R.id.flASOSMainDisplay);
        rcMenu = (RecyclerView) findViewById(R.id.rcASOSNavMenu);
        rcMenu.setLayoutManager(new LinearLayoutManager(this));
        setMenuAdapter(GENDER.MEN);
        setToggle();
        dlayout.setDrawerListener(toggle);
        putSet();
        printSetOfString();
    }

    public void putSet() {
        setOfString.add("1");
        setOfString.add("2");
        setOfString.add("3");
    }

    public void printSetOfString() {
        for (String s : setOfString) {
            Log.i("SET OF String", "Value :" + s);
        }

    }


    private void startRestAdapter() {
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading...");
//        progressDialog.show();
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(ASOSConstants.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        interfaceASOS = restAdapter.create(InterfaceASOS.class);
    }

    private void setToggle() {
        toggle = new ActionBarDrawerToggle(this, dlayout, toolbar, R.string.drawer_open, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startNavMenu(GENDER gender) {
        if (gender == GENDER.MEN)
            rcMenu.setAdapter(menMenuAdapter);
        else if (gender == GENDER.WOMEN)
            rcMenu.setAdapter(womenMenuAdapter);

    }

//    public void startListingFragment(String catId) {
//        ASOSListingFragment listingFragment = new ASOSListingFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("catId", catId);
//        listingFragment.setArguments(bundle);
//
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction editor = fm.beginTransaction();
//        if (fm.findFragmentById(R.id.flASOSMainDisplay) != null)
//            editor.replace(R.id.flASOSMainDisplay, listingFragment);
//        else
//            editor.add(R.id.flASOSMainDisplay, listingFragment);
//        editor.commit();
//        MyTestingFragment fragment = new MyTestingFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction editor = fm.beginTransaction();
//        if (fm.findFragmentById(R.id.flASOSMainDisplay) != null)
//            editor.replace(R.id.flASOSMainDisplay, fragment);
//        else
//            editor.add(R.id.flASOSMainDisplay, fragment);
//        editor.commit();

    public void startListingFragment(List<ListingForCat> listingItems) {
        ASOSListingFragment listingFragment = new ASOSListingFragment();
        listingFragment.setListingItems(listingItems);
        Bundle bundle = new Bundle();
        bundle.putString("catId", "Ok");
        listingFragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction editor = fm.beginTransaction();
        if (fm.findFragmentById(R.id.flASOSMainDisplay) != null)
            editor.replace(R.id.flASOSMainDisplay, listingFragment);
        else
            editor.add(R.id.flASOSMainDisplay, listingFragment);
        editor.commit();
    }

    public void startProductDetailFragment(Product pr) {
        ASOSProductDetailFragment productFragment = new ASOSProductDetailFragment();
        productFragment.setProduct(pr);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction editor = fm.beginTransaction();
        if (fm.findFragmentById(R.id.flASOSMainDisplay) != null)
            editor.replace(R.id.flASOSMainDisplay, productFragment);
        else
            editor.add(R.id.flASOSMainDisplay, productFragment);
        editor.commit();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    /************************The methods below are for dealing retrival of data from api*********************/

    /**
     * This method implements InterfaceASOS method getMenProducts().
     * the second parameter of the method getMenProducts() is used for storing
     * ASOSMen from web.
     */
    public void setMenuAdapter(GENDER gender) {
        if (gender == GENDER.MEN) {
            interfaceASOS.getMenProducts(new Callback<ASOSMen>() {
                @Override
                public void success(ASOSMen men, Response response) {
                    dismissProgressDialog();
                    asosMenMenu = men.getListing();
                    menMenuAdapter = new ASOSMenuRecycleAdapter(context,
                            R.layout.asos_menu_row_layout, asosMenMenu);
                    rcMenu.setAdapter(menMenuAdapter);
                    for (int i = 0; i < asosMenMenu.size(); i++) {
                        Log.i("MENMODLE", "Values :" + asosMenMenu.get(i).getName());
                        Log.i("MENMODLE", "Values Counter: " + asosMenMenu.get(i).getProductCount());
                    }
                    if (defaultStarterCatId == null)
                        defaultStarterCatId = asosMenMenu.get(0).getCategoryId();
                }

                @Override
                public void failure(RetrofitError error) {
                    dismissProgressDialog();
                    logFailureMessage(error, "ASOSMen");
                }
            });
        } else if (gender == GENDER.WOMEN) {
            interfaceASOS.getWomenProducts(new Callback<ASOSWomen>() {
                @Override
                public void success(ASOSWomen women, Response response) {
                    dismissProgressDialog();
                    menMenuAdapter = new ASOSMenuRecycleAdapter(context,
                            R.layout.asos_menu_row_layout, women.getListing());
                    rcMenu.setAdapter(menMenuAdapter);
                    Log.i("Women", "Values: " + women.getDescription());
                }

                @Override
                public void failure(RetrofitError error) {
                    dismissProgressDialog();
                    logFailureMessage(error, "ASOSWomen");
                }
            });
        }
    }


    /**
     * The method uses Product Category id and gets products in specified Id.
     *
     * @param catId
     */
    public void requestProductsByCatId(String catId) {
        interfaceASOS.getProductsWithCatID(catId, new Callback<ProductsByCategory>() {
            @Override
            public void success(ProductsByCategory pByCategory, Response response) {
                dismissProgressDialog();
                startListingFragment(pByCategory.getListings());
                Log.i("ProductsByCatId", "Products" + pByCategory.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "Products By Category");
            }
        });
    }

    /**
     * This method is like above uses product id and gets details of the prodcut.
     *
     * @param productId
     */
    public void requestProductDetail(String productId) {
        interfaceASOS.getProduct(productId, new Callback<Product>() {
            @Override
            public void success(Product p, Response response) {
                dismissProgressDialog();
                startProductDetailFragment(p);
                Log.i("MainActivity", "Product Detail :" + p.getProductImageUrls());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "Product Detail");
            }
        });
    }

    /**
     * This method for dismissing ProgressDialog
     */
    public void dismissProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * This method is for logging errors returned by Retrofit restadapter
     *
     * @param error
     * @param ASOSProduct
     */
    private void logFailureMessage(RetrofitError error, String ASOSProduct) {
        if (error.getKind().equals(RetrofitError.Kind.NETWORK))
            Log.d(ASOSProduct, "Network Error: " + error.getMessage());
        else if (error.getKind().equals(RetrofitError.Kind.CONVERSION))
            Log.d(ASOSProduct, "Coversion Error: " + error.getMessage());
        else if (error.getKind().equals(RetrofitError.Kind.UNEXPECTED))
            Log.d(ASOSProduct, "Unexpected Error: " + error.getMessage());
        else if (error.getKind().equals(RetrofitError.Kind.HTTP))
            Log.d(ASOSProduct, "HTTP Error: " + error.getMessage());
    }


    //    @Override
//    public void startListing(String catId) {
//        startListingFragment(catId);
//    }

    /**
     * The method is used and called by ASOSMenuRecycleAdapter using Interface AdapterItem
     *
     * @param catId
     */
    @Override
    public void startListing(String catId) {
        startListByCatId(catId);
    }

    /**
     * This method is similar to above and called by ASOSListingAdapter and this called without
     * implementation of an interface.
     *
     * @param productId
     */
    public void showtProductDetail(String productId) {
        requestProductDetail(productId);
    }
}
