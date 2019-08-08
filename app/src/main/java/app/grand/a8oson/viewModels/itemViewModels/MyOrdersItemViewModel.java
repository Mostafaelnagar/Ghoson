package app.grand.a8oson.viewModels.itemViewModels;

import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.models.orders.myOrders.MyOrders;
import app.grand.a8oson.models.orders.waitingOrders.MyOrdersData;

public class MyOrdersItemViewModel extends BaseViewModel {

    private MyOrders myOrdersData;

    public MyOrdersItemViewModel(MyOrders myOrdersData) {
        this.myOrdersData = myOrdersData;
    }

    @Bindable
    public MyOrders getMyOrdersData() {
        return myOrdersData;
    }

    @BindingAdapter({"orderImage"})
    public static void orderImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    public void toOrderDetail() {
        getClicksMutableLiveData().setValue(1);
    }
}
