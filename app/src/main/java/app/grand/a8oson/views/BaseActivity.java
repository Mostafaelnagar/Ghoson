package app.grand.a8oson.views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.snackbar.Snackbar;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import app.grand.a8oson.R;
import app.grand.a8oson.base.ConnectivityReceiver;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.ParentActivity;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.databinding.ActivityBaseBinding;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends ParentActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    public ActivityBaseBinding activityBaseBinding;
    private LottieAnimationView animationView;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/font1.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        super.onCreate(savedInstanceState);
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        checkConnection();

        if (getIntent().hasExtra(Params.INTENT_PAGE)) {
            addFragment(getIntent().getIntExtra(Params.INTENT_PAGE, 0));
        }
    }


    private void addFragment(int page) {

        if (page == Codes.LOGIN_SCREEN) {
            if (UserPreferenceHelper.getInstance(this).getUserData() != null)
                MovementManager.startMainActivity(this, Codes.HOME_SCREEN);
            else
                MovementManager.addFragment(this, new LoginFragment(), "");
        } else if (page == Codes.REGISTER_SCREEN) {
            MovementManager.addFragment(this, new SignUpFragment(), "");
        } else if (page == Codes.SEND_CODE_SCREEN) {
            MovementManager.addFragment(this, new EmailConfirmationFragment(), "");
        } else if (page == Codes.HOME_SCREEN) {
            MovementManager.addFragment(this, new HomeFragment(), "");
        } else if (page == Codes.PROFILE_GO_SETTINGS) {
            MovementManager.addFragment(this, new SettingsFragment(), "");
        } else if (page == Codes.FORGOT_PASSWORD_SCREEN) {
            MovementManager.addFragment(this, new ForgetPasswordResetFragment(), "");
        } else if (page == Codes.ENTER_CODE_SCREEN) {
            MovementManager.addFragment(this, new CodeConfirmationFragment(), "");
        } else if (page == Codes.STORE_DETAIL) {
            MovementManager.addFragment(this, new StoreMainDetailFragment(), "");
        } else if (page == Codes.GO_ORDER_NOW) {
            MovementManager.addFragment(this, new OrderNowFragment(), "");
        } else if (page == Codes.MY_STORE) {
            MovementManager.addFragment(this, new MyStoresFragment(), "");
        } else if (page == Codes.EDIT_PROFILE) {
            MovementManager.addFragment(this, new EditProfileFragment(), "");
        } else if (page == Codes.POLICY) {
            MovementManager.addFragment(this, new AboutFragment(), "");
        } else if (page == Codes.TERMS) {
            MovementManager.addFragment(this, new ContactFragment(), "");
        } else if (page == Codes.ORDER_DETAILS) {
            OrdreDetailFragment ordreDetailFragment = new OrdreDetailFragment();
            Bundle bundle = new Bundle();
            Log.i("addFragment", "addFragment: " + getIntent().getStringExtra(Params.CHAT_TYPE));
            bundle.putString(Params.CHAT_TYPE, getIntent().getStringExtra(Params.CHAT_TYPE));
            ordreDetailFragment.setArguments(bundle);
            MovementManager.addFragment(this, ordreDetailFragment, "");
        } else if (page == Codes.OFFER_DETAIL) {
            MovementManager.addFragment(this, new SendDeliveryPriceFragment(), "");
        } else if (page == Codes.CHAT) {
            ChatFragment chatFragment = new ChatFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Params.CHAT_NAME, getIntent().getStringExtra(Params.CHAT_NAME));
            chatFragment.setArguments(bundle);
            MovementManager.addFragment(this, chatFragment, "");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            try {
                Fragment fragmentManager = getSupportFragmentManager().findFragmentByTag("REGISTER_SCREEN");
                fragmentManager.onActivityResult(requestCode, resultCode, data);
            } catch (Exception e) {
                Log.i("Ex", "Ex: " + e.getMessage());
            }
        } else if (requestCode == Codes.PICK_UP_LOCATION) {
            try {
                Fragment fragmentManager = getSupportFragmentManager().findFragmentByTag(Params.stackValue);
                fragmentManager.onActivityResult(requestCode, resultCode, data);
            } catch (Exception e) {
                Log.i("Ex", "Ex: " + e.getMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = getBaseContext().getString(R.string.connection_vaild_msg);
//            Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show();

        } else {
            message = getBaseContext().getString(R.string.connection_invaild_msg);
            color = Color.RED;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.base), message, Snackbar.LENGTH_LONG);

            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }


    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);

    }

    public LottieAnimationView getAnimationView() {
        return animationView;
    }
}