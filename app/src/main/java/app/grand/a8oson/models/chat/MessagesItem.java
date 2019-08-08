package app.grand.a8oson.models.chat;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessagesItem implements Serializable {

    @SerializedName("to_user")
    private ToUser toUser;

    @SerializedName("image")
    private String image;

    @SerializedName("id")
    private int id;

    @SerializedName("message")
    private String message;

    @SerializedName("type")
    private int type;

    @SerializedName("from_user")
    private FromUser fromUser;

    public void setToUser(ToUser toUser) {
        this.toUser = toUser;
    }

    public ToUser getToUser() {
        return toUser;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setFromUser(FromUser fromUser) {
        this.fromUser = fromUser;
    }

    public FromUser getFromUser() {
        return fromUser;
    }

    @Override
    public String toString() {
        return
                "MessagesItem{" +
                        "to_user = '" + toUser + '\'' +
                        ",image = '" + image + '\'' +
                        ",id = '" + id + '\'' +
                        ",message = '" + message + '\'' +
                        ",type = '" + type + '\'' +
                        ",from_user = '" + fromUser + '\'' +
                        "}";
    }
}