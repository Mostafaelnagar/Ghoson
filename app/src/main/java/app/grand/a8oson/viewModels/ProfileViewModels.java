package app.grand.a8oson.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.auth.UserData;
import app.grand.a8oson.models.auth.checkIsDelegate.CheckData;
import app.grand.a8oson.models.auth.checkIsDelegate.CheckIsDelegateResponse;
import app.grand.a8oson.models.auth.register.RegisterResponse;

public class ProfileViewModels extends BaseViewModel {
    private ArrayList<VolleyFileObject> volleyFileObjects;
    CheckData checkData;
    UserData userData;
    public int gender, notADelegate = 1;
    String useName;

    public ProfileViewModels() {
        volleyFileObjects = new ArrayList<>();
        userData = new UserData();
        checkData = new CheckData();
    }

    public void PickUpProfileImage() {
        getClicksMutableLiveData().setValue(Codes.SELECT_PROFILE_IMAGE);
    }

    public ArrayList<VolleyFileObject> getVolleyFileObjects() {
        return volleyFileObjects;
    }

    public UserData getUserData() {
        return userData;
    }

    public CheckData getCheckData() {
        return checkData;
    }


    public void setUserData(UserData userData) {
        notifyChange();
        this.userData = userData;
    }


    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view, String profileImage) {
        if (profileImage != null)
            ConnectionHelper.loadImage(view, profileImage);

    }

    @BindingAdapter({"rating"})
    public static void setRating(RatingBar view, float rating) {
        view.setRating(rating);
    }

    public void getProfileData() {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                RegisterResponse userResponse = (RegisterResponse) response;
                if (userResponse.getStatus() == WebServices.SUCCESS) {
                    UserData userItem = userResponse.getData();
                    getClicksMutableLiveData().setValue(userItem.getGender());
                    setUserData(userItem);
                    notifyChange();
                } else if (userResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.GET_PROFILE, new Object(), RegisterResponse.class);

    }

    public void checkUserIsDelegate() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                CheckIsDelegateResponse userResponse = (CheckIsDelegateResponse) response;
                if (userResponse.getStatus() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    checkData = userResponse.getData();
                    setNotADelegate(0);
                    notifyChange();

                } else if (userResponse.getStatus() == WebServices.ERROR) {
                    setNotADelegate(1);
                    accessLoadingBar(View.GONE);
                } else if (userResponse.getStatus() == 405) {
                    accessLoadingBar(View.GONE);
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

        }).requestJsonObject(Request.Method.GET, URLS.USER_IS_DELEGATE, new Object(), CheckIsDelegateResponse.class);

    }

    public void updateUser() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                RegisterResponse userResponse = (RegisterResponse) response;
                if (userResponse.getStatus() == WebServices.SUCCESS) {
                    UserData data = userResponse.getData();
                    setUserData(userResponse.getData());
                    data.setStatus(data.getStatus());
                    data.setRate(data.getRate());
                    data.setRoleId(data.getRoleId());
                    data.setUpdatedAt(data.getUpdatedAt());
                    data.setFacebookId(data.getFacebookId());
                    data.setCreatedAt(data.getCreatedAt());
                    data.setEmail(data.getEmail());
                    data.setJwt(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getJwt());
                    data.setId(data.getId());
                    data.setImage(data.getImage());
                    data.setName(data.getName());
                    data.setDateOfBirth(data.getDateOfBirth());
                    data.setDeviceToken(data.getDeviceToken());
                    data.setGender(data.getGender());
                    data.setMobile(data.getMobile());
                    UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).userLogin(data);
                    setUseName(data.getName());
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }

        }).multiPartConnect(URLS.UPDATE_PROFILE, userData, volleyFileObjects, RegisterResponse.class);

    }

    public void goSettings() {
        getClicksMutableLiveData().setValue(Codes.PROFILE_GO_SETTINGS);
    }

    public void goBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void goLogout() {
        getClicksMutableLiveData().setValue(Codes.LOG_OUT);
    }

    @Bindable
    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {

        this.useName = useName;
    }

    public int getNotADelegate() {
        return notADelegate == 0 ? View.VISIBLE : View.GONE;
    }

    public void setNotADelegate(int notADelegate) {
        notifyChange();
        this.notADelegate = notADelegate;
    }
}
