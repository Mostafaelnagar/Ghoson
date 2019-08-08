package app.grand.a8oson.viewModels.itemViewModels;

import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.models.markets.Markets;

public class MyStoreItemViewModel extends BaseViewModel {

    private Markets markets;

    public MyStoreItemViewModel(Markets markets) {
        this.markets = markets;
    }

    @Bindable
    public Markets getMarkets() {
        return markets;
    }

    @BindingAdapter({"storeImage"})
    public static void storeImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    public void marketDetails() {
        getClicksMutableLiveData().setValue(1);
    }
}
