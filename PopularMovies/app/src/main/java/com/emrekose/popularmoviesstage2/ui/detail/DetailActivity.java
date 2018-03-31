package com.emrekose.popularmoviesstage2.ui.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.base.BaseActivity;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.ui.detail.favorite.FavoriteMvpView;
import com.emrekose.popularmoviesstage2.ui.detail.favorite.FavoritePresenter;
import com.emrekose.popularmoviesstage2.ui.detail.overview.OverviewFragment;
import com.emrekose.popularmoviesstage2.ui.detail.reviews.ReviewsFragment;
import com.emrekose.popularmoviesstage2.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements FavoriteMvpView {

    @Inject
    FavoritePresenter presenter;

    @BindView(R.id.detailAppBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.movie_detail_poster_path)
    ImageView moviePoster;

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.detail_movie_name)
    TextView movieName;

    @BindView(R.id.detail_backdrop)
    ImageView movieBackDrop;

    @BindView(R.id.detail_viewpager)
    ViewPager viewPager;

    @BindView(R.id.movie_detail_tabs)
    TabLayout tabLayout;

    @BindView(R.id.movie_detail_toolbar)
    Toolbar detailToolbar;

    @BindView(R.id.favoriteFab)
    FloatingActionButton favoriteFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        presenter.setView(this);

        setupToolbar();


        if (getIntent().getExtras() != null) {
            MovieResults results = getIntent().getExtras().getParcelable("movies");
            prepareMovieDetails(results);
            setupViewPager(viewPager, results);
            fabBehaviour(results);
            isFavoriteCheck(results);

            favoriteFab.setOnClickListener(view -> {
                if (presenter.isMovieFavorited(results.getId())) {
                    presenter.removeToFavorite(results);
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.addToFavorite(results);
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
            });
        }

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbar() {
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void fabBehaviour(MovieResults results) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {

            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                favoriteFab.hide();
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                detailToolbar.setTitle(results.getTitle());
            } else {
                favoriteFab.show();
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                detailToolbar.setTitle(" ");
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void prepareMovieDetails(MovieResults results) {
        movieName.setText(results.getTitle());
        detailToolbar.setTitle(results.getTitle());

        Glide.with(this).load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W500 + results.getBackdrop_path()).apply(new RequestOptions().placeholder(R.drawable.no_movie_image)).into(movieBackDrop);
        Glide.with(this).load(Constants.IMAGE_BASE_URL + Constants.IMAGE_W500 + results.getPoster_path()).into(moviePoster);
    }

    private void setupViewPager(ViewPager viewPager, MovieResults results) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(OverviewFragment.newInstance(results), getString(R.string.detail_movie_overview));
        adapter.addFragment(ReviewsFragment.newInstance(results), getString(R.string.detail_movie_reviews));
        viewPager.setAdapter(adapter);
    }

    private void isFavoriteCheck(MovieResults results) {
        if (presenter.isMovieFavorited(results.getId())) {
            updateFavImage(true);
        } else {
            updateFavImage(false);
        }
    }

    @Override
    public void updateFavImage(boolean fav) {
        if (fav) {
            favoriteFab.setImageResource(R.drawable.ic_star);
        } else {
            favoriteFab.setImageResource(R.drawable.ic_star_border);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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
}
