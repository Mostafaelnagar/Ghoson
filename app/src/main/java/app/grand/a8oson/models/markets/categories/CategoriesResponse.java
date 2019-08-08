package app.grand.a8oson.models.markets.categories;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CategoriesResponse implements Serializable {

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private List<Categories> data;

    @SerializedName("status")
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(List<Categories> data) {
        this.data = data;
    }

    public List<Categories> getData() {
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
                "CategoriesResponse{" +
                        "msg = '" + msg + '\'' +
                        ",data = '" + data + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}