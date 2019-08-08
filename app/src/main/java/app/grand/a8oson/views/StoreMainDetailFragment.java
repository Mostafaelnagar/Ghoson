package app.grand.a8oson.views;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentStoreDetailMainBinding;
import app.grand.a8oson.viewModels.StoreDetailsViewModels;
import app.grand.a8oson.viewModels.StoresViewModels;


public class StoreMainDetailFragment extends BaseFragment {

    FragmentStoreDetailMainBinding storeDetailMainBinding;
    StoreDetailsViewModels storesViewModels;

    public StoreMainDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        storeDetailMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_detail_main, container, false);
        storesViewModels = new StoreDetailsViewModels();
        storeDetailMainBinding.setStoreDetailSViewModel(storesViewModels);
        MovementManager.replaceHomeFragment(getActivity(), R.id.storeDetailsFrame, new StoreDetailsFragment(), "");
        storeDetailMainBinding.tabLayout.addTab(storeDetailMainBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabWaitingOrders)));
        storeDetailMainBinding.tabLayout.addTab(storeDetailMainBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabStoreDetails)));
        storeDetailMainBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        storeDetailMainBinding.tabLayout.getTabAt(1).select();

        storeDetailMainBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                detailsBinding.viewPager.setCurrentItem(tab.getPosition());
                int position = tab.getPosition();
                if (position == 0) {
                    MovementManager.replaceHomeFragment(getActivity(), R.id.storeDetailsFrame, new StoreWaitingOrdersFragment(), "");
                } else if (position == 1) {
                    MovementManager.replaceHomeFragment(getActivity(), R.id.storeDetailsFrame, new StoreDetailsFragment(), "");

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        liveDataListeners();

        return storeDetailMainBinding.getRoot();

    }

    private void liveDataListeners() {
        storesViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            }
        });
    }
}
