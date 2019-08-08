package app.grand.a8oson.adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.R;
import app.grand.a8oson.base.MovementManager;
import app.grand.a8oson.base.UserPreferenceHelper;
import app.grand.a8oson.base.constantsutils.Codes;
import app.grand.a8oson.base.constantsutils.Params;
import app.grand.a8oson.databinding.ChatItemBinding;
import app.grand.a8oson.databinding.MyOrdersItemBinding;
import app.grand.a8oson.models.chat.ChatData;
import app.grand.a8oson.models.chat.MessagesItem;
import app.grand.a8oson.models.orders.myOrders.MyOrders;
import app.grand.a8oson.viewModels.itemViewModels.ChatItemViewModel;
import app.grand.a8oson.viewModels.itemViewModels.MyOrdersItemViewModel;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    public List<MessagesItem> chatData;
    Context context;

    public ChatAdapter() {
        chatData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessagesItem dataModel = chatData.get(position);
        ChatItemViewModel homeItemViewModels = new ChatItemViewModel(dataModel);
        if (dataModel.getType()==Params.SendType) {
            holder.itemBinding.linChat.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.itemBinding.chatMessage.setBackground(context.getResources().getDrawable(R.drawable.corner_view));
            holder.itemBinding.chatMessage.setGravity(Gravity.RIGHT);
            holder.itemBinding.chatMessage.setPadding(10, 10, 10, 10);

        } else {
            holder.itemBinding.chatMessage.setBackground(context.getResources().getDrawable(R.drawable.corner_view_primary));
            holder.itemBinding.linChat.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            holder.itemBinding.chatMessage.setGravity(Gravity.LEFT);
            holder.itemBinding.chatMessage.setPadding(12, 10, 12, 10);

        }
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.chatData.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<MessagesItem> data) {

        this.chatData.clear();

        this.chatData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ChatItemBinding itemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemBinding == null) {
                itemBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemBinding != null) {
                itemBinding.unbind();
            }
        }

        void setViewModel(ChatItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setChatItemViewModels(itemViewModels);
            }
        }
    }
}
