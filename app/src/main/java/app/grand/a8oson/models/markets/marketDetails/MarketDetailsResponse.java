package app.grand.a8oson.models.markets.marketDetails;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MarketDetailsResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private MarketDetails data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(MarketDetails data) {
        this.data = data;
    }

    public MarketDetails getData() {
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
                "MarketDetailsResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}