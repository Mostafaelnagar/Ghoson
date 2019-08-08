package app.grand.a8oson.viewModels;

import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.adapters.ChatAdapter;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.WebServices;
import app.grand.a8oson.base.volleyutils.ConnectionHelper;
import app.grand.a8oson.base.volleyutils.ConnectionListener;
import app.grand.a8oson.base.volleyutils.MyApplication;
import app.grand.a8oson.base.volleyutils.URLS;
import app.grand.a8oson.models.chat.ChatRequest;
import app.grand.a8oson.models.chat.ChatResponse;

public class ChatViewModels extends BaseViewModel {
    ChatAdapter chatAdapter;
    ChatRequest chatRequest;
    public String chatName;
    public int checkDecision = 1, sendOrderBill = 1, sendOrderImage, orderComplete, orderStatus;
    public String lat, lng;

    public ChatViewModels() {
        getMessages();
    }

    @Bindable
    public ChatAdapter getChatAdapter() {
        return this.chatAdapter == null ? this.chatAdapter = new ChatAdapter() : this.chatAdapter;
    }

    public ChatRequest getChatRequest() {
        return chatRequest;
    }

    @BindingAdapter({"app:chatAdapter"})
    public static void getChatBinding(RecyclerView recyclerView, ChatAdapter chatAdapter) {
        recyclerView.setAdapter(chatAdapter);
        if (chatAdapter.chatData != null) {
            recyclerView.scrollToPosition(chatAdapter.chatData.size() - 1);
        }
    }

    public void getMessages() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                ChatResponse chatResponse = (ChatResponse) response;
                if (chatResponse.getStatus() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    if (chatResponse.getData().getMessages().get(0).getFromUser().getId() == UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getId())
                        setSendOrderBill(1);
                    else
                        setOrderStatus(chatResponse.getData().getButtonStatus());
                    setCheckDecision(chatResponse.getData().getOrderStatus());

                    lat = chatResponse.getData().getLatitude();
                    lng = chatResponse.getData().getLongitude();
                    getChatAdapter().updateData(chatResponse.getData().getMessages());
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), chatResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }


        }).requestJsonObject(Request.Method.GET, URLS.GET_MESSAGES + "user_id=" + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getId() + "&order_id=" + UserPreferenceHelper.getInstance(MyApplication.getInstance()).getOrderId(), new Object(), ChatResponse.class);
    }

    public void sendMessage() {
        accessLoadingBar(View.VISIBLE);
        new ConnectionHelper(new ConnectionListener() {
            @Override
            public void onRequestSuccess(Object response) {
                ChatResponse chatResponse = (ChatResponse) response;
                if (chatResponse.getStatus() == WebServices.SUCCESS) {
                    accessLoadingBar(View.GONE);
                    if (chatResponse.getData().getMessages().get(0).getFromUser().getId() == UserPreferenceHelper.getInstance(MyApplication.getInstance()).getUserData().getId())
                        setSendOrderBill(1);
                    else
                        setOrderStatus(chatResponse.getData().getButtonStatus());
                    setCheckDecision(chatResponse.getData().getOrderStatus());

                    lat = chatResponse.getData().getLatitude();
                    lng = chatResponse.getData().getLongitude();
                    getChatAdapter().updateData(chatResponse.getData().getMessages());
                } else {
                    accessLoadingBar(View.GONE);
                    Toast.makeText(MyApplication.getInstance(), chatResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }


        }).requestJsonObject(Request.Method.POST, URLS.SEND_MESSAGE, new Object(), ChatResponse.class);

    }

    public void orderNavigation() {
        getClicksMutableLiveData().setValue(Codes.ORDER_NAVIGATE);
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        notifyChange();
        this.chatName = chatName;
    }

    public int getCheckDecision() {
        return checkDecision != 1 ? View.VISIBLE : View.GONE;
    }

    public void setCheckDecision(int checkDecision) {
        notifyChange();
        this.checkDecision = checkDecision;
    }

    public int getOrderStatus() {
        if (orderStatus == 0) {
            sendOrderBill = orderStatus;

        } else if (orderStatus == 1) {
            sendOrderImage = orderStatus;
        } else if (orderStatus == 2) {
            orderComplete = orderStatus;
        }
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        notifyChange();
        this.orderStatus = orderStatus;
    }

    public int getSendOrderBill() {

        return sendOrderBill == 0 ? View.VISIBLE : View.GONE;
    }

    public void setSendOrderBill(int sendOrderBill) {
        notifyChange();
        this.sendOrderBill = sendOrderBill;
    }

    public int getSendOrderImage() {
        return sendOrderImage == 1 ? View.VISIBLE : View.GONE;
    }

    public void setSendOrderImage(int sendOrderImage) {
        notifyChange();
        this.sendOrderImage = sendOrderImage;
    }

    public int getOrderComplete() {
        return orderComplete == 2 ? View.VISIBLE : View.GONE;
    }

    public void setOrderComplete(int orderComplete) {
        notifyChange();
        this.orderComplete = orderComplete;
    }
}
