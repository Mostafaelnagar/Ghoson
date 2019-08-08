package app.grand.a8oson.models.auth.checkIsDelegate;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckData implements Serializable {

    @SerializedName("orders_count")
    private String ordersCount;

    @SerializedName("rate")
    private float rate;

    @SerializedName("account")
    private String account;

    @SerializedName("reviews_count")
    private String reviewsCount;

    public void setOrdersCount(String ordersCount) {
        this.ordersCount = ordersCount;
    }

    public String getOrdersCount() {
        return ordersCount;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setReviewsCount(String reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getReviewsCount() {
        return reviewsCount;
    }

    @Override
    public String toString() {
        return
                "CheckData{" +
                        "orders_count = '" + ordersCount + '\'' +
                        ",rate = '" + rate + '\'' +
                        ",account = '" + account + '\'' +
                        ",reviews_count = '" + reviewsCount + '\'' +
                        "}";
    }
}