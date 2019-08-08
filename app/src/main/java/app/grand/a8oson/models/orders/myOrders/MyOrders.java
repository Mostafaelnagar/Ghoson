package app.grand.a8oson.models.orders.myOrders;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyOrders implements Serializable {

    @SerializedName("representative_id")
    private Object representativeId;

    @SerializedName("market_image")
    private String marketImage;

    @SerializedName("code")
    private String code;

    @SerializedName("address")
    private String address;

    @SerializedName("total_cost")
    private Object totalCost;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("description")
    private String description;

    @SerializedName("delivery_time")
    private String deliveryTime;

    @SerializedName("market_name")
    private String marketName;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("id")
    private String id;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("status")
    private int status;


    public void setRepresentativeId(Object representativeId) {
        this.representativeId = representativeId;
    }

    public Object getRepresentativeId() {
        return representativeId;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public String getMarketImage() {
        return marketImage;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTotalCost(Object totalCost) {
        this.totalCost = totalCost;
    }

    public Object getTotalCost() {
        return totalCost;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "MyOrders{" +
                        "representative_id = '" + representativeId + '\'' +
                        ",market_image = '" + marketImage + '\'' +
                        ",code = '" + code + '\'' +
                        ",total_cost = '" + totalCost + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",description = '" + description + '\'' +
                        ",delivery_time = '" + deliveryTime + '\'' +
                        ",market_name = '" + marketName + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",id = '" + id + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}