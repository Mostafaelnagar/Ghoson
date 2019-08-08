package app.grand.a8oson.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import app.grand.a8oson.R;
import app.grand.a8oson.databinding.CategoryItemBinding;
import app.grand.a8oson.models.markets.categories.Categories;
import app.grand.a8oson.viewModels.itemViewModels.CategoriesItemViewModel;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    public List<Categories> categoriesList;
    Context context;
    public MutableLiveData<Integer> categoryIdLiveData;

    public CategoriesAdapter() {
        categoriesList = new ArrayList<>();
        categoryIdLiveData = new MutableLiveData<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Categories dataModel = categoriesList.get(position);
        CategoriesItemViewModel homeItemViewModels = new CategoriesItemViewModel(dataModel);
        homeItemViewModels.getClicksMutableLiveData().observe((LifecycleOwner) context, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        categoryIdLiveData.setValue(dataModel.getId());
                    }
                }
        );
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.categoriesList.size();
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

    public void updateData(@Nullable List<Categories> data) {

        this.categoriesList.clear();

        this.categoriesList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding itemBinding;

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

        void setViewModel(CategoriesItemViewModel itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setCategoriesItemViewModels(itemViewModels);
            }
        }
    }
}
