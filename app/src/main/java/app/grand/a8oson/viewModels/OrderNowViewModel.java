package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;

import androidx.databinding.Bindable;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.markets.MarketResponse;
import app.grand.a8oson.models.orders.CreateOrderResponse;
import app.grand.a8oson.models.orders.OrderRequest;

public class OrderNowViewModel extends BaseViewModel {
    public String deliveredTime, deliveredLocation, deliveredDate;
    public OrderRequest orderRequest;
    private ArrayList<VolleyFileObject> volleyFileObjects;

    public OrderNowViewModel() {
        volleyFileObjects = new ArrayList<>();

        orderRequest = new OrderRequest();
    }

    public void toBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public ArrayList<VolleyFileObject> getVolleyFileObjects() {
        return volleyFileObjects;
    }

    public void createOrder() {
        getOrderRequest().setDelivery_time(deliveredDate + " " + deliveredTime);
        getOrderRequest().setPlace_id(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId());
        //todo add address dynamic
        getOrderRequest().setAddress("test");
        getOrderRequest().setMarketImage(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getMarketImage());
        getOrderRequest().setMarketName(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getMarketName());
        getOrderRequest().setMarketAddress(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getMarketAddress());
        getOrderRequest().setMarketLatitude(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getMarketLat());
        getOrderRequest().setMarketLongitude(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getMarketLng());
        if (getOrderRequest().orderVaild()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    CreateOrderResponse createOrderResponse = (CreateOrderResponse) response;
                    if (createOrderResponse.getStatus() == WebServices.SUCCESS) {
                        Toast.makeText(MyApplication.getInstance(), createOrderResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        accessLoadingBar(View.GONE);
                        getClicksMutableLiveData().setValue(Codes.BACK);
                    } else {
                        accessLoadingBar(View.GONE);
                        Toast.makeText(MyApplication.getInstance(), createOrderResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).requestJsonObject(Request.Method.POST, URLS.CREATE_CATEGORY, orderRequest, CreateOrderResponse.class);
        } else
            Toast.makeText(MyApplication.getInstance(), MyApplication.getInstance().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    public void pickUpDeliverdLocation() {
        getClicksMutableLiveData().setValue(Codes.PICK_UP_LOCATION);
    }

    public void pickOrderDetailImage() {
        getClicksMutableLiveData().setValue(Codes.PICK_UP_ORDER_IMAGE);
    }

    @Bindable
    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        notifyChange();
        this.deliveredTime = deliveredTime;
    }

    @Bindable
    public String getDeliveredLocation() {
        return deliveredLocation;
    }

    public void setDeliveredLocation(String deliveredLocation) {
        this.deliveredLocation = deliveredLocation;
        notifyChange();

    }

    @Bindable
    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        notifyChange();
        this.deliveredDate = deliveredDate;
    }
}
