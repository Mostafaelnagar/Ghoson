package app.grand.a8oson.views;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.SelectLocationActivity;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.FileOperations;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.databinding.FragmentOrderNowBinding;
import app.grand.a8oson.viewModels.OrderNowViewModel;

public class OrderNowFragment extends BaseFragment {
    FragmentOrderNowBinding orderNowBinding;
    OrderNowViewModel orderNowViewModel;

    public OrderNowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        orderNowBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_now, container, false);
        orderNowViewModel = new OrderNowViewModel();
        orderNowBinding.setOrderNowViewModel(orderNowViewModel);
        liveDataListeners();
        return orderNowBinding.getRoot();
    }

    private void liveDataListeners() {
        orderNowViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.PICK_UP_LOCATION) {
                Intent intent = new Intent(getActivity(), SelectLocationActivity.class);
                startActivityForResult(intent, Codes.PICK_UP_LOCATION);
                orderNowViewModel.notifyChange();
            } else if (result == Codes.PICK_UP_ORDER_IMAGE) {
                selectProfilePhoto();

            } else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            if (data != null) {
                VolleyFileObject volleyFileObject = FileOperations.getVolleyFileObject(getActivity(), data, WebServices.IMAGES, requestCode);
                orderNowBinding.orderImage.setImageBitmap(Objects.requireNonNull(volleyFileObject).getCompressObject().getImage());
                orderNowViewModel.getVolleyFileObjects().add(volleyFileObject);
            }
        } else if (data != null) {
            double lat = data.getDoubleExtra("lat", 0.0);
            double lng = data.getDoubleExtra("lng", 0.0);
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.pickLocationSuccess), Toast.LENGTH_SHORT).show();
            orderNowViewModel.setDeliveredLocation(getActivity().getResources().getString(R.string.pickLocationSuccess));
            orderNowViewModel.getOrderRequest().setLatitude(lat);
            orderNowViewModel.getOrderRequest().setLongitude(lng);
            orderNowViewModel.notifyChange();

        }
    }

    private void selectProfilePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                // start picker to get image for cropping and then use the image in cropping activity
                FileOperations.pickImage(getActivity());
            }
        } else {
            FileOperations.pickImage(getActivity());

        }
    }

    protected void showProgressAnimation(int show) {
        LottieAnimationView animationView;
        animationView = ((BaseActivity) getActivity()).getAnimationView();
        animationView.setAnimation(R.raw.loading);
        animationView.setVisibility(show);
    }
}
