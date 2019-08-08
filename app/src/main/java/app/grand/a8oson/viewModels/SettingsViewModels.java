package app.grand.a8oson.viewModels;

import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;

public class SettingsViewModels extends BaseViewModel {
    public SettingsViewModels() {
    }

    public void goBack() {
        getClicksMutableLiveData().setValue(Codes.BACK);
    }

    public void goMyStore() {
         getClicksMutableLiveData().setValue(Codes.MY_STORE);
    }

    public void goEditProfile() {

        getClicksMutableLiveData().setValue(Codes.EDIT_PROFILE);
    }

    public void goPolicy() {
        getClicksMutableLiveData().setValue(Codes.POLICY);
    }

    public void goTerms() {
        getClicksMutableLiveData().setValue(Codes.TERMS);
    }

    public void goHowToWork() {
        //todo how to work
        getClicksMutableLiveData().setValue(Codes.HOW_TO_WORK);
    }



    public void goRateApp() {
        //todo rate App
        getClicksMutableLiveData().setValue(Codes.RATE_APP);
    }


}
