package app.grand.a8oson.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.WaitingOrdersAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.orders.waitingOrders.WaitingOrdersResponse;

public class WaitingOrdersViewModels extends BaseViewModel {
    public int emptyList;
    public WaitingOrdersAdapter ordersAdapter;

    public WaitingOrdersViewModels() {
        getMyOrders();
    }

    public void getMyOrders() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                WaitingOrdersResponse ordersResponse = (WaitingOrdersResponse) response;
                Log.i("onRequestSuccess", "onRequestSuccess: " + response);
                if (ordersResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(ordersResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getOrdersAdapter().updateData(ordersResponse.getData());
                } else if (ordersResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), ordersResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), ordersResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.SHOP_WAITING_ORDERS + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId(), new Object(), WaitingOrdersResponse.class);
    }

    @Bindable
    public WaitingOrdersAdapter getOrdersAdapter() {
        return this.ordersAdapter == null ? this.ordersAdapter = new WaitingOrdersAdapter() : this.ordersAdapter;
    }

    @BindingAdapter({"app:ordersAdapter"})
    public static void getOrderBinding(RecyclerView recyclerView, WaitingOrdersAdapter ordersAdapter) {
        recyclerView.setAdapter(ordersAdapter);
    }

    @Bindable
    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
}
