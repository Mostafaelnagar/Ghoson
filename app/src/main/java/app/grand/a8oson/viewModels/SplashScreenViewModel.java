package app.grand.a8oson.viewModels;

import android.os.Handler;

import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;

public class SplashScreenViewModel extends BaseViewModel {

    public SplashScreenViewModel() {
        startApp();
    }

    private void startApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //                    getClicksMutableLiveData().setValue(Codes.HOME_SCREEN);
                getClicksMutableLiveData().setValue(Codes.LOGIN_SCREEN);
            }
        }, 3000);

    }
}