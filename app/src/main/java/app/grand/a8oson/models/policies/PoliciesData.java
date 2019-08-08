package app.grand.a8oson.models.policies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PoliciesData implements Serializable {

    @SerializedName("terms_and_conditions")
    private String termsAndConditions;

    @SerializedName("updated_at")
    private Object updatedAt;

    @SerializedName("about_us")
    private String aboutUs;

    @SerializedName("privacy_policy")
    private String privacyPolicy;

    @SerializedName("created_at")
    private Object createdAt;

    @SerializedName("my_account")
    private String myAccount;

    @SerializedName("id")
    private int id;

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
    }

    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setMyAccount(String myAccount) {
        this.myAccount = myAccount;
    }

    public String getMyAccount() {
        return myAccount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "PoliciesData{" +
                        "terms_and_conditions = '" + termsAndConditions + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",about_us = '" + aboutUs + '\'' +
                        ",privacy_policy = '" + privacyPolicy + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",my_account = '" + myAccount + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}