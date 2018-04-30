package com.emrekose.bakingapp.ui.steps;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.model.Step;
import com.emrekose.bakingapp.utils.Constants;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends Fragment implements ExoPlayer.EventListener {

    @BindView(R.id.step_desc)
    TextView stepDescription;

    @BindView(R.id.next_step)
    Button nextStepBtn;

    @BindView(R.id.prev_step)
    Button prevStepBtn;

    @BindView(R.id.recipe_step_video)
    SimpleExoPlayerView exoPlayerView;

    SimpleExoPlayer exoPlayer;
    MediaSessionCompat mediaSession;
    PlaybackStateCompat.Builder stateBuilder;

    int stepIndex;

    private static final String STEP_ARG = "step_arg";
    private static final String MEDIA_SESSION_TAG = "steps_media_session";


    public StepsFragment() {
        // Required empty public constructor
    }

    public static StepsFragment newInstance(Step step) {

        Bundle args = new Bundle();
        args.putSerializable(STEP_ARG, step);

        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments().getSerializable(STEP_ARG) != null) {
            Step step = (Step) getArguments().getSerializable(STEP_ARG);
            stepDescription.setText(step.getDescription());
            String videoUrl = step.getVideoURL();

            playVideo(videoUrl, true);

        } else {
            if (getActivity().getIntent() != null) {
                List<Step> stepList = (List<Step>) getActivity().getIntent().getExtras().getSerializable(Constants.STEPS_EXTRA);
                stepIndex = (getActivity().getIntent().getExtras().getInt(Constants.STEPS_INDEX_EXTRA));
                stepDescription.setText(stepList.get(stepIndex).getDescription());
                String videoUrl = stepList.get(stepIndex).getVideoURL();

                playVideo(videoUrl, false);

                Intent resultIntent = new Intent();

                nextStepBtn.setOnClickListener(v -> {
                    if (stepIndex != stepList.size() - 1) {
                        stepIndex++;
                        stepDescription.setText(stepList.get(stepIndex).getDescription());
                        playVideo(stepList.get(stepIndex).getVideoURL(), false);
                    } else {
                        Toast.makeText(getActivity(), "Last Step", Toast.LENGTH_SHORT).show();
                    }

                    setResult(stepIndex, resultIntent);
                });

                prevStepBtn.setOnClickListener(v -> {
                    if (stepIndex != 0) {
                        stepIndex--;
                        stepDescription.setText(stepList.get(stepIndex).getDescription());
                        playVideo(stepList.get(stepIndex).getVideoURL(), false);
                    } else {
                        Toast.makeText(getActivity(), "First Step", Toast.LENGTH_SHORT).show();
                    }

                    setResult(stepIndex, resultIntent);
                });
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void setResult(int stepIndex, Intent intent) {
        intent.putExtra(Constants.STEP_INDEX_RESULT, stepIndex);
        getActivity().setResult(Activity.RESULT_OK, intent);
    }

    private void playVideo(String videoUrl, boolean isTwoPane) {
        releasePlayer();
        if (!videoUrl.equals("") && !videoUrl.isEmpty()) {
            exoPlayerView.setVisibility(View.VISIBLE);
            initializeMediaSession();
            initializePlayer(Uri.parse(videoUrl));

            if (isTwoPane) {
                expandVideoView(exoPlayerView);
                hideSystemUI();
            }

        } else {
            exoPlayerView.setVisibility(View.GONE);
        }
    }

    private void expandVideoView(SimpleExoPlayerView exoPlayer) {
        exoPlayer.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        exoPlayer.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(getActivity(), MEDIA_SESSION_TAG);

        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MySessionCallback());
        mediaSession.setActive(true);
    }

    private void initializePlayer(Uri mediaUri) {
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getActivity(), "RecipeSteps");
            MediaSource mediaSource = new ExtractorMediaSource(
                    mediaUri,
                    new DefaultDataSourceFactory(
                            getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);


        }
    }

    private void hideSystemUI() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }

    // Exo player media session callback class
    public class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            exoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            exoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            exoPlayer.seekTo(0);
        }
    }


    // Exo player event listeners
    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if (playbackState == ExoPlayer.STATE_READY) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
