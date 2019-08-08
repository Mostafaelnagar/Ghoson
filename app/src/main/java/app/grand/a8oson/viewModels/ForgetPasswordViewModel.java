package app.grand.a8oson.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.auth.UserData;
import app.grand.a8oson.models.auth.verify.VerifyRequest;
import app.grand.a8oson.models.auth.verify.VerifyResponse;

public class ForgetPasswordViewModel extends BaseViewModel {
    VerifyRequest verifyRequest;

    public ForgetPasswordViewModel() {
        verifyRequest = new VerifyRequest();
    }

    @Bindable
    public VerifyRequest getVerifyRequest() {
        return verifyRequest;
    }

    public void sendCode() {
//        if (getVerifyRequest().isPhoneValid()) {
//            accessLoadingBar(View.VISIBLE);
//            new ConnectionHelper(new ConnectionListener() {
//                @Override
//                public void onRequestSuccess(Object response) {
//                    accessLoadingBar(View.GONE);
//                    VerifyResponse successResponse = (VerifyResponse) response;
//                    if (successResponse.getStatus() == WebServices.SUCCESS) {
//                        Params.type = "forget";
//                        UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addUserPhone(getVerifyRequest().getMobile());
                        getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
//                    } else if (successResponse.getStatus() == WebServices.ERROR) {
//                        accessLoadingBar(View.GONE);
//                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((VerifyResponse) response).getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).requestJsonObject(Request.Method.POST, URLS.SEND_CODE, verifyRequest, VerifyResponse.class);
//        } else
//            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getApplicationContext().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    public void submitCode() {
//        getVerifyRequest().setMobile(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserPhone());
//        if (getVerifyRequest().isCodeValid()) {
//            accessLoadingBar(View.VISIBLE);
//            new ConnectionHelper(new ConnectionListener() {
//                @Override
//                public void onRequestSuccess(Object response) {
//                    accessLoadingBar(View.GONE);
//                    VerifyResponse successResponse = (VerifyResponse) response;
//                    if (successResponse.getStatus() == WebServices.SUCCESS) {
//                        UserData userItem = successResponse.getData();
//                        if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserPhone() != null) {
//                            UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).addUserId(String.valueOf(userItem.getId()));
//                            if (Params.type.equals("forget")) {
//                                Params.type = "";
                                getClicksMutableLiveData().setValue(Codes.FORGOT_PASSWORD_SCREEN);
//                            } else {
//
//                                getClicksMutableLiveData().setValue(Codes.LOGIN_SCREEN);
//                            }
//                        } else {
//                            getClicksMutableLiveData().setValue(Codes.ENTER_CODE_SCREEN);
//                        }
//                    } else if (successResponse.getStatus() == WebServices.ERROR) {
//                        accessLoadingBar(View.GONE);
//
//                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((VerifyResponse) response).getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).requestJsonObject(Request.Method.POST, URLS.CHECK_CODE, verifyRequest, VerifyResponse.class);
//        } else
//            Log.i("sendCode", "sendCode: ");
    }

    public void changePassword() {
        getVerifyRequest().setUser_id(UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserId());
        if (getVerifyRequest().isPasswordsValid()) {
            if (getVerifyRequest().getNew_confirm_password().equals(getVerifyRequest().getNew_password())) {
                accessLoadingBar(View.VISIBLE);
                new ConnectionHelper(new ConnectionListener() {
                    @Override
                    public void onRequestSuccess(Object response) {
                        accessLoadingBar(View.GONE);
                        VerifyResponse successResponse = (VerifyResponse) response;
                        if (successResponse.getStatus() == WebServices.SUCCESS) {
                            getClicksMutableLiveData().setValue(Codes.LOGIN_SCREEN);
                        } else if (successResponse.getStatus() == WebServices.ERROR) {
                            accessLoadingBar(View.GONE);
                            Toast.makeText(MyApplication.getInstance().getApplicationContext(), "" + ((VerifyResponse) response).getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).requestJsonObject(Request.Method.POST, URLS.CAHNGE_PASSWORD, verifyRequest, VerifyResponse.class);
            } else
                Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.notMatchedPasswords), Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(MyApplication.getInstance().getApplicationContext(), MyApplication.getInstance().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }


}