package app.grand.a8oson.models.auth.checkIsDelegate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CheckIsDelegateResponse implements Serializable {
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose

    @SerializedName("data")
    private CheckData data;
    @Expose

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(CheckData data) {
        this.data = data;
    }

    public CheckData getData() {
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
                "CheckIsDelegateResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}