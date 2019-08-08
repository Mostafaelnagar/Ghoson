package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.MyNotificationAdapter;
import app.grand.a8oson.adapters.MyStoresAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
 import app.grand.a8oson.models.notifications.NotificationsResponse;

public class MyNotificationsViewModels extends BaseViewModel {
    MyNotificationAdapter notificationAdapter;
    public int emptyList;

    public MyNotificationsViewModels() {
        getMyNotfications();
    }


    private void getMyNotfications() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                NotificationsResponse notificationsResponse = (NotificationsResponse) response;
                if (notificationsResponse.getStatus() == WebServices.SUCCESS) {
                    setEmptyList(notificationsResponse.getData().size());
                    accessLoadingBar(View.GONE);
                    getNotificationAdapter().updateData(notificationsResponse.getData());
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), notificationsResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }).requestJsonObject(Request.Method.GET, URLS.NOTIFICATIONS, new Object(), NotificationsResponse.class);
    }

    @BindingAdapter({"app:notificationsAdapter"})
    public static void getNotificationsBinding(RecyclerView recyclerView, MyNotificationAdapter notificationAdapter) {
        recyclerView.setAdapter(notificationAdapter);
    }


    @Bindable
    public MyNotificationAdapter getNotificationAdapter() {
        return this.notificationAdapter == null ? this.notificationAdapter = new MyNotificationAdapter() : this.notificationAdapter;
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
