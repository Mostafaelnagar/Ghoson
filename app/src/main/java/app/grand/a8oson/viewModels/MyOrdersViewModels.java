package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.MyOrdersAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.orders.myOrders.MyOrdersResponse;

public class MyOrdersViewModels extends BaseViewModel {
    public int orderStatus;
    public String statusName;
    int emptyList;
    MyOrdersAdapter myOrdersAdapter;

    public MyOrdersViewModels() {
        myOrders();
    }

    @BindingAdapter({"app:myOrdersAdapter"})
    public static void getOrdersBinding(RecyclerView recyclerView, MyOrdersAdapter myOrdersAdapter) {
        recyclerView.setAdapter(myOrdersAdapter);
    }

    @Bindable
    public MyOrdersAdapter getMyOrdersAdapter() {
        return this.myOrdersAdapter == null ? this.myOrdersAdapter = new MyOrdersAdapter() : this.myOrdersAdapter;
    }

    public void myOrders() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MyOrdersResponse myOrdersResponse = (MyOrdersResponse) response;
                if (myOrdersResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(myOrdersResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getMyOrdersAdapter().updateData(myOrdersResponse.getData());
                } else {
                    setEmptyList(myOrdersResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), myOrdersResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.MY_ORDERS + getOrderStatus(), new Object(), MyOrdersResponse.class);
    }

    public void deliverOrders() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MyOrdersResponse myOrdersResponse = (MyOrdersResponse) response;
                if (myOrdersResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(myOrdersResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getMyOrdersAdapter().updateData(myOrdersResponse.getData());
                } else {
                    setEmptyList(myOrdersResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), myOrdersResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.MY_ORDERS_DELIVERY + getOrderStatus(), new Object(), MyOrdersResponse.class);
    }

    public void orderStatus() {
        getClicksMutableLiveData().setValue(Codes.ORDER_STATUS);
    }

    public int getOrderStatus() {
        if (orderStatus == 0)
            orderStatus = 1;
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {

        this.orderStatus = orderStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Bindable
    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        this.emptyList = emptyList;
        notifyChange();

    }
}
