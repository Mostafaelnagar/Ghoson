package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.orders.orderDetail.OrderDetail;
import app.grand.a8oson.models.orders.orderDetail.OrderDetailResponse;

public class OrderDetailViewModels extends BaseViewModel {
    OrderDetail detail;
    public int checkTotal, checkdelegate;

    public OrderDetailViewModels() {
        detail = new OrderDetail();
        getOrderDetail();
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
                    if (detail.getTotalCost() != null)
                        setCheckTotal(1);
                    if (detail.getRepresentativeId() != null)
                        setCheckdelegate(1);
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
        }).requestJsonObject(Request.Method.GET, URLS.ORDER_DETAIL + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getPlaceId() + "&type=" + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getType(), new Object(), OrderDetailResponse.class);

    }

    public void toBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void toChat() {
        UserPreferenceHelper.getInstance(MyApplication.getInstance()).addOrderId(String.valueOf(detail.getId()));
        getClicksMutableLiveData().setValue(Codes.CHAT);
    }

    @BindingAdapter({"delegateImage"})
    public static void delegateImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    @Bindable
    public int getCheckTotal() {
        return checkTotal == 1 ? View.VISIBLE : View.GONE;
    }

    public void setCheckTotal(int checkTotal) {
        notifyChange();
        this.checkTotal = checkTotal;
    }

    @Bindable
    public int getCheckdelegate() {
        return checkdelegate == 1 ? View.VISIBLE : View.GONE;
    }

    public void setCheckdelegate(int checkdelegate) {
        notifyChange();
        this.checkdelegate = checkdelegate;
    }
}
