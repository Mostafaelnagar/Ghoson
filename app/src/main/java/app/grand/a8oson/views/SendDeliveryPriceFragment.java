package app.grand.a8oson.views;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentSendDeliveryPriceBinding;
import app.grand.a8oson.viewModels.SendPriceViewModels;


public class SendDeliveryPriceFragment extends BaseFragment implements OnMapReadyCallback {

    FragmentSendDeliveryPriceBinding sendDeliveryPriceBinding;
    SendPriceViewModels sendPriceViewModels;
    MapFragment mapFragment;
    GoogleMap mMap;

    public SendDeliveryPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sendDeliveryPriceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_delivery_price, container, false);
        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.sendPriceMap);
        sendPriceViewModels = new SendPriceViewModels();
        sendDeliveryPriceBinding.setOrderViewModels(sendPriceViewModels);
        mapFragment.getMapAsync(this);
        liveDataListeners();

        return sendDeliveryPriceBinding.getRoot();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap = googleMap;
    }

    private void liveDataListeners() {
        sendPriceViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
                if (sendPriceViewModels.getDetail().getLatitude() != null) {
                    MovementManager.setupLocations(Double.parseDouble(sendPriceViewModels.getDetail().getLatitude()),
                            Double.parseDouble(sendPriceViewModels.getDetail().getLongitude()), mMap, getActivity(), R.mipmap.ic_current_location);
                    if (sendPriceViewModels.getDetail().getMarketLatitude() != null) {
                        MovementManager.setupLocations(Double.parseDouble(sendPriceViewModels.getDetail().getMarketLatitude()),
                                Double.parseDouble(sendPriceViewModels.getDetail().getMarketLongitude()), mMap, getActivity(), R.drawable.ic_shope);
                    }

                }

            } else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            } else if (result == Codes.CHAT) {
                MovementManager.startActivity(getActivity(), Codes.CHAT);
                ((Activity) getActivity()).finish();
            }
        });
    }
}
