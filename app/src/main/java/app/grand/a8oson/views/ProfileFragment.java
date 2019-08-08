package app.grand.a8oson.views;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.databinding.FragmentProfileBinding;
import app.grand.a8oson.viewModels.ProfileViewModels;


public class ProfileFragment extends BaseFragment {
    FragmentProfileBinding fragmentProfileBinding;
    ProfileViewModels profileViewModels;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        profileViewModels = new ProfileViewModels();
        profileViewModels.checkUserIsDelegate();
        fragmentProfileBinding.setProfileViewModels(profileViewModels);
        liveDataListeners();

        return fragmentProfileBinding.getRoot();
    }

    private void liveDataListeners() {
        profileViewModels.getClicksMutableLiveData().observe(this, result -> {

            if (result == Codes.PROFILE_GO_SETTINGS) {
                MovementManager.startActivity(getContext(), Codes.PROFILE_GO_SETTINGS);
            } else if (result == Codes.LOG_OUT) {
                loggout();
            } else if (result == Codes.TOKEN_EXPIRED) {
                MovementManager.startMainActivity(getActivity(), Codes.LOGIN_SCREEN);
                UserPreferenceHelper.getInstance(getActivity()).loggout();

            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }

        });
    }

    public void loggout() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logout_item);
        TextView negative_Button = dialog.findViewById(R.id.reject);
        TextView postive_Button = dialog.findViewById(R.id.accept);
        negative_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        postive_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovementManager.startMainActivity(getActivity(), Codes.LOGIN_SCREEN);
                UserPreferenceHelper.getInstance(getActivity()).loggout();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentProfileBinding.prTvName.setText(UserPreferenceHelper.getInstance(getActivity()).getUserData().getName());
        if (UserPreferenceHelper.getInstance(getActivity()).getUserData().getImage() != null)
            ConnectionHelper.loadImage(fragmentProfileBinding.userProfileImage,
                    UserPreferenceHelper.getInstance(getActivity()).getUserData().getImage().getName());
    }
}
