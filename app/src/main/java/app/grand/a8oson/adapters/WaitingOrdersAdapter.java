package app.grand.a8oson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.R;
import app.grand.a8oson.databinding.MyOrdersItemBinding;
import app.grand.a8oson.databinding.ShopWaitingOrdersItemBinding;
import app.grand.a8oson.models.orders.waitingOrders.MyOrdersData;
import app.grand.a8oson.viewModels.itemViewModels.WaitingItemViewModel;

public class WaitingOrdersAdapter extends RecyclerView.Adapter<WaitingOrdersAdapter.ViewHolder> {
    public List<MyOrdersData> orderData;
    Context context;

    public WaitingOrdersAdapter() {
        orderData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_waiting_orders_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyOrdersData dataModel = orderData.get(position);
        WaitingItemViewModel homeItemViewModels = new WaitingItemViewModel(dataModel);

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

    public void updateData(@Nullable List<MyOrdersData> data) {

        this.orderData.clear();

        this.orderData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShopWaitingOrdersItemBinding itemBinding;

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

        void setViewModel(WaitingItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setOrderWaitingItemViewModels(itemViewModels);
            }
        }
    }
}
