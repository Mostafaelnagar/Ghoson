package app.grand.a8oson.views;


import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.PopUpItem;
import app.grand.a8oson.base.PopUpMenus;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentMyOrdersBinding;
import app.grand.a8oson.viewModels.MyOrdersViewModels;

public class MyOrdersFragment extends BaseFragment {
    FragmentMyOrdersBinding myOrdersBinding;
    MyOrdersViewModels ordersViewModels;
    List<PopUpItem> popUpItems;

    public MyOrdersFragment() {
        // Required empty public constructor
        popUpItems = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myOrdersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_orders, container, false);
        ordersViewModels = new MyOrdersViewModels();
        myOrdersBinding.setMyOrdersViewModels(ordersViewModels);
        Resources resources = getActivity().getResources();

        popUpItems.add(new PopUpItem(0, resources.getString(R.string.orderActive)));
        popUpItems.add(new PopUpItem(1, resources.getString(R.string.orderEnd)));
        ordersViewModels.setStatusName(resources.getString(R.string.orderActive));
        liveDataListeners();
        ordersViewModels.getMyOrdersAdapter().type = 1;

        return myOrdersBinding.getRoot();
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
        PopUpMenus.showOrderStatusPopUp(getActivity(), myOrdersBinding.orderStatus, popUpItems).
                setOnMenuItemClickListener(item -> {
                    ordersViewModels.getMyOrdersAdapter().orderData.clear();
                    ordersViewModels.setStatusName(popUpItems.get(item.getItemId()).getName());
                    ordersViewModels.setOrderStatus(popUpItems.get(item.getItemId()).getId() + 1);
                    ordersViewModels.notifyChange();
                    ordersViewModels.myOrders();
                    return false;
                });

    }

}
