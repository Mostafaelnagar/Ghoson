package app.grand.a8oson.views;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.databinding.FragmentHomeBinding;
import app.grand.a8oson.viewModels.HomeViewModels;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    FragmentHomeBinding homeBinding;
    HomeViewModels homeViewModels;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        homeViewModels = new HomeViewModels();
        homeBinding.setHomeViewModel(homeViewModels);

        homeBinding.bottomView.setSelectedItemId(R.id.menuStores);
        homeBinding.bottomView.setOnNavigationItemSelectedListener(this);
        MovementManager.addHomeFragment(getContext(), new StoresFragment(), "");

        return homeBinding.getRoot();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuStores:
                MovementManager.replaceHomeFragment(getContext(), R.id.storeHomeFrame, new StoresFragment(), "");
                return true;
            case R.id.MenusOrders:
                if (UserPreferenceHelper.getInstance(getActivity()).getUserData().getType() == 0) {
                    MovementManager.replaceHomeFragment(getContext(), R.id.storeHomeFrame, new MyOrdersFragment(), "");
                } else {
                    MovementManager.replaceHomeFragment(getContext(), R.id.storeHomeFrame, new MandopOrdersFragment(), "");
                }
                return true;
            case R.id.MenusProfile:
                MovementManager.replaceHomeFragment(getContext(), R.id.storeHomeFrame, new ProfileFragment(), "");

                return true;
            case R.id.menuNotifications:
                MovementManager.replaceHomeFragment(getContext(), R.id.storeHomeFrame, new NotifcationsFragment(), "");

                return true;
            default:
                return false;
        }
    }
}
