package app.grand.a8oson.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.Bindable;

import com.android.volley.Request;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.auth.UserData;
import app.grand.a8oson.models.auth.login.LoginRequest;
import app.grand.a8oson.models.auth.register.RegisterResponse;

public class LoginViewModel extends BaseViewModel {
    LoginRequest loginRequest;


    public LoginViewModel() {

        loginRequest = new LoginRequest();
    }

    @Bindable
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void goLogin() {


        if (getLoginRequest().isLoginValid()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    accessLoadingBar(View.GONE);
                    RegisterResponse userResponse = (RegisterResponse) response;
                    if (userResponse.getStatus() == WebServices.SUCCESS) {
                        UserData userItem = userResponse.getData();
                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).userLogin(userItem);
                        getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);
                    } else if (userResponse.getStatus() == 405) {
                        Log.e("onRequestSuccess", "onRequestSuccess: 405");
                        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();

                    } else if (userResponse.getStatus() == 401) {
                        Log.e("onRequestSuccess", "onRequestSuccess: 401");

                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
                    }
                }
            }).requestJsonObject(Request.Method.POST, URLS.LOGIN, loginRequest, RegisterResponse.class);
        } else

            Toast.makeText(MyApplication.getInstance().

                    getApplicationContext(), MyApplication.getInstance().

                    getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).

                    show();
    }


    public void goForget() {
        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
    }

    public void getRegister() {
        getClicksMutableLiveData().setValue(Codes.REGISTER_SCREEN);
    }

    public void goFacebook() {
        getClicksMutableLiveData().setValue(Codes.WITH_FACEBOOK);
    }

}