package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.MyStoresAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.markets.MarketResponse;

public class MyStoresViewModels extends BaseViewModel {
    MyStoresAdapter storeAdapter;
    public int emptyList;

    public MyStoresViewModels() {
        getMyStores();
    }


    private void getMyStores() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketResponse marketResponse = (MarketResponse) response;
                if (marketResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(marketResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getStoreAdapter().updateData(marketResponse.getData());
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.MY_STORES, new Object(), MarketResponse.class);
    }

    @BindingAdapter({"app:storeAdapter"})
    public static void getStoreBinding(RecyclerView recyclerView, MyStoresAdapter storeAdapter) {
        recyclerView.setAdapter(storeAdapter);
    }


    @Bindable
    public MyStoresAdapter getStoreAdapter() {
        return this.storeAdapter == null ? this.storeAdapter = new MyStoresAdapter() : this.storeAdapter;
    }


    @Bindable
    public int getEmptyList() {
        return emptyList == 0 ? View.VISIBLE : View.GONE;
    }

    public void setEmptyList(int emptyList) {
        notifyChange();
        this.emptyList = emptyList;
    }
    public void goBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

}
