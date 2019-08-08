package app.grand.a8oson.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.databinding.FragmentNotifcationsBinding;
import app.grand.a8oson.viewModels.MyNotificationsViewModels;

public class NotifcationsFragment extends BaseFragment {

    FragmentNotifcationsBinding myNotifcationsBinding;
    MyNotificationsViewModels notificationsViewModels;

    public NotifcationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myNotifcationsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifcations, container, false);
        notificationsViewModels = new MyNotificationsViewModels();
        myNotifcationsBinding.setMyNotificationsViewModels(notificationsViewModels);
        liveDataListeners();
        return myNotifcationsBinding.getRoot();
    }

    private void liveDataListeners() {
        notificationsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }


}
