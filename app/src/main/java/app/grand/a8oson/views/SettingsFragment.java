package app.grand.a8oson.views;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentSettingsBinding;
import app.grand.a8oson.viewModels.SettingsViewModels;

public class SettingsFragment extends BaseFragment {
    FragmentSettingsBinding settingsBinding;
    SettingsViewModels settingsViewModels;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        settingsViewModels = new SettingsViewModels();
        settingsBinding.setSettingsViewModels(settingsViewModels);
        liveDataListeners();
        return settingsBinding.getRoot();
    }

    private void liveDataListeners() {
        settingsViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.MY_STORE) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == Codes.EDIT_PROFILE) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == Codes.POLICY) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == Codes.TERMS) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == Codes.RATE_APP) {
                MovementManager.startActivity(getActivity(), result);
            } else if (result == Codes.HOW_TO_WORK) {
                MovementManager.startActivity(getActivity(), result);
            }  else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            }
        });
    }


}
