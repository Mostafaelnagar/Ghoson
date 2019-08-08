package app.grand.a8oson.viewModels.itemViewModels;


import androidx.databinding.Bindable;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.models.notifications.NotificationsData;

public class MyNotificationsItemViewModel extends BaseViewModel {

    private NotificationsData notificationsData;

    public MyNotificationsItemViewModel(NotificationsData notificationsData) {
        this.notificationsData = notificationsData;
    }

    @Bindable
    public NotificationsData getNotificationsData() {
        return notificationsData;
    }

    public void orderDetails() {
        int type = notificationsData.getType();
        if (type == 0) {

        } else if (type == 1) {
            getClicksMutableLiveData().setValue(Codes.OFFER_DETAIL);
        } else if (type == 2) {
        } else if (type == 3) {

        } else if (type == 4) {

        }
    }
}
