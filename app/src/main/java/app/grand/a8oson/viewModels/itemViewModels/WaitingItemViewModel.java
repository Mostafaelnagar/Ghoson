package app.grand.a8oson.viewModels.itemViewModels;

import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.models.markets.Markets;
import app.grand.a8oson.models.orders.waitingOrders.MyOrdersData;

public class WaitingItemViewModel extends BaseViewModel {

    private MyOrdersData myOrdersData;

    public WaitingItemViewModel(MyOrdersData myOrdersData) {
        this.myOrdersData = myOrdersData;
    }

    @Bindable
    public MyOrdersData getMyOrdersData() {
        return myOrdersData;
    }

    @BindingAdapter({"orderImage"})
    public static void orderImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    public void marketDetails() {
        getClicksMutableLiveData().setValue(1);
    }
}
