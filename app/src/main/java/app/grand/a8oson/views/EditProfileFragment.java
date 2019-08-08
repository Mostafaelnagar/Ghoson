package app.grand.a8oson.views;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.filesutils.FileOperations;
import app.grand.a8oson.base.filesutils.VolleyFileObject;
import app.grand.a8oson.databinding.FragmentEditProfileBinding;
import app.grand.a8oson.viewModels.ProfileViewModels;
import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;

public class EditProfileFragment extends BaseFragment {

    FragmentEditProfileBinding fragmentProfileBinding;
    ProfileViewModels profileViewModels;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        profileViewModels = new ProfileViewModels();
        fragmentProfileBinding.setProfileViewModels(profileViewModels);
        profileViewModels.setUseName(UserPreferenceHelper.getInstance(getActivity()).getUserData().getName());
        profileViewModels.getProfileData();
        liveDataListeners();
        fragmentProfileBinding.switch1.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                if (position == 1)
                    profileViewModels.getUserData().setGender(position);
                else
                    profileViewModels.getUserData().setGender(2);
            }
        });
        return fragmentProfileBinding.getRoot();

    }

    private void liveDataListeners() {
        profileViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            } else if (result == Codes.SELECT_PROFILE_IMAGE) {
                selectProfilePhoto();
            } else if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else {
                if (result == 1)
                    fragmentProfileBinding.switch1.setCheckedTogglePosition(result);
                else
                    fragmentProfileBinding.switch1.setCheckedTogglePosition(0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Codes.FILE_TYPE_IMAGE) {
            if (data != null) {
                VolleyFileObject volleyFileObject = FileOperations.getVolleyFileObject(getActivity(), data, WebServices.IMAGE, requestCode);
                fragmentProfileBinding.userProfileImage.setImageBitmap(Objects.requireNonNull(volleyFileObject).getCompressObject().getImage());
                profileViewModels.getVolleyFileObjects().add(volleyFileObject);
            }
        }
    }

    private void selectProfilePhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                // start picker to get image for cropping and then use the image in cropping activity
                FileOperations.pickImage(getActivity());
            }
        } else {
            FileOperations.pickImage(getActivity());

        }
    }
}
