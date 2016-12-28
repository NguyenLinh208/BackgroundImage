package jp.linhnk.backgroundimage.api.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by usr0200475 on 2016/12/28.
 */

public class Hit implements Parcelable {
    public int previewHeight;
    public int likes;
    public int favorites;
    public String tags;
    public int views;
    public int previewWidth;
    public int comments;
    public int downloads;
    public String pageUrl;
    public String previewURL;
    public String userId;
    public String user;
    public String type;
    public int id;
    public String userImageURL;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.previewHeight);
        dest.writeInt(this.likes);
        dest.writeInt(this.favorites);
        dest.writeString(this.tags);
        dest.writeInt(this.views);
        dest.writeInt(this.previewWidth);
        dest.writeInt(this.comments);
        dest.writeInt(this.downloads);
        dest.writeString(this.pageUrl);
        dest.writeString(this.previewURL);
        dest.writeString(this.userId);
        dest.writeString(this.user);
        dest.writeString(this.type);
        dest.writeInt(this.id);
        dest.writeString(this.userImageURL);
    }

    public Hit() {
    }

    protected Hit(Parcel in) {
        this.previewHeight = in.readInt();
        this.likes = in.readInt();
        this.favorites = in.readInt();
        this.tags = in.readString();
        this.views = in.readInt();
        this.previewWidth = in.readInt();
        this.comments = in.readInt();
        this.downloads = in.readInt();
        this.pageUrl = in.readString();
        this.previewURL = in.readString();
        this.userId = in.readString();
        this.user = in.readString();
        this.type = in.readString();
        this.id = in.readInt();
        this.userImageURL = in.readString();
    }

    public static final Creator<Hit> CREATOR = new Creator<Hit>() {
        @Override
        public Hit createFromParcel(Parcel source) {
            return new Hit(source);
        }

        @Override
        public Hit[] newArray(int size) {
            return new Hit[size];
        }
    };
}
