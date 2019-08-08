package app.grand.a8oson.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.FragmentEmailConfirmationBinding;
import app.grand.a8oson.viewModels.ForgetPasswordViewModel;

public class EmailConfirmationFragment extends BaseFragment {
    FragmentEmailConfirmationBinding confirmationBinding;
    ForgetPasswordViewModel passwordViewModel;

    public EmailConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        confirmationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_email_confirmation, container, false);
        passwordViewModel = new ForgetPasswordViewModel();
        confirmationBinding.setForgetPasswordViewModel(passwordViewModel);
        liveDataListeners();
        return confirmationBinding.getRoot();
    }

    private void liveDataListeners() {
        passwordViewModel.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.ENTER_CODE_SCREEN) {
                MovementManager.replaceFragment(getContext(), new CodeConfirmationFragment(), Params.stackValue);
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.BACK) {
                MovementManager.replaceFragment(getContext(), new LoginFragment(), Params.stackValue);
            }
        });
    }
}
