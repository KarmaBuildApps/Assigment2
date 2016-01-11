package uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class ProductsByCategory {

    @SerializedName("AlsoSearched")
    @Expose
    private List<Object> AlsoSearched = new ArrayList<Object>();
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Facets")
    @Expose
    private List<Facet> Facets = new ArrayList<Facet>();
    @SerializedName("ItemCount")
    @Expose
    private Integer ItemCount;
    @SerializedName("Listings")
    @Expose
    private List<ListingForCat> Listings = new ArrayList<ListingForCat>();
    @SerializedName("RedirectUrl")
    @Expose
    private String RedirectUrl;
    @SerializedName("SortType")
    @Expose
    private String SortType;

    /**
     * @return The AlsoSearched
     */
    public List<Object> getAlsoSearched() {
        return AlsoSearched;
    }

    /**
     * @param AlsoSearched The AlsoSearched
     */
    public void setAlsoSearched(List<Object> AlsoSearched) {
        this.AlsoSearched = AlsoSearched;
    }

    /**
     * @return The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return The Facets
     */
    public List<Facet> getFacets() {
        return Facets;
    }

    /**
     * @param Facets The Facets
     */
    public void setFacets(List<Facet> Facets) {
        this.Facets = Facets;
    }

    /**
     * @return The ItemCount
     */
    public Integer getItemCount() {
        return ItemCount;
    }

    /**
     * @param ItemCount The ItemCount
     */
    public void setItemCount(Integer ItemCount) {
        this.ItemCount = ItemCount;
    }

    /**
     * @return The Listings
     */
    public List<ListingForCat> getListings() {
        return Listings;
    }

    /**
     * @param Listings The Listings
     */
    public void setListings(List<ListingForCat> Listings) {
        this.Listings = Listings;
    }

    /**
     * @return The RedirectUrl
     */
    public String getRedirectUrl() {
        return RedirectUrl;
    }

    /**
     * @param RedirectUrl The RedirectUrl
     */
    public void setRedirectUrl(String RedirectUrl) {
        this.RedirectUrl = RedirectUrl;
    }

    /**
     * @return The SortType
     */
    public String getSortType() {
        return SortType;
    }

    /**
     * @param SortType The SortType
     */
    public void setSortType(String SortType) {
        this.SortType = SortType;
    }

}
