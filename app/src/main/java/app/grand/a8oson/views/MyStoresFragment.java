package app.grand.a8oson.views;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.FragmentMyStoresBinding;
import app.grand.a8oson.viewModels.MyStoresViewModels;


public class MyStoresFragment extends BaseFragment {
    FragmentMyStoresBinding myStoresBinding;
    MyStoresViewModels myStoresViewModels;


    public MyStoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myStoresBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_stores, container, false);
        myStoresViewModels = new MyStoresViewModels();
        myStoresBinding.setMyStoresViewModels(myStoresViewModels);
        liveDataListeners();
        return myStoresBinding.getRoot();
    }

    private void liveDataListeners() {
        myStoresViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            }
        });
    }

}
