package com.emrekose.famula.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.emrekose.famula.R;
import com.emrekose.famula.common.BaseOnlyActivity;
import com.emrekose.famula.databinding.ActivitySearchBinding;
import com.emrekose.famula.model.restaurant.search.Restaurant;
import com.emrekose.famula.ui.detail.RestaurantDetailActivity;
import com.emrekose.famula.util.Constants;
import com.emrekose.famula.util.EntityType;

public class SearchActivity extends BaseOnlyActivity<ActivitySearchBinding, SearchViewModel> implements SearchResultsCallback {

    private SearchResultsAdapter adapter;
    private String query;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    public Class<SearchViewModel> getViewModel() {
        return SearchViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            query = getIntent().getExtras().getString(Constants.SEARCH_QUERY_KEY);
        }

        setupToolbar(query);

        adapter = new SearchResultsAdapter(this);
        dataBinding.searchRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.searchRecyclerview.setAdapter(adapter);

       // TODO: 1.08.2018 entityid and entitytype will take from sharedpref
        viewModel.getRestaurants(query, 59, EntityType.CITY.getType()).observe(this, restaurants -> {
            dataBinding.setListSize(restaurants.size());
            if (restaurants.size() == 0) {
                dataBinding.noResultsLl.setVisibility(View.VISIBLE);
                dataBinding.searchProgressbar.setVisibility(View.GONE);
            } else if (restaurants.size() > 0){
                dataBinding.noResultsLl.setVisibility(View.GONE);
                dataBinding.searchProgressbar.setVisibility(View.GONE);
            }
            adapter.submitList(restaurants);
        });
    }

    @Override
    public void onRestaurantClick(Restaurant restaurant) {
        Intent intent = new Intent(SearchActivity.this, RestaurantDetailActivity.class);
        intent.putExtra(Constants.RESTAURANTS_BUNDLE_KEY, restaurant);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar(String title) {
        setSupportActionBar(dataBinding.searchToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(title);
    }

}
