package app.grand.a8oson.models.auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserData {
    @Expose
    @SerializedName("image")
    private Image image;
    @Expose

    @SerializedName("gender")
    private int gender;
    @Expose

    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @Expose

    @SerializedName("jwt")
    private String jwt;
    @Expose

    @SerializedName("latitude")
    private String latitude;
    @Expose

    @SerializedName("mobile")
    private String mobile;
    @Expose

    @SerializedName("created_at")
    private String createdAt;
    @Expose

    @SerializedName("facebook_id")
    private String facebookId;
    @Expose

    @SerializedName("updated_at")
    private String updatedAt;
    @Expose

    @SerializedName("role_id")
    private int roleId;
    @Expose

    @SerializedName("rate")
    private int rate;
    @Expose

    @SerializedName("device_token")
    private String deviceToken;
    @Expose

    @SerializedName("name")
    private String name;
    @Expose

    @SerializedName("id")
    private int id;
    @Expose

    @SerializedName("email")
    private String email;
    @Expose

    @SerializedName("longitude")
    private String longitude;
    @Expose

    @SerializedName("status")
    private int status;
    @Expose

    @SerializedName("type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
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
                "UserData{" +
                        "image = '" + image + '\'' +
                        ",gender = '" + gender + '\'' +
                        ",date_of_birth = '" + dateOfBirth + '\'' +
                        ",jwt = '" + jwt + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",mobile = '" + mobile + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",facebook_id = '" + facebookId + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",role_id = '" + roleId + '\'' +
                        ",rate = '" + rate + '\'' +
                        ",device_token = '" + deviceToken + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}