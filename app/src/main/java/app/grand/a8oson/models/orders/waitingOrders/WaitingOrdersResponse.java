package app.grand.a8oson.models.orders.waitingOrders;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WaitingOrdersResponse implements Serializable {


    @SerializedName("data")
    private List<MyOrdersData> data;

    @SerializedName("msg")
    private String msg;


    @SerializedName("status")
    private int status;
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(List<MyOrdersData> data) {
        this.data = data;
    }

    public List<MyOrdersData> getData() {
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
                "WaitingOrdersResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}