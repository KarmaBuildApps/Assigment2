package uk.ac.tae.myapp.asosonlinebasic.extra;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import uk.ac.tae.myapp.asosonlinebasic.model.Listing;
import uk.ac.tae.myapp.asosonlinebasic.model.ProductCategory.ListingForCat;

/**
 * Created by Karma on 22/12/15.
 */
public class ListingItems implements Parcelable {
    List<ListingForCat> listingItems;
    protected ListingItems(Parcel in) {
    }

    public static final Creator<ListingItems> CREATOR = new Creator<ListingItems>() {
        @Override
        public ListingItems createFromParcel(Parcel in) {
            return new ListingItems(in);
        }

        @Override
        public ListingItems[] newArray(int size) {
            return new ListingItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
