package app.grand.a8oson.models.chat;

import com.google.gson.annotations.SerializedName;

public class ChatRequest {
    @SerializedName("to_user_id")
    private String toUserId;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("message")
    private String message;

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
