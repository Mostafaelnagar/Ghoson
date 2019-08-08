package app.grand.a8oson.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.databinding.FragmentOrderNavigationBinding;
import app.grand.a8oson.viewModels.OrderNavigationViewModels;

public class OrderNavigationFragment extends BaseFragment {
    FragmentOrderNavigationBinding orderNavigationBinding;
    OrderNavigationViewModels navigationViewModels;

    public OrderNavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         orderNavigationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_navigation, container, false);
        navigationViewModels = new OrderNavigationViewModels();
        orderNavigationBinding.setOrderNavigationViewModels(navigationViewModels);
        return orderNavigationBinding.getRoot();
    }

}
