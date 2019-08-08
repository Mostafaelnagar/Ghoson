package app.grand.a8oson.models.orders.orderDetail;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderDetailResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private OrderDetail data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(OrderDetail data) {
        this.data = data;
    }

    public OrderDetail getData() {
        return data;
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
                "OrderDetailResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}