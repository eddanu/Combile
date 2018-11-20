package com.android.herry.combile;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private int id;
    private String title;
    private String detail;
    private int pic;

    protected Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        detail = in.readString();
        pic= in.readInt();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public Event() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public int getPic() { return pic; }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(detail);
        dest.writeInt(pic);
    }
}
