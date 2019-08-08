package app.grand.a8oson.models.orders.orderDetail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import app.grand.a8oson.models.auth.UserData;

public class OrderDetail implements Serializable {

    @SerializedName("representative_id")
    private String representativeId;

    @SerializedName("market_image")
    private String marketImage;

    @SerializedName("market_longitude")
    private String marketLongitude;

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

    @SerializedName("market_latitude")
    private String marketLatitude;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("market_address")
    private String marketAddress;

    @SerializedName("id")
    private int id;

    @SerializedName("representative")
    private Representative representative;

    @SerializedName("user")
    private UserData user;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("status")
    private int status;

    public void setRepresentativeId(String representativeId){
        this.representativeId = representativeId;
    }

    public String getRepresentativeId(){
        return representativeId;
    }

    public void setMarketImage(String marketImage){
        this.marketImage = marketImage;
    }

    public String getMarketImage(){
        return marketImage;
    }

    public void setMarketLongitude(String marketLongitude){
        this.marketLongitude = marketLongitude;
    }

    public String getMarketLongitude(){
        return marketLongitude;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setTotalCost(Object totalCost){
        this.totalCost = totalCost;
    }

    public Object getTotalCost(){
        return totalCost;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setDeliveryTime(String deliveryTime){
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime(){
        return deliveryTime;
    }

    public void setMarketName(String marketName){
        this.marketName = marketName;
    }

    public String getMarketName(){
        return marketName;
    }

    public void setMarketLatitude(String marketLatitude){
        this.marketLatitude = marketLatitude;
    }

    public String getMarketLatitude(){
        return marketLatitude;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setMarketAddress(String marketAddress){
        this.marketAddress = marketAddress;
    }

    public String getMarketAddress(){
        return marketAddress;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setRepresentative(Representative representative){
        this.representative = representative;
    }

    public Representative getRepresentative(){
        return representative;
    }

    public void setUser(UserData user){
        this.user = user;
    }

    public UserData getUser(){
        return user;
    }

    public void setPlaceId(String placeId){
        this.placeId = placeId;
    }

    public String getPlaceId(){
        return placeId;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "OrderDetail{" +
                        "representative_id = '" + representativeId + '\'' +
                        ",market_image = '" + marketImage + '\'' +
                        ",market_longitude = '" + marketLongitude + '\'' +
                        ",code = '" + code + '\'' +
                        ",address = '" + address + '\'' +
                        ",total_cost = '" + totalCost + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",description = '" + description + '\'' +
                        ",delivery_time = '" + deliveryTime + '\'' +
                        ",market_name = '" + marketName + '\'' +
                        ",market_latitude = '" + marketLatitude + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",market_address = '" + marketAddress + '\'' +
                        ",id = '" + id + '\'' +
                        ",representative = '" + representative + '\'' +
                        ",user = '" + user + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

}