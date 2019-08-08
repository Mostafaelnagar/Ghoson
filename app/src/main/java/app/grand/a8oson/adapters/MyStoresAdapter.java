package app.grand.a8oson.adapters;

import android.content.Context;
import android.util.Log;
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
import app.grand.a8oson.databinding.StoreItemBinding;
import app.grand.a8oson.models.markets.Markets;
import app.grand.a8oson.viewModels.itemViewModels.MyStoreItemViewModel;

public class MyStoresAdapter extends RecyclerView.Adapter<MyStoresAdapter.ViewHolder> {
    public List<Markets> marketsList;
    Context context;

    public MyStoresAdapter() {
        marketsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Markets dataModel = marketsList.get(position);
        MyStoreItemViewModel homeItemViewModels = new MyStoreItemViewModel(dataModel);
        homeItemViewModels.getClicksMutableLiveData().observe((LifecycleOwner) context, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                UserPreferenceHelper.getInstance(context).addPlaceId(dataModel.getPlaceId());
                UserPreferenceHelper.getInstance(context).addMarketImage(dataModel.getPhoto());
                UserPreferenceHelper.getInstance(context).addMarketName(dataModel.getName());

                MovementManager.startActivity(context, Codes.STORE_DETAIL);
            }
        });
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.marketsList.size();
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

    public void updateData(@Nullable List<Markets> data) {

        this.marketsList.clear();

        this.marketsList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        StoreItemBinding itemBinding;

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

        void setViewModel(MyStoreItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setMyStoresItemViewModels(itemViewModels);
            }
        }
    }
}
