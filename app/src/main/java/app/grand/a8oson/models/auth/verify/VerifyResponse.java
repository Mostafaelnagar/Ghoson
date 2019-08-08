package app.grand.a8oson.models.auth.verify;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import app.grand.a8oson.models.auth.UserData;

public class VerifyResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private UserData data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public UserData getData() {
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
                "VerifyResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}