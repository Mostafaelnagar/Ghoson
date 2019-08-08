package app.grand.a8oson.models.policies;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PoliciesResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private PoliciesData data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(PoliciesData data) {
        this.data = data;
    }

    public PoliciesData getData() {
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
                "PoliciesResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}