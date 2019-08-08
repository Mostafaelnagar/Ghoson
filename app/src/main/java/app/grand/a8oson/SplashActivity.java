package app.grand.a8oson;

 import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.ParentActivity;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.databinding.ActivitySplashBinding;
import app.grand.a8oson.viewModels.SplashScreenViewModel;

public class SplashActivity extends ParentActivity {
    ActivitySplashBinding splashBinding;
    SplashScreenViewModel screenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        screenViewModel = new SplashScreenViewModel();
        liveDataListeners();

    }


    private void liveDataListeners() {
        screenViewModel.getClicksMutableLiveData().observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (UserPreferenceHelper.getInstance(SplashActivity.this).getCurrentLanguage(SplashActivity.this) != null)
                            changeLan(UserPreferenceHelper.getInstance(SplashActivity.this).getCurrentLanguage(SplashActivity.this));
                        MovementManager.startBaseActivity(SplashActivity.this, integer);

                    }
                }
        );
    }

    private void changeLan(String lang) {
        Resources res = this.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        UserPreferenceHelper.getInstance(this).setLanguage(this, lang);
    }
}
