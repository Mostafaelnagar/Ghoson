package app.grand.a8oson.base;


import android.app.Activity;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import app.grand.a8oson.base.volleyutils.MyApplication;

public class BaseViewModel extends BaseObservable {

    private String returnedMessage;
    private MutableLiveData<Integer> clicksMutableLiveData;


    public BaseViewModel() {

    }

    public void accessLoadingBar(int visiablity) {
        getClicksMutableLiveData().setValue(visiablity);
    }

    public void goBack() {
        ((Activity) MyApplication.getInstance().getApplicationContext()).finish();
    }


    public MutableLiveData<Integer> getClicksMutableLiveData() {
        if (clicksMutableLiveData == null) clicksMutableLiveData = new MutableLiveData<>();
        return clicksMutableLiveData;
    }

    public String getReturnedMessage() {
        return returnedMessage;
    }

    public void setClicksMutableLiveData(MutableLiveData<Integer> clicksMutableLiveData) {
        this.clicksMutableLiveData = clicksMutableLiveData;
    }

    public void setReturnedMessage(String returnedMessage) {
        this.returnedMessage = returnedMessage;
    }
}
