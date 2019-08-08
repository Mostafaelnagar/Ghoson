package app.grand.a8oson.models.markets.marketDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MarketDetails implements Serializable {

    @SerializedName("working_time")
    private List<String> workingTime;

    @SerializedName("address")
    private String address;

    @SerializedName("logitude")
    private double logitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    public List<String> getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(List<String> workingTime) {
        this.workingTime = workingTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return
                "MarketDetails{" +
                        "working_time = '" + workingTime + '\'' +
                        ",address = '" + address + '\'' +
                        ",logitude = '" + logitude + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",name = '" + name + '\'' +
                        ",category = '" + category + '\'' +
                        "}";
    }
}