package app.grand.a8oson.views;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.databinding.FragmentLoginBinding;
import app.grand.a8oson.viewModels.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {
    FragmentLoginBinding loginBinding;
    LoginViewModel loginViewModel;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new LoginViewModel();
        loginBinding.setLoginViewModel(loginViewModel);
        liveDataListeners();
        return loginBinding.getRoot();
    }

    private void liveDataListeners() {
        loginViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.REGISTER_SCREEN) {
                MovementManager.replaceFragment(getActivity(), new SignUpFragment(), Params.stackValue);
            } else if (result == Codes.ENTER_CODE_SCREEN) {
                MovementManager.replaceFragment(getActivity(), new EmailConfirmationFragment(), Params.stackValue);
            } else if (result == Codes.HOME_SCREEN) {
                MovementManager.startMainActivity(getActivity(), result);
            } else if (result == Codes.WITH_FACEBOOK) {
                withFacebook();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    private void withFacebook() {
        callbackManager = CallbackManager.Factory.create();
        loginButton = loginBinding.getRoot().findViewById(R.id.loginFaceBook);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                AccessToken accessToken = loginResult.getAccessToken();
                useLoginInformation(accessToken);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("onError", "onError: " + exception.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void useLoginInformation(AccessToken accessToken) {
        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");

                    Log.i("onCompleted", "onCompleted: " + name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }
}
