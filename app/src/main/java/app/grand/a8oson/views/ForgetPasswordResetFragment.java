package app.grand.a8oson.views;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.FragmentForgetPasswordResetBinding;
import app.grand.a8oson.viewModels.ForgetPasswordViewModel;

public class ForgetPasswordResetFragment extends BaseFragment {
    FragmentForgetPasswordResetBinding passwordResetBinding;
    ForgetPasswordViewModel passwordViewModel;

    public ForgetPasswordResetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        passwordResetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password_reset, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        passwordResetBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return passwordResetBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.LOGIN_SCREEN) {
                MovementManager.replaceFragment(getContext(), new LoginFragment(), Params.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
