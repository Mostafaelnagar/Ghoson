package app.grand.a8oson.viewModels;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.auth.register.RegisterRequest;
import app.grand.a8oson.models.auth.register.RegisterResponse;

public class SignUpViewModel extends BaseViewModel {
    public RegisterRequest registerRequest;
    public boolean isScheduleChecked;

    public SignUpViewModel() {
        registerRequest = new RegisterRequest();
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void companyRegister() {
//        if (getRegisterRequest().isValid()) {
//            accessLoadingBar(View.VISIBLE);
//            new ConnectionHelper(new ConnectionListener() {
//                @Override
//                public void onRequestSuccess(Object response) {
//                    accessLoadingBar(View.GONE);
//                    RegisterResponse userResponse = (RegisterResponse) response;
//                    if (userResponse.getStatus() == WebServices.SUCCESS) {
//                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addUserPhone(registerRequest.getMobile());
//                        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
//                    } else {
//                        accessLoadingBar(View.GONE);
//                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//            }).requestJsonObject(Request.Method.POST, URLS.REGISTER, registerRequest, RegisterResponse.class);
//        } else
//            Toast.makeText(MyApplication.getInstance().
//
//                    getApplicationContext(), MyApplication.
//
//                    getInstance().
//
//                    getResources().
//
//                    getString(R.string.emptyData), Toast.LENGTH_SHORT).
//
//                    show();
        if (isScheduleChecked) {
            Log.i("companyRegister", "companyRegister: Thank you");

        } else {
            Log.i("companyRegister", "companyRegister: Should Accept terms");
        }

    }

    public void check() {
        if (isScheduleChecked) {
            isScheduleChecked = false;
        } else {
            isScheduleChecked = true;
        }

    }

}