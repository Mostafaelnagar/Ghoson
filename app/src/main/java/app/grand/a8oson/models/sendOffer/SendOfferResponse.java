package app.grand.a8oson.models.sendOffer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SendOfferResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private SendOfferData data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(SendOfferData data) {
        this.data = data;
    }

    public SendOfferData getData() {
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
                "SendOfferResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}