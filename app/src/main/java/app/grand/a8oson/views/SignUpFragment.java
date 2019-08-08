package app.grand.a8oson.views;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Objects;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.FileOperations;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.databinding.FragmentSignUpBinding;
import app.grand.a8oson.viewModels.SignUpViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment {
    FragmentSignUpBinding signUpBinding;
    SignUpViewModel signUpViewModel;
    private FusedLocationProviderClient client;

    private LocationManager service;
    private boolean enabled;
    private double lat, lng;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        signUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);

        signUpViewModel = new SignUpViewModel();
        signUpBinding.setSignUpViewModel(signUpViewModel);
        client = LocationServices.getFusedLocationProviderClient(getActivity());

//        service = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        //return the GPS Status if Enabled or not
//        enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Check if enabled and if not send user to the GPS settings
//        if (!enabled) {
//            getLocationRequest();
//        } else {
        getCurrentLocation();

//        }
        liveDataListeners();
        return signUpBinding.getRoot();
    }

    private void liveDataListeners() {
        signUpViewModel.getClicksMutableLiveData().observe(this, result -> {

            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.ENTER_CODE_SCREEN) {
                MovementManager.replaceFragment(getActivity(), new CodeConfirmationFragment(), Params.stackValue);
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //todo get location
        Log.i("onActivityResult", "onActivityResult: sign " + requestCode);
        if (data != null) {
            MovementManager.replaceFragment(getActivity(), new SignUpFragment(), Params.stackValue);
        } else {
            getLocationRequest();
        }
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
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    //todo add new lat lng to map
                    Log.i("onSuccess", "onSuccess: " + lat + " lng " + lng);
                    signUpViewModel.getRegisterRequest().setLatitude(lat);
                    signUpViewModel.getRegisterRequest().setLongitude(lng);
                    signUpViewModel.notifyChange();
                } else {
                    Log.i("onSuccess", "onSuccess:error " + lat + " lng " + lng);

                }


            }
        });

    }

    private void getLocationRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Enable Your GPS?!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Enable GPS
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, Codes.PICK_UP_LOCATION);
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }


}
