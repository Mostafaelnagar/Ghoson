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
import app.grand.a8oson.databinding.FragmentCodeConfirmationBinding;
import app.grand.a8oson.viewModels.ForgetPasswordViewModel;

public class CodeConfirmationFragment extends BaseFragment {
    FragmentCodeConfirmationBinding confirmationBinding;
    ForgetPasswordViewModel passwordViewModel;


    public CodeConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        confirmationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_code_confirmation, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        confirmationBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return confirmationBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.FORGOT_PASSWORD_SCREEN) {
                MovementManager.replaceFragment(getContext(), new ForgetPasswordResetFragment(), Params.stackValue);
            } else if (result == Codes.LOGIN_SCREEN) {
                MovementManager.replaceFragment(getContext(), new LoginFragment(), Params.stackValue);
            }  else if (result == Codes.ENTER_CODE_SCREEN) {
                MovementManager.replaceFragment(getContext(), new EmailConfirmationFragment(), Params.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

}
