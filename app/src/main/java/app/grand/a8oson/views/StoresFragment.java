package app.grand.a8oson.views;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentStoresBinding;
import app.grand.a8oson.viewModels.StoresViewModels;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

public class StoresFragment extends BaseFragment {
    FragmentStoresBinding storesBinding;
    StoresViewModels storesViewModels;
    private FusedLocationProviderClient client;

    private LocationManager service;
    private boolean enabled;
    private double lat, lng;
    private int count = 0;

    public StoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_stores, container, false);
        storesViewModels = new StoresViewModels();
        storesBinding.setStoresViewModels(storesViewModels);
        storesBinding.tabLayout.addTab(storesBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabNearestStoresText)));
        storesBinding.tabLayout.addTab(storesBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabActiveStoresText)));
        storesBinding.tabLayout.addTab(storesBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabAllText)));
        storesBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        storesBinding.tabLayout.getTabAt(2).select();

        storesBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                detailsBinding.viewPager.setCurrentItem(tab.getPosition());
                int position = tab.getPosition();
                storesViewModels.notifyChange();
                if (position == 0) {
                    storesViewModels.getStoreAdapter().marketsList.clear();
                    storesViewModels.getNearestMarkets(lat, lng);
                } else if (position == 1) {
                    storesViewModels.getStoreAdapter().marketsList.clear();
                    storesViewModels.getBestSellingMarkets(lat, lng);
                } else if (position == 2) {
                    storesViewModels.getStoreAdapter().marketsList.clear();
                    storesViewModels.getMyStores(lat, lng);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        storesViewModels.getCategoriesAdapter().categoryIdLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                storesViewModels.getStoresByCategory(integer, lat, lng);
            }
        });
        liveDataListeners();
        return storesBinding.getRoot();
    }

    private void liveDataListeners() {
        storesViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.STORE_DETAIL) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.TOKEN_EXPIRED) {
                MovementManager.startMainActivity(getActivity(), Codes.LOGIN_SCREEN);
                UserPreferenceHelper.getInstance(getActivity()).loggout();
            }
        });
    }

    private void getCurrentLocation() {

        ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, Codes.PICK_UP_LOCATION);
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            return;
        }

        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    //todo add new lat lng to map
                    Log.i("onSuccess", "onSuccess: " + lat + " lng " + lng);
                    storesViewModels.getMyStores(lat, lng);
                    storesViewModels.notifyChange();
                } else {
                    Log.i("onSuccess", "onSuccess:error " + lat + " lng " + lng);

                }


            }
        });

    }

    private void getLocationRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enable Your GPS?!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Enable GPS
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivityForResult(intent, Codes.PICK_UP_LOCATION);
                getActivity().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Codes.PICK_UP_LOCATION) {
//            if (data != null) {
//                getCurrentLocation();
//            } else {
//                getLocationRequest();
//            }
//        } else
        Toast.makeText(getActivity(), "" + requestCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        service = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        //return the GPS Status if Enabled or not
        enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

//        Check if enabled and if not send user to the GPS settings
        if (!enabled) {
            getLocationRequest();
        } else {
            if (count == 0) {
                count = 1;
                Log.i("onResume", "onResume: " + enabled);
                getCurrentLocation();
            }
        }
    }
}
