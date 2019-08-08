package app.grand.a8oson.models.orders;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.volleyutils.MyApplication;

public class OrderRequest {
    @Expose
    @SerializedName("place_id")
    private String place_id;
    @Expose

    @SerializedName("description")
    private String description;
    @Expose

    @SerializedName("delivery_time")
    private String delivery_time;
    @Expose

    @SerializedName("longitude")
    private double longitude;
    @Expose

    @SerializedName("latitude")
    private double latitude;
    @SerializedName("address")
    private String address;
    @SerializedName("market_name")
    private String marketName;
    @SerializedName("market_image")
    private String marketImage;
    @SerializedName("market_latitude")
    private String marketLatitude;
    @SerializedName("market_longitude")
    private String marketLongitude;
    @SerializedName("market_address")
    private String marketAddress;

    public String getMarketLatitude() {
        return marketLatitude;
    }

    public void setMarketLatitude(String marketLatitude) {
        this.marketLatitude = marketLatitude;
    }

    public String getMarketLongitude() {
        return marketLongitude;
    }

    public void setMarketLongitude(String marketLongitude) {
        this.marketLongitude = marketLongitude;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketImage() {
        return marketImage;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean orderVaild() {
        Log.i("orderVaild", "orderVaild: "
                + delivery_time + longitude + latitude + place_id
        );
        return (delivery_time != null && longitude != 0.0 && latitude != 0.0 && place_id != null);
    }
}
