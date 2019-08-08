package app.grand.a8oson.models.auth.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("mobile")
    private String mobile;
    @SerializedName("password")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginValid() {
        return (mobile != null && password != null);
    }
}
