package app.grand.a8oson.views;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.databinding.FragmentAboutBinding;
import app.grand.a8oson.viewModels.PolicesViewModels;

public class AboutFragment extends BaseFragment {

    FragmentAboutBinding fragmentAbout;
    PolicesViewModels policesViewModels;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAbout = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);

        policesViewModels = new PolicesViewModels();
        fragmentAbout.setAboutViewModels(policesViewModels);
        liveDataListeners();
        return fragmentAbout.getRoot();
    }

    private void liveDataListeners() {
        policesViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.BACK) {
                ((Activity) getActivity()).finish();
            }
        });
    }

}
