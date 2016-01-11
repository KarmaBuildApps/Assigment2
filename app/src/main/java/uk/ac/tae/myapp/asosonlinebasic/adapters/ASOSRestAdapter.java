package uk.ac.tae.myapp.asosonlinebasic.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.ac.tae.myapp.asosonlinebasic.api.InterfaceASOS;
import uk.ac.tae.myapp.asosonlinebasic.constants.ASOSConstants;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSMen;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSWomen;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ListingForCat;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ProductsByCategory;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductDetail.Product;

/**
 * Created by Karma on 18/12/15.
 * <p>
 * This class act as Retrofit RestAdapter. This class is to seperate role of fetching info from
 * website from the Main class.
 */
public class ASOSRestAdapter {
    private ProgressDialog progressDialog;
    private ASOSWomen asosWomen; //retrieved ASOSWomen by RestAdapter
    private ASOSMen asosMen;//retrieved ASOSMen by RestAdaptor
    private ProductsByCategory productsByCategory;
    private Product product;
    private Context context; //Main calls context
    private RestAdapter restAdapter;
    private InterfaceASOS interfaceASOS;

    /**
     * This contructor can be used to link the Main class of the Application.
     *
     * @param context
     */
    public ASOSRestAdapter(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        this.restAdapter = new RestAdapter.Builder()
                .setEndpoint(ASOSConstants.BASE_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        interfaceASOS = restAdapter.create(InterfaceASOS.class);

    }

    /**
     * This method implements InterfaceASOS method getWomenProducts().
     * the second parameter of the method getWomenProducts() is used for storing
     * ASOSWomen from web.
     */
    public ASOSWomen requestWomenProducts() {
        interfaceASOS.getWomenProducts(new Callback<ASOSWomen>() {
            @Override
            public void success(ASOSWomen women, Response response) {
                dismissProgressDialog();
                Log.i("ASOSWomen", "" + asosMen.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "ASOSWomen");
            }
        });

        return asosWomen;
    }


    /**
     * This method implements InterfaceASOS method getMenProducts().
     * the second parameter of the method getMenProducts() is used for storing
     * ASOSMen from web.
     */
    public ASOSMen resquestMenProducts() {
        interfaceASOS.getMenProducts(new Callback<ASOSMen>() {
            @Override
            public void success(ASOSMen men, Response response) {
                dismissProgressDialog();
                asosMen = men;
                Log.i("ASOSMen", asosMen.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "ASOSMen");
            }
        });

        return asosMen;
    }

    public void setAsosWomen(ASOSWomen asosWomen) {
        asosWomen = asosWomen;
        Log.i("RestAdapter", "Women Value :" + asosWomen.getDescription());
    }

    /**
     * The method uses Product Category id and gets products in specified Id.
     *
     * @param catId
     */
    public List<ListingForCat> requestProductsByCatId(String catId) {
        interfaceASOS.getProductsWithCatID(catId, new Callback<ProductsByCategory>() {
            @Override
            public void success(ProductsByCategory pByCategory, Response response) {
                dismissProgressDialog();
                productsByCategory = pByCategory;
                Log.i("Product Category", productsByCategory.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "Products By Category");
            }
        });
        return productsByCategory.getListings();
    }

    /**
     * This method is like above uses product id and gets details of the prodcut.
     *
     * @param productId
     */
    public Product requestProductDetail(final String productId) {
        interfaceASOS.getProduct(productId, new Callback<Product>() {
            @Override
            public void success(Product p, Response response) {
                dismissProgressDialog();
                product = p;
                Log.i("Product Detail", product.getTitle());
            }

            @Override
            public void failure(RetrofitError error) {
                dismissProgressDialog();
                logFailureMessage(error, "Product Detail");
            }
        });
        return product;
    }

    /**
     * This method for dismissing ProgressDialog
     */
    public void dismissProgressDialog() {
        progressDialog.show();
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    /**
     * The method is only way to get access to ASOSWomen of the class
     */
    public ASOSWomen getAsosWomen() {
        return asosWomen;
    }

    /**
     * The method is only way to get access to ASOSMen of the class
     */
    public ASOSMen getAsosMen() {
        return asosMen;
    }

    // Get product Detail by Product Id
    public Product getProduct() {
        return product;
    }

    //Get Products by Product Category Id.
    public ProductsByCategory getProductsByCategory() {
        return productsByCategory;
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
}

