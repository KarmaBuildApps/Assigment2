package uk.ac.tae.myapp.asosonlinebasic.model;

import java.util.ArrayList;
import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class ASOSMen {

    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Listing")
    @Expose
    private List<uk.ac.tae.myapp.asosonlinebasic.model.Listing> Listing = new ArrayList<uk.ac.tae.myapp.asosonlinebasic.model.Listing>();
    @SerializedName("SortType")
    @Expose
    private String SortType;

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The Listing
     */
    public List<uk.ac.tae.myapp.asosonlinebasic.model.Listing> getListing() {
        return Listing;
    }

    /**
     *
     * @param Listing
     * The Listing
     */
    public void setListing(List<uk.ac.tae.myapp.asosonlinebasic.model.Listing> Listing) {
        this.Listing = Listing;
    }

    /**
     *
     * @return
     * The SortType
     */
    public String getSortType() {
        return SortType;
    }

    /**
     *
     * @param SortType
     * The SortType
     */
    public void setSortType(String SortType) {
        this.SortType = SortType;
    }

}