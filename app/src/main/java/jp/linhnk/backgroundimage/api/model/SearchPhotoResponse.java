package jp.linhnk.backgroundimage.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import jp.linhnk.backgroundimage.api.model.Hit;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class SearchPhotoResponse implements Parcelable {

    public int totalHits;
    public List<Hit> hits;
    public int total;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.totalHits);
        dest.writeTypedList(this.hits);
        dest.writeInt(this.total);
    }

    public SearchPhotoResponse() {
    }

    protected SearchPhotoResponse(Parcel in) {
        this.totalHits = in.readInt();
        this.hits = in.createTypedArrayList(Hit.CREATOR);
        this.total = in.readInt();
    }

    public static final Creator<SearchPhotoResponse> CREATOR = new Creator<SearchPhotoResponse>() {
        @Override
        public SearchPhotoResponse createFromParcel(Parcel source) {
            return new SearchPhotoResponse(source);
        }

        @Override
        public SearchPhotoResponse[] newArray(int size) {
            return new SearchPhotoResponse[size];
        }
    };
}
