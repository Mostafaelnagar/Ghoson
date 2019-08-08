package app.grand.a8oson.viewModels.itemViewModels;


import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import app.grand.a8oson.base.BaseViewModel;
import app.grand.a8oson.models.markets.categories.Categories;

public class CategoriesItemViewModel extends BaseViewModel {
    private Categories categories;

    public CategoriesItemViewModel(Categories categories) {
        this.categories = categories;
    }

    @Bindable
    public Categories getCategories() {
        return categories;
    }

    public void filterByCategory() {
//        itemClickData.setValue(1);
        getClicksMutableLiveData().setValue(1);
    }
}
