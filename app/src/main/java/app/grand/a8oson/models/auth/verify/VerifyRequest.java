package app.grand.a8oson.models.auth.verify;

public class VerifyRequest {
    private String mobile, code, new_password, new_confirm_password;
    private String user_id;

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_confirm_password() {
        return new_confirm_password;
    }

    public void setNew_confirm_password(String new_confirm_password) {
        this.new_confirm_password = new_confirm_password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCodeValid() {
        return (code != null && mobile != null);
    }

    public boolean isPhoneValid() {
        return (mobile != null);
    }

    public boolean isPasswordsValid() {
        return (new_password != null && new_confirm_password != null);
    }
}
