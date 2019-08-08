package app.grand.a8oson.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.grand.a8oson.models.auth.UserData;
import app.grand.a8oson.models.markets.Markets;


public class UserPreferenceHelper {
    private static UserPreferenceHelper mInstance;
    private static Context mCtx;
    private static final String SHARED_NAMES = "LuandrySharedPref";
    private static final String SHARED_PREF_NAME = "myshared";

    private UserPreferenceHelper(Context context) {
        mCtx = context;
    }

    public static synchronized UserPreferenceHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserPreferenceHelper(context);
        }
        return mInstance;
    }

    public void userLogin(UserData userData) {
        loggout();
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userData);
        editor.putString("userData", json);
        editor.apply();
        editor.commit();

    }


    public void addUserPhone(String userPhone) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPhone", userPhone);
        editor.apply();

    }

    public void addMarketImage(String marketImage) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("marketImage", marketImage);
        editor.apply();

    }

    public void addMarketName(String marketName) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("marketName", marketName);
        editor.apply();

    }

    public void addMarketLat(String marketLat) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("marketLat", marketLat);
        editor.apply();

    }

    public void addMarketLng(String marketLng) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("marketLng", marketLng);
        editor.apply();

    }

    public void addMarketAddress(String marketAddress) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("marketAddress", marketAddress);
        editor.apply();

    }

    public void addUserId(String userId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.apply();

    }

    public void addOrderId(String orderId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("order_Id", orderId);
        editor.apply();

    }

    public String addUserData() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userData", null);

    }


    public String getUserPhone() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userPhone", null);
    }

    public String getMarketName() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("marketName", null);
    }

    public String getMarketLat() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("marketLat", null);
    }

    public String getMarketLng() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("marketLng", null);
    }

    public String getMarketAddress() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("marketAddress", null);
    }

    public String getMarketImage() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("marketImage", null);
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", null);
    }

    public String getOrderId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("order_Id", null);
    }


    public UserData getUserData() {
        Gson gson = new Gson();
        String json = addUserData();
        UserData obj = gson.fromJson(json, UserData.class);
        return obj;
    }

    public boolean loggout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public void addPlaceId(String placeId) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Place_Id", placeId);
        editor.apply();
    }

    public String getPlaceId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Place_Id", null);
    }

    public void removeSecDepsId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("trip_fir_secs_type").apply();

    }

    public void setLanguage(Context context, String language) {
        SharedPreferences userDetails = context.getSharedPreferences("languageData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userDetails.edit();
        editor.putString("language", language);
        editor.putBoolean("haveLanguage", true);
        editor.apply();
    }

    public String getCurrentLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("languageData", Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("haveLanguage", false)) return "en";
        return sharedPreferences.getString("language", "en");
    }

}
