package com.emrekose.popularmoviesstage2.ui.detail.reviews;


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

import com.emrekose.popularmoviesstage2.R;
import com.emrekose.popularmoviesstage2.base.BaseFragment;
import com.emrekose.popularmoviesstage2.model.movie.MovieResults;
import com.emrekose.popularmoviesstage2.model.reviews.MovieReviewsResults;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends BaseFragment implements ReviewMvpView {

    @Inject
    ReviewPresenter presenter;

    @BindView(R.id.review_recyclerview)
    RecyclerView reviewRecyclerView;

    @BindView(R.id.review_progressbar)
    ProgressBar reviewProgressBar;

    ReviewRecyclerAdapter adapter;

    List<MovieReviewsResults> resultsList = new ArrayList<>();

    private static final String REVIEW_STATE_KEY = "reviewlist";

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance(MovieResults results) {

        Bundle args = new Bundle();

        ReviewsFragment fragment = new ReviewsFragment();
        args.putParcelable("reviews", results);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_reviews;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);

        presenter.setView(this);

        MovieResults results = getArguments().getParcelable("reviews");

        if (savedInstanceState != null) {
            this.resultsList = savedInstanceState.getParcelableArrayList(REVIEW_STATE_KEY);
            presenter.handleScreenRotation(this.resultsList);
        } else {
            presenter.init(results.getId());
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(REVIEW_STATE_KEY, (ArrayList<? extends Parcelable>) this.resultsList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showLoading() {
        reviewProgressBar.setVisibility(View.VISIBLE);
        reviewRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        reviewProgressBar.setVisibility(View.GONE);
        reviewRecyclerView.setVisibility(View.VISIBLE);
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
    public void renderReviews(List<MovieReviewsResults> movieReviewsResults) {
        adapter = new ReviewRecyclerAdapter(movieReviewsResults, getActivity());

        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reviewRecyclerView.setAdapter(adapter);
        this.resultsList = movieReviewsResults;
    }
}
