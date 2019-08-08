package app.grand.a8oson.base;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import app.grand.a8oson.R;
import app.grand.a8oson.SplashActivity;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.views.BaseActivity;


public class MovementManager {


    //---------Fragments----------//
    private static final int CONTAINER_ID = R.id.fl_home_container;

    public static void popAllFragments(Context context) {
        FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public static void addFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(CONTAINER_ID, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }

    public static void removeFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().remove(fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }


    public static void addHomeFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(R.id.storeHomeFrame, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }


    public static void replaceFragment(Context context, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(CONTAINER_ID, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }

    public static void replaceHomeFragment(Context context, int layout, Fragment fragment, String backStackText) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(layout, fragment);
        if (!backStackText.equals("")) {
            fragmentTransaction.addToBackStack(backStackText);
        }
        fragmentTransaction.commit();
    }


    public static void popLastFragment(Context context) {
        ((FragmentActivity) context).getSupportFragmentManager().popBackStack();
    }

    //---------------Maps----------------------//
    public static void setupLocations(double lat, double lng, GoogleMap mMap, Context context, int icon) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12.0f));
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new LatLng(lat, lng)), 10000, null);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
//                .title(UserPreferenceHelper.getInstance(context).getUserData().getName())
                .icon(bitmapDescriptorFromVector(context, icon)));

    }

    public static BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    //-----------Activities-----------------//

    public static void startBaseActivity(Context context, int page) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.putExtra(Params.INTENT_PAGE, page);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void startActivity(Context context, int page) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.putExtra(Params.INTENT_PAGE, page);
        context.startActivity(intent);
    }

    public static void startActivityWithBundle(Context context, int page, Intent intentRec) {
        String chatName = intentRec.getStringExtra(Params.CHAT_NAME);
        Intent intent = new Intent(context, BaseActivity.class);
        intent.putExtra(Params.INTENT_PAGE, page);
        intent.putExtra(Params.CHAT_NAME, chatName);
        context.startActivity(intent);
    }

    public static void startMainActivity(Context context, int page) {
        Intent intent = new Intent(context, BaseActivity.class);
        intent.putExtra(Params.INTENT_PAGE, page);
        context.startActivity(intent);
        ((Activity) context).finish();

    }

    public static void startWhatsApp(Context context, String phone) {
        PackageManager pm = context.getPackageManager();
        try {
            Uri uri = Uri.parse("smsto:" + phone);
            Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");
            context.startActivity(waIntent);

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    public static void startWebPage(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);


    }

    public static void startCall(Context context, String phone) {
        try {

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            context.startActivity(intent);

        } catch (Exception e) {
            e.getStackTrace();
            Log.i("startWhatsApp", "startWhatsApp: " + e.getMessage());
        }
    }

    public static void restart(Context context) {
        ((Activity) context).finish();
        context.startActivity(new Intent(context, SplashActivity.class));
    }

    public static void mapNavigate(String lat, String lng, Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://ditu.google.cn/maps?f=d&source=s_d" +
                        "&daddr=" +
                        lat +
                        "," + lng +
                        "&hl=zh&t=m&dirflg=d"));


        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        ((Activity) context).startActivityForResult(intent, 1);
    }
}
