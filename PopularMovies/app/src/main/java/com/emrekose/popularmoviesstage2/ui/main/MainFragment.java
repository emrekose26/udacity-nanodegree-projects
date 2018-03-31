package com.emrekose.popularmoviesstage2.ui.main;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.base.BaseFragment;
import com.emrekose.popularmoviesstage2.data.local.MoviesContract;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.ui.detail.DetailActivity;
import com.emrekose.popularmoviesstage2.util.SortType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements MovieMvpView, MovieRecyclerAdapter.MovieClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    MoviePresenter presenter;

    @BindView(R.id.mainProgressbar)
    ProgressBar progressBar;

    @BindView(R.id.moviesRecyclerView)
    RecyclerView moviesRecyclerView;

    MovieRecyclerAdapter adapter;

    private final int GRIDLAYOUT_SPAN_COUNT = 2;

    private static final int CURSOR_LOADER_ID = 0;

    private Cursor loadData;

    List<MovieResults> resultsList = new ArrayList<>();

    private static final String MOVIE_STATE_KEY = "movielist";

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        presenter.setView(this);

        if (savedInstanceState != null) {
            this.resultsList = savedInstanceState.getParcelableArrayList(MOVIE_STATE_KEY);
            presenter.handleScreenRotation(this.resultsList);
        } else {
            presenter.init(SortType.POPULAR, null);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(MOVIE_STATE_KEY, (ArrayList<? extends Parcelable>) this.resultsList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        moviesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        moviesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getActivity().findViewById(R.id.mainLayout), "Something went wrong.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkConnectionError() {
        Snackbar.make(getActivity().findViewById(R.id.mainLayout), "Network connection error.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError() {
        Snackbar.make(getActivity().findViewById(R.id.mainLayout), "Something went wrong. Server not found", Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void renderMovies(List<MovieResults> movieResults) {
        adapter = new MovieRecyclerAdapter(movieResults, getActivity(), this);

        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRIDLAYOUT_SPAN_COUNT));
        moviesRecyclerView.setAdapter(adapter);
        this.resultsList = movieResults;
    }

    @Override
    public void onMovieClick(MovieResults results) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movies", results);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                presenter.init(SortType.POPULAR, null);
                break;
            case R.id.sort_by_rating:
                presenter.init(SortType.TOPRATED, null);
                break;
            case R.id.sort_by_favorite:
                presenter.init(SortType.FAVORITE, loadData);
                break;
        }
        return true;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(getActivity(),
                MoviesContract.MoviesEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.getCount() > 0) {
            loadData = data;
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
