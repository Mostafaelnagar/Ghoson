package app.grand.a8oson.viewModels;


import app.grand.a8oson.base.BaseViewModel;

public class HomeViewModels extends BaseViewModel {
    //    HomeAdapter homeAdapter;
    public int emptyList;

    public HomeViewModels() {


    }


//    private void getLastTrips() {
//        accessLoadingBar(View.VISIBLE);
//        new ConnectionHelper(new ConnectionListener() {
//            @Override
//            public void onRequestSuccess(Object response) {
//                AllTripsResponse tripsResponse = (AllTripsResponse) response;
//                setEmptyList(tripsResponse.getData().size());
//                accessLoadingBar(View.GONE);
//                getHomeAdapter().updateData(tripsResponse.getData());
//            }
//        }).requestJsonObject(Request.Method.GET, URLS.LAST_TRIP, new Object(), AllTripsResponse.class);
//    }

//    @BindingAdapter({"app:homeAdapter"})
//    public static void getTripsBinding(RecyclerView recyclerView, HomeAdapter homeAdapter) {
//        recyclerView.setAdapter(homeAdapter);
//    }
//
//
//    @Bindable
//    public HomeAdapter getHomeAdapter() {
//        return this.homeAdapter == null ? this.homeAdapter = new HomeAdapter() : this.homeAdapter;
//    }
//
//
//    private void getAllTrips() {
//        accessLoadingBar(View.VISIBLE);
//        new ConnectionHelper(new ConnectionListener() {
//            @Override
//            public void onRequestSuccess(Object response) {
//                AllTripsResponse tripsResponse = (AllTripsResponse) response;
//                setEmptyList(tripsResponse.getData().size());
//                accessLoadingBar(View.GONE);
//                getHomeAdapter().updateData(tripsResponse.getData());
//            }
//        }).requestJsonObject(Request.Method.GET, URLS.ALL_TRIPS, new Object(), AllTripsResponse.class);
//    }

//    public int getEmptyList() {
//        return emptyList == 0 ? View.VISIBLE : View.GONE;
//    }
//
//    public void setEmptyList(int emptyList) {
//        this.emptyList = emptyList;
//    }
}
