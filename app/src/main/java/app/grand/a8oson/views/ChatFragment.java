package app.grand.a8oson.views;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import app.grand.a8oson.R;
import app.grand.a8oson.base.BaseFragment;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.FragmentChatBinding;
import app.grand.a8oson.viewModels.ChatViewModels;

public class ChatFragment extends BaseFragment {
    FragmentChatBinding chatBinding;
    ChatViewModels chatViewModels;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);
        Bundle bundle = this.getArguments();
        chatViewModels = new ChatViewModels();
        chatBinding.setChatViewModels(chatViewModels);
        liveDataListeners();
        if (bundle != null) {
             chatViewModels.setChatName(bundle.getString(Params.CHAT_NAME));
        }
        return chatBinding.getRoot();
    }

    private void liveDataListeners() {
        chatViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            } else if (result == Codes.ORDER_NAVIGATE) {
                MovementManager.mapNavigate(chatViewModels.lat, chatViewModels.lng, getActivity());
            }
        });
    }


}
