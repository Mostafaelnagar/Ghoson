package app.grand.a8oson.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import app.grand.a8oson.databinding.MyOrdersItemBinding;
import app.grand.a8oson.models.orders.myOrders.MyOrders;
import app.grand.a8oson.viewModels.itemViewModels.MyOrdersItemViewModel;
import app.grand.a8oson.viewModels.itemViewModels.WaitingItemViewModel;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {
    public List<MyOrders> orderData;
    Context context;
    public int type;

    public MyOrdersAdapter() {
        orderData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyOrders dataModel = orderData.get(position);
        MyOrdersItemViewModel homeItemViewModels = new MyOrdersItemViewModel(dataModel);
        homeItemViewModels.getClicksMutableLiveData().observe(((LifecycleOwner) context), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (dataModel.getStatus() == 1 || dataModel.getStatus() == 2) {
                    UserPreferenceHelper.getInstance(context).addPlaceId(dataModel.getId());
                    Intent intent = new Intent();
                    Log.i("onChanged", "onChanged: "+type);
                    intent.putExtra(Params.CHAT_TYPE, type);
                    MovementManager.startActivityWithBundle(context, Codes.ORDER_DETAILS, intent);
                }
            }
        });
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.orderData.size();
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

    public void updateData(@Nullable List<MyOrders> data) {

        this.orderData.clear();

        this.orderData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyOrdersItemBinding itemBinding;

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

        void setViewModel(MyOrdersItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setOrderItemViewModels(itemViewModels);
            }
        }
    }
}
