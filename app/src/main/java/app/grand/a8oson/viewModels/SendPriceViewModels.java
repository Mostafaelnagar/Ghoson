package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.orders.orderDetail.OrderDetail;
import app.grand.a8oson.models.orders.orderDetail.OrderDetailResponse;
import app.grand.a8oson.models.sendOffer.SendOfferRequest;
import app.grand.a8oson.models.sendOffer.SendOfferResponse;

public class SendPriceViewModels extends BaseViewModel {
    OrderDetail detail;
    public int loading;
    SendOfferRequest offerRequest;

    public SendPriceViewModels() {
        offerRequest = new SendOfferRequest();
        detail = new OrderDetail();
        getOrderDetail();
    }

    public SendOfferRequest getOfferRequest() {
        return offerRequest;
    }

    public OrderDetail getDetail() {
        return detail;
    }

    public void getOrderDetail() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                OrderDetailResponse detailsResponse = (OrderDetailResponse) response;
                if (detailsResponse.getStatus() == WebServices.SUCCESS) {
                    detail = detailsResponse.getData();
                    setLoading(1);
                    notifyChange();
                    accessLoadingBar(View.GONE);
                } else if (detailsResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.ORDER_DETAIL + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId(), new Object(), OrderDetailResponse.class);

    }

    public void toBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void sendOffer() {
        getOfferRequest().setOrderId(UserPreferenceHelper.getInstance(MyApplication.getInstance()).getOrderId());
        if (getOfferRequest().sendOfferValid()) {
            accessLoadingBar(View.VISIBLE);
            new ConnectionHelper(new ConnectionListener() {
                @Override
                public void onRequestSuccess(Object response) {
                    SendOfferResponse detailsResponse = (SendOfferResponse) response;
                    if (detailsResponse.getStatus() == WebServices.SUCCESS) {
                        accessLoadingBar(View.GONE);
                        getClicksMutableLiveData().setValue(Codes.CHAT);
                    } else if (detailsResponse.getStatus() == 405) {
                        getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                        Toast.makeText(MyApplication.getInstance().getApplicationContext(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        accessLoadingBar(View.GONE);
                        Toast.makeText(MyApplication.getInstance(), detailsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).requestJsonObject(Request.Method.POST, URLS.SEND_OFFER, offerRequest, SendOfferResponse.class);
        } else
            Toast.makeText(MyApplication.getInstance(), MyApplication.getInstance().getResources().getString(R.string.emptyData), Toast.LENGTH_SHORT).show();
    }

    @Bindable
    public int getLoading() {
        return loading == 1 ? View.VISIBLE : View.GONE;
    }

    public void setLoading(int loading) {
        notifyChange();
        this.loading = loading;
    }
}
