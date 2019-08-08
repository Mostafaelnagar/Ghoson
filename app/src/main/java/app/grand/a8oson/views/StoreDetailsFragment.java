package app.grand.a8oson.views;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.PopUpMenus;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentStoreDetailsBinding;
import app.grand.a8oson.viewModels.StoreDetailsViewModels;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class StoreDetailsFragment extends BaseFragment implements OnMapReadyCallback {
    FragmentStoreDetailsBinding fragmentStoreDetailsBinding;
    StoreDetailsViewModels storeDetailsViewModels;
    MapFragment mapFragment;
    double marketLat, marketLng;
    GoogleMap mMap;
    private FusedLocationProviderClient client;
    private List<String> popUpItems;

    public StoreDetailsFragment() {
        popUpItems = new ArrayList<>();
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentStoreDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_details, container, false);
        storeDetailsViewModels = new StoreDetailsViewModels();
        fragmentStoreDetailsBinding.setStoreDetailSViewModel(storeDetailsViewModels);

        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapFragment);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        mapFragment.getMapAsync(this);
        getCurrentLocation();
        liveDataListeners();
        return fragmentStoreDetailsBinding.getRoot();
    }

    private void liveDataListeners() {
        storeDetailsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.GO_ORDER_NOW) {

                MovementManager.startActivity(getActivity(), result);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
                if (storeDetailsViewModels.getMarketDetails().getLatitude() != 0.0) {
                    marketLat = storeDetailsViewModels.getMarketDetails().getLatitude();
                    marketLng = storeDetailsViewModels.getMarketDetails().getLogitude();
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(marketLat, marketLng))
                            .title(storeDetailsViewModels.getMarketDetails().getName())
                            .icon(MovementManager.bitmapDescriptorFromVector(getActivity(), R.mipmap.ic_shop)));
                }
            } else if (result == Codes.GET_WORKING_HOURS) {
                getWorkingHours();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap = googleMap;
    }


    private void getCurrentLocation() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    MovementManager.setupLocations(location.getLatitude(), location.getLongitude(), mMap, getActivity(), R.mipmap.ic_current_location);
                }


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapFragment);
        if (f != null)
            getActivity().getFragmentManager().beginTransaction().remove(f).commit();
    }

    private void getWorkingHours() {
        popUpItems = storeDetailsViewModels.workingHoursList;
        if (popUpItems != null)
            PopUpMenus.showWorkingHoursPopUp(getActivity(), fragmentStoreDetailsBinding.marDetailsTvWorkingHours, popUpItems);
    }
}
