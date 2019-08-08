package app.grand.a8oson.models.sendOffer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import app.grand.a8oson.models.orders.orderDetail.OrderDetail;
import app.grand.a8oson.models.orders.orderDetail.Representative;

public class SendOfferData implements Serializable {

    @SerializedName("representative_id")
    private int representativeId;

    @SerializedName("offer_cost")
    private String offerCost;

    @SerializedName("id")
    private int id;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("representative")
    private Representative representative;

    @SerializedName("status")
    private int status;

    @SerializedName("order")
    private OrderDetail order;

    public void setRepresentativeId(int representativeId) {
        this.representativeId = representativeId;
    }

    public int getRepresentativeId() {
        return representativeId;
    }

    public void setOfferCost(String offerCost) {
        this.offerCost = offerCost;
    }

    public String getOfferCost() {
        return offerCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }

    public Representative getRepresentative() {
        return representative;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setOrder(OrderDetail order) {
        this.order = order;
    }

    public OrderDetail getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return
                "SendOfferData{" +
                        "representative_id = '" + representativeId + '\'' +
                        ",offer_cost = '" + offerCost + '\'' +
                        ",id = '" + id + '\'' +
                        ",order_id = '" + orderId + '\'' +
                        ",representative = '" + representative + '\'' +
                        ",status = '" + status + '\'' +
                        ",order = '" + order + '\'' +
                        "}";
    }
}