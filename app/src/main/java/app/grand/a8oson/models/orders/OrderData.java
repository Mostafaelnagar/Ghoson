package app.grand.a8oson.models.orders;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OrderData implements Serializable {

    @SerializedName("images")
    private List<ImagesItem> images;

    @SerializedName("code")
    private String code;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("description")
    private String description;

    @SerializedName("delivery_time")
    private String deliveryTime;

    @SerializedName("id")
    private int id;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("status")
    private int status;

    public void setImages(List<ImagesItem> images) {
        this.images = images;
    }

    public List<ImagesItem> getImages() {
        return images;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
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
                "OrderData{" +
                        "images = '" + images + '\'' +
                        ",code = '" + code + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",description = '" + description + '\'' +
                        ",delivery_time = '" + deliveryTime + '\'' +
                        ",id = '" + id + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}