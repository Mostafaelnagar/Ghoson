package app.grand.a8oson.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentStoreWaitingOrdersBinding;
import app.grand.a8oson.viewModels.WaitingOrdersViewModels;

public class StoreWaitingOrdersFragment extends BaseFragment {
    FragmentStoreWaitingOrdersBinding waitingOrdersBinding;
    WaitingOrdersViewModels waitingOrdersViewModels;

    public StoreWaitingOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        waitingOrdersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_waiting_orders, container, false);
        waitingOrdersViewModels = new WaitingOrdersViewModels();
        waitingOrdersBinding.setWaitingOrdersViewModel(waitingOrdersViewModels);
        liveDataListeners();

        return waitingOrdersBinding.getRoot();
    }

    private void liveDataListeners() {
        waitingOrdersViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.TOKEN_EXPIRED) {
                MovementManager.startMainActivity(getActivity(), Codes.LOGIN_SCREEN);
                UserPreferenceHelper.getInstance(getActivity()).loggout();
            }
        });
    }
}
