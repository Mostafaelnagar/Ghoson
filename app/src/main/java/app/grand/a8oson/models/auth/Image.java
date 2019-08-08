package app.grand.a8oson.models.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image  {

    @SerializedName("imagable_id")
    private int imagableId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("imagable_type")
    private String imagableType;

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    public void setImagableId(int imagableId) {
        this.imagableId = imagableId;
    }

    public int getImagableId() {
        return imagableId;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setImagableType(String imagableType) {
        this.imagableType = imagableType;
    }

    public String getImagableType() {
        return imagableType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Image{" +
                        "imagable_id = '" + imagableId + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",imagable_type = '" + imagableType + '\'' +
                        ",name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}