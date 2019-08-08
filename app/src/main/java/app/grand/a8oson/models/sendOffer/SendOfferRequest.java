package app.grand.a8oson.models.sendOffer;

import com.google.gson.annotations.SerializedName;

public class SendOfferRequest {
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("offer_cost")
    private String offerCost;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOfferCost() {
        return offerCost;
    }

    public void setOfferCost(String offerCost) {
        this.offerCost = offerCost;
    }

    public boolean sendOfferValid() {
        return (orderId != null && offerCost != null);
    }
}
