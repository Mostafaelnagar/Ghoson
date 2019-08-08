package app.grand.a8oson.viewModels;


import android.util.Log;
import android.view.View;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.policies.PoliciesData;
import app.grand.a8oson.models.policies.PoliciesResponse;

public class PolicesViewModels extends BaseViewModel {
    public PoliciesData policiesData;

    public PolicesViewModels() {
        policiesData = new PoliciesData();
        getAbout();
    }

    //
    @Bindable
    public PoliciesData getPoliciesData() {
        return policiesData;
    }

    public void setPoliciesData(PoliciesData policiesData) {
        this.policiesData = policiesData;
    }

    public void getAbout() {

        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                accessLoadingBar(View.GONE);
                PoliciesResponse userResponse = (PoliciesResponse) response;
                if (userResponse.getStatus() == WebServices.SUCCESS) {
                    setPoliciesData(userResponse.getData());
                    Log.i("onRequestSuccess", "onRequestSuccess: " + userResponse.getData());
                } else
                    Log.i("onRequestSuccess", "onRequestSuccess: error " + userResponse.getMsg());
                notifyChange();
            }
        }).requestJsonObject(Request.Method.GET, URLS.POLICIES, policiesData, PoliciesResponse.class);

    }

    public void goBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }
}
