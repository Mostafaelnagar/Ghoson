package app.grand.a8oson.base.volleyutils;


import androidx.multidex.MultiDexApplication;

import app.grand.a8oson.base.ConnectivityReceiver;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;



public class MyApplication extends MultiDexApplication {
    public static final String TAG = MyApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        /*OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new GanderInterceptor(this))
                .build();

        // For In Memory DB (tripSections retained in memory lost on app close)
        Gander.setGanderStorage(GanderIMDB.getInstance());
        new GanderInterceptor(this).showNotification(true);*/

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

}