package app.grand.a8oson.views;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.FragmentOrdreDetailBinding;
import app.grand.a8oson.viewModels.OrderDetailViewModels;


public class OrdreDetailFragment extends BaseFragment implements OnMapReadyCallback {
    FragmentOrdreDetailBinding fragmentOrdreDetailBinding;
    OrderDetailViewModels detailViewModels;
    MapFragment mapFragment;
    GoogleMap mMap;
    private FusedLocationProviderClient client;
    Bundle bundle;

    public OrdreDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentOrdreDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ordre_detail, container, false);
        detailViewModels = new OrderDetailViewModels();
        fragmentOrdreDetailBinding.setOrderViewModels(detailViewModels);
        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.orderDetailmapFragment);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        mapFragment.getMapAsync(this);
        liveDataListeners();
        bundle = this.getArguments();
        return fragmentOrdreDetailBinding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap = googleMap;
    }

    private void liveDataListeners() {
        detailViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
                if (detailViewModels.getDetail().getLatitude() != null) {
                    MovementManager.setupLocations(Double.parseDouble(detailViewModels.getDetail().getLatitude()),
                            Double.parseDouble(detailViewModels.getDetail().getLongitude()), mMap, getActivity(), R.mipmap.ic_current_location);

                }

            } else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            } else if (result == Codes.CHAT) {
//                MovementManager.startActivity(getActivity(), Codes.CHAT);
                Intent intent = new Intent();
                if (bundle != null) {
                    int type = bundle.getInt(Params.CHAT_TYPE);
                    if (type == 0) {
                        intent.putExtra(Params.CHAT_NAME, detailViewModels.getDetail().getUser().getName());

                    } else {
                        intent.putExtra(Params.CHAT_NAME, detailViewModels.getDetail().getRepresentative().getName());

                    }
                }
                MovementManager.startActivityWithBundle(getActivity(), Codes.CHAT, intent);

            }
        });
    }
}
