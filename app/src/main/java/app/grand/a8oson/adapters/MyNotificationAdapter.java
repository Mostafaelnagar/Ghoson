package app.grand.a8oson.adapters;

import android.content.Context;
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
import app.grand.a8oson.databinding.MyNotificationsItemBinding;
import app.grand.a8oson.models.notifications.NotificationsData;
import app.grand.a8oson.viewModels.itemViewModels.MyNotificationsItemViewModel;

public class MyNotificationAdapter extends RecyclerView.Adapter<MyNotificationAdapter.ViewHolder> {
    public List<NotificationsData> notificationsData;
    Context context;

    public MyNotificationAdapter() {
        notificationsData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_notifications_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NotificationsData dataModel = notificationsData.get(position);
        MyNotificationsItemViewModel homeItemViewModels = new MyNotificationsItemViewModel(dataModel);
        homeItemViewModels.getClicksMutableLiveData().observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                UserPreferenceHelper.getInstance(context).addPlaceId(dataModel.getOrder_Id());
                if (integer == Codes.OFFER_DETAIL) {
                    MovementManager.startActivity(context, Codes.OFFER_DETAIL);
                }
            }
        });
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.notificationsData.size();
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

    public void updateData(@Nullable List<NotificationsData> data) {

        this.notificationsData.clear();

        this.notificationsData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyNotificationsItemBinding itemBinding;

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

        void setViewModel(MyNotificationsItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setNotificationsItemViewModel(itemViewModels);
            }
        }
    }
}
