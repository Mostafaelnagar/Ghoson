package app.grand.a8oson.viewModels;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.Bindable;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.markets.marketDetails.MarketDetails;
import app.grand.a8oson.models.markets.marketDetails.MarketDetailsResponse;
import app.grand.a8oson.models.orders.OrderRequest;
import app.grand.a8oson.models.orders.RegisterAsDelegateResponse;

public class StoreDetailsViewModels extends BaseViewModel {
    public MarketDetails marketDetails;
    public List<String> workingHoursList;
    OrderRequest orderRequest;

    public StoreDetailsViewModels() {
        marketDetails = new MarketDetails();
        orderRequest = new OrderRequest();
        workingHoursList = new ArrayList<>();
        getMarketDetail();
    }

    @Bindable
    public MarketDetails getMarketDetails() {
        return marketDetails;
    }

    @Bindable
    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    public void goOrderNow() {
        UserPreferenceHelper.getInstance(MyApplication.getInstance()).addMarketAddress(marketDetails.getAddress());
        UserPreferenceHelper.getInstance(MyApplication.getInstance()).addMarketLat(marketDetails.getLatitude() + "");
        UserPreferenceHelper.getInstance(MyApplication.getInstance()).addMarketLng(marketDetails.getLogitude() + "");
        getClicksMutableLiveData().setValue(Codes.GO_ORDER_NOW);
    }

    public void getMarketDetail() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketDetailsResponse detailsResponse = (MarketDetailsResponse) response;
                if (detailsResponse.getStatus() == WebServices.SUCCESS) {
                    marketDetails = detailsResponse.getData();
                    workingHoursList = marketDetails.getWorkingTime();
                    accessLoadingBar(View.GONE);
                    notifyChange();
                } else if (detailsResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.MARKET_DETAILS + "place_id=" + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId(), new Object(), MarketDetailsResponse.class);

    }

    public void workingHours() {
        getClicksMutableLiveData().setValue(Codes.GET_WORKING_HOURS);
    }

    public void toBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void registerAsDelegate() {
        getOrderRequest().setPlace_id(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId());
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                RegisterAsDelegateResponse detailsResponse = (RegisterAsDelegateResponse) response;
                if (detailsResponse.getStatus() == WebServices.SUCCESS) {
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    accessLoadingBar(View.GONE);
                } else if (detailsResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.POST, URLS.REGISTERD_AS_DELEGATE, orderRequest, RegisterAsDelegateResponse.class);

    }
}
