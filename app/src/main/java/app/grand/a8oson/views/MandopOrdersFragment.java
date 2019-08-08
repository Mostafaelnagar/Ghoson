package app.grand.a8oson.views;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.PopUpItem;
import app.grand.a8oson.base.PopUpMenus;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentMandopOrdersBinding;
import app.grand.a8oson.viewModels.MyOrdersViewModels;

public class MandopOrdersFragment extends BaseFragment {

    private FragmentMandopOrdersBinding mandopOrdersBinding;
    private MyOrdersViewModels ordersViewModels;
    private List<PopUpItem> popUpItems;
    private int position = 3;

    public MandopOrdersFragment() {
        popUpItems = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mandopOrdersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mandop_orders, container, false);
        Resources resources = getActivity().getResources();

        ordersViewModels = new MyOrdersViewModels();
        popUpItems.add(new PopUpItem(0, resources.getString(R.string.orderActive)));
        popUpItems.add(new PopUpItem(1, resources.getString(R.string.orderEnd)));
        ordersViewModels.setStatusName(resources.getString(R.string.orderActive));
        ordersViewModels.getMyOrdersAdapter().type = 0; // 0 for user 1 for del
        mandopOrdersBinding.setMyOrdersViewModels(ordersViewModels);
        mandopOrdersBinding.tabLayout.addTab(mandopOrdersBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabOrdersDelivered)));
        mandopOrdersBinding.tabLayout.addTab(mandopOrdersBinding.tabLayout.newTab().setText(getActivity().getResources().getString(R.string.tabMyOrders)));
        mandopOrdersBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mandopOrdersBinding.tabLayout.getTabAt(1).select();
        //by default get MyOrders Fragment
        mandopOrdersBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                detailsBinding.viewPager.setCurrentItem(tab.getPosition());
                position = tab.getPosition();
                ordersViewModels.notifyChange();
                if (position == 1) {
                    ordersViewModels.setStatusName(resources.getString(R.string.orderActive));
                    ordersViewModels.setOrderStatus(1);
                    ordersViewModels.getMyOrdersAdapter().orderData.clear();
                    ordersViewModels.myOrders();
                    ordersViewModels.getMyOrdersAdapter().type = 0;
                } else if (position == 0) {
                    ordersViewModels.setStatusName(resources.getString(R.string.orderActive));
                    ordersViewModels.setOrderStatus(1);
                    ordersViewModels.getMyOrdersAdapter().orderData.clear();
                    ordersViewModels.deliverOrders();
                    ordersViewModels.getMyOrdersAdapter().type = 1;

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
        return mandopOrdersBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (position == 1) {
            ordersViewModels.getMyOrdersAdapter().orderData.clear();
            ordersViewModels.myOrders();
            ordersViewModels.getMyOrdersAdapter().type = 0;
        } else if (position == 0) {
            ordersViewModels.getMyOrdersAdapter().orderData.clear();
            ordersViewModels.deliverOrders();
            ordersViewModels.getMyOrdersAdapter().type = 0;
        }
    }

    private void liveDataListeners() {
        ordersViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.GO_ORDER_NOW) {
//                MovementManager.startActivity(getActivity(), result);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);

            } else if (result == Codes.ORDER_STATUS) {
                getOrderStatus();
            }
        });
    }

    private void getOrderStatus() {
        PopUpMenus.showOrderStatusPopUp(getActivity(), mandopOrdersBinding.orderStatus, popUpItems).
                setOnMenuItemClickListener(item -> {
                    ordersViewModels.getMyOrdersAdapter().orderData.clear();
                    ordersViewModels.setStatusName(popUpItems.get(item.getItemId()).getName());
                    ordersViewModels.setOrderStatus(popUpItems.get(item.getItemId()).getId() + 1);
                    ordersViewModels.notifyChange();
                    if (position == 0) {
                        ordersViewModels.getMyOrdersAdapter().orderData.clear();
                        ordersViewModels.myOrders();
                    } else if (position == 1) {
                        ordersViewModels.getMyOrdersAdapter().orderData.clear();
                        ordersViewModels.deliverOrders();

                    }
                    return false;
                });

    }
}
