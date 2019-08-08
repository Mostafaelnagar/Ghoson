package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.CategoriesAdapter;
import app.grand.a8oson.adapters.MyStoresAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.markets.MarketResponse;
import app.grand.a8oson.models.markets.categories.CategoriesResponse;

public class StoresViewModels extends BaseViewModel {
    MyStoresAdapter storeAdapter;
    CategoriesAdapter categoriesAdapter;
    public int emptyList;

    public StoresViewModels() {
        getCategories();
    }



    public void getMyStores(double lat, double lng) {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketResponse marketResponse = (MarketResponse) response;
                if (marketResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(marketResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getStoreAdapter().updateData(marketResponse.getData());
                } else if (marketResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.ALL_Markets + "latitude=" + lat + "&longitude=" + lng, new Object(), MarketResponse.class);
    }

    public void getBestSellingMarkets(double lat, double lng) {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketResponse marketResponse = (MarketResponse) response;
                if (marketResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(marketResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getStoreAdapter().updateData(marketResponse.getData());
                } else if (marketResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.BEST_SELLING + "latitude=" + lat + "&longitude=" + lng, new Object(), MarketResponse.class);
    }

    public void getNearestMarkets(double lat, double lng) {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketResponse marketResponse = (MarketResponse) response;
                if (marketResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(marketResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getStoreAdapter().updateData(marketResponse.getData());
                } else if (marketResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.NEAREST_MARKETS + "latitude=" + lat + "&longitude=" + lng, new Object(), MarketResponse.class);
    }

    public void getCategories() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                CategoriesResponse categoriesResponse = (CategoriesResponse) response;
                if (categoriesResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(categoriesResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getCategoriesAdapter().updateData(categoriesResponse.getData());
                } else if (categoriesResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), categoriesResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), categoriesResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.CATEGORIES, new Object(), CategoriesResponse.class);
    }

    public void getStoresByCategory(int cat_id, double lat, double lng) {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                MarketResponse marketResponse = (MarketResponse) response;
                if (marketResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(marketResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getStoreAdapter().updateData(marketResponse.getData());
                } else if (marketResponse.getStatus() == 405) {
                    getClicksMutableLiveData().setValue(Codes.TOKEN_EXPIRED);
                    Toast.makeText(MyApplication.getInstance().getApplicationContext(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();

                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), marketResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.GET_STORES_BY_CATEGORY + "category_id=" + cat_id + "&latitude=" + lat + "&longitude=" + lng, new Object(), MarketResponse.class);
    }


    @BindingAdapter({"app:storeAdapter"})
    public static void getStoreBinding(RecyclerView recyclerView, MyStoresAdapter storeAdapter) {
        recyclerView.setAdapter(storeAdapter);
    }

    @BindingAdapter({"app:categoryAdapter"})
    public static void getCategoriesBinding(RecyclerView recyclerView, CategoriesAdapter categoriesAdapter) {
        recyclerView.setAdapter(categoriesAdapter);
    }


    @Bindable
    public MyStoresAdapter getStoreAdapter() {
        return this.storeAdapter == null ? this.storeAdapter = new MyStoresAdapter() : this.storeAdapter;
    }

    @Bindable
    public CategoriesAdapter getCategoriesAdapter() {
        return this.categoriesAdapter == null ? this.categoriesAdapter = new CategoriesAdapter() : this.categoriesAdapter;
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
