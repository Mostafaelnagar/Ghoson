package app.grand.a8oson.models.orders.waitingOrders;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import app.grand.a8oson.models.orders.ImagesItem;

public class MyOrdersData implements Serializable {

    @SerializedName("representative_id")
    private Object representativeId;

    @SerializedName("images")
    private List<Object> images;

    @SerializedName("code")
    private String code;

    @SerializedName("total_cost")
    private Object totalCost;

    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_image")
    private String userImage;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("description")
    private String description;

    @SerializedName("delivery_time")
    private String deliveryTime;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("status")
    private int status;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setRepresentativeId(Object representativeId) {
        this.representativeId = representativeId;
    }

    public Object getRepresentativeId() {
        return representativeId;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public List<Object> getImages() {
        return images;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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
                "MyOrdersData{" +
                        "representative_id = '" + representativeId + '\'' +
                        ",images = '" + images + '\'' +
                        ",code = '" + code + '\'' +
                        ",total_cost = '" + totalCost + '\'' +
                        ",user_name = '" + userName + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",description = '" + description + '\'' +
                        ",delivery_time = '" + deliveryTime + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",id = '" + id + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}