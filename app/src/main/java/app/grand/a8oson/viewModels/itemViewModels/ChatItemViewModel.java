package app.grand.a8oson.viewModels.itemViewModels;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import app.grand.a8oson.BR;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.models.chat.MessagesItem;

public class ChatItemViewModel extends BaseViewModel {

    private MessagesItem chatData;
    public String userImage;

    public ChatItemViewModel(MessagesItem chatData) {
        this.chatData = chatData;
//        if (getChatData().getType() ==Params.SendType){
//            if (getChatData().getToUser().getImage() != null) {
//                setUserImage(getChatData().getToUser().getImage().getName());
//                Log.i("ChatItemViewModel", "ChatItemViewModel: ");
//            }
//        } else{
//            if (getChatData().getFromUser().getImage() != null) {
//                setUserImage(getChatData().getToUser().getImage().getName());
//            }
//        }
        if (getChatData().getFromUser().getImage() != null)
            userImage = getChatData().getFromUser().getImage().getName();
    }

    @Bindable
    public MessagesItem getChatData() {
        return chatData;
    }

    @BindingAdapter({"userImage"})
    public static void userImage(ImageView view, String imagePath) {
        ConnectionHelper.loadImage(view, imagePath);
    }

    @Bindable
    public String getUserImage() {
        Log.i("getUser_image", "getUser_image: " + getChatData().getFromUser().getImage());
        return userImage;
    }

    public void setUserImage(String userImage) {
        notifyChange();
        this.userImage = userImage;
    }
}
