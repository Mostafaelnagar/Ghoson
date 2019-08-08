package app.grand.a8oson.models.chat;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ChatData implements Serializable {

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("messages")
    private List<MessagesItem> messages;

    @SerializedName("order_id")
    private int orderId;
    @SerializedName("button_status")
    private int buttonStatus;
    @SerializedName("order_status")
    private int orderStatus;

    @SerializedName("longitude")
    private String longitude;

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(int buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setMessages(List<MessagesItem> messages) {
        this.messages = messages;
    }

    public List<MessagesItem> getMessages() {
        return messages;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return
                "ChatData{" +
                        "latitude = '" + latitude + '\'' +
                        ",messages = '" + messages + '\'' +
                        ",order_id = '" + orderId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}