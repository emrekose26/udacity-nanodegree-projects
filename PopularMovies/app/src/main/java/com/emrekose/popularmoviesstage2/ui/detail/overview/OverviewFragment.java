package com.emrekose.popularmoviesstage2.ui.detail.overview;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.base.BaseFragment;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.model.videos.MovieVideosResults;
import com.emrekose.popularmoviesstage2.util.Constants;
import com.emrekose.popularmoviesstage2.widget.RatingBarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends BaseFragment implements TrailerMvpView, TrailerRecyclerAdapter.TrailerClickListener {

    @Inject
    TrailerPresenter presenter;

    @BindView(R.id.overview)
    TextView movieOverview;

    @BindView(R.id.movies_trailer_recyclerview)
    RecyclerView trailerRecyclerView;

    @BindView(R.id.detail_movie_trailers_progress)
    ProgressBar trailerProgressBar;

    @BindView(R.id.ratingBar)
    RatingBarView ratingBar;

    @BindView(R.id.releaseDate)
    TextView releaseDate;

    @BindView(R.id.voteCount)
    TextView voteCount;

    TrailerRecyclerAdapter adapter;

    List<MovieVideosResults> resultsList = new ArrayList<>();

    private static final String TRAILER_STATE_KEY = "trailerlist";

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(MovieResults results) {
        Bundle args = new Bundle();

        OverviewFragment fragment = new OverviewFragment();
        args.putParcelable("movies", results);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_overview;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);

        presenter.setView(this);

        MovieResults results = getArguments().getParcelable("movies");

        movieOverview.setText(results.getOverview());

        ratingBar.setProgress((float) results.getVote_average());
        ratingBar.setText(results.getVote_average() + "");

        releaseDate.setText(results.getRelease_date());

        voteCount.setText("from " + results.getVote_count() + " people");

        if (savedInstanceState != null) {
            this.resultsList = savedInstanceState.getParcelableArrayList(TRAILER_STATE_KEY);
            presenter.handleScreenRotation(this.resultsList);
        } else {
            presenter.init(results.getId());
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(TRAILER_STATE_KEY, (ArrayList<? extends Parcelable>) this.resultsList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading() {
        trailerProgressBar.setVisibility(View.VISIBLE);
        trailerRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        trailerProgressBar.setVisibility(View.GONE);
        trailerRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getActivity().findViewById(R.id.detailCoordinatorLayout), "Something went wrong.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkConnectionError() {
        Snackbar.make(getActivity().findViewById(R.id.detailCoordinatorLayout), "Network connection error.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showServerError() {
        Snackbar.make(getActivity().findViewById(R.id.detailCoordinatorLayout), "Something went wrong. Server not found", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void renderTrailers(List<MovieVideosResults> movieVideosResultsList) {
        adapter = new TrailerRecyclerAdapter(movieVideosResultsList, getActivity(), this);

        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        trailerRecyclerView.setAdapter(adapter);
        this.resultsList = movieVideosResultsList;
    }

    @Override
    public void onTrailerClick(MovieVideosResults results) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Constants.YOUTUBE_WATCH_URL + results.getKey()));
        startActivity(intent);
    }
}
