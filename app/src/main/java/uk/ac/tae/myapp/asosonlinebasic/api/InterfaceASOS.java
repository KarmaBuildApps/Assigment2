package uk.ac.tae.myapp.asosonlinebasic.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSMen;
import uk.ac.tae.myapp.asosonlinebasic.model.ASOSWomen;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ProductsByCategory;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductDetail.Product;

/**
 * Created by Karma on 18/12/15.
 */
public interface InterfaceASOS {
    //Gets women categories. This categories are used as navigation drawer menu items
    @GET("/u/1559445/ASOS/SampleApi/cats_women.json")
    public void getWomenProducts(Callback<ASOSWomen> asosWomen);

    //Gets men categories. This categories are used as navigation drawer menu items
    @GET("/u/1559445/ASOS/SampleApi/cats_men.json")
    public void getMenProducts(Callback<ASOSMen> asosMen);

    //For Getting products by specified category id
    @GET("/u/1559445/ASOS/SampleApi/anycat_products.json")
    public void getProductsWithCatID(@Query("catid") String CategoryID, Callback<ProductsByCategory> productsByCategory);

    //For getting product item details by product Id.
    @GET("/u/1559445/ASOS/SampleApi/anyproduct_details.json")
    public void getProduct(@Query("catid") String productId, Callback<Product> product);

}
