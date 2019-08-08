package app.grand.a8oson.models.markets;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Markets implements Serializable {

    @SerializedName("distance")
    private String distance;

    @SerializedName("name")
    private String name;

    @SerializedName("photo")
    private String photo;

    @SerializedName("category")
    private String category;

    @SerializedName("place_id")
    private String placeId;

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    @Override
    public String toString() {
        return
                "Markets{" +
                        "distance = '" + distance + '\'' +
                        ",name = '" + name + '\'' +
                        ",photo = '" + photo + '\'' +
                        ",category = '" + category + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        "}";
    }
}