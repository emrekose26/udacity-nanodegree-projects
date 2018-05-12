package com.emrekose.bakingapp.ui.detail;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.emrekose.bakingapp.R;
import com.emrekose.bakingapp.base.BaseFragment;
import com.emrekose.bakingapp.data.local.BakingProvider;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.ui.steps.StepsActivity;
import com.emrekose.bakingapp.ui.steps.StepsFragment;
import com.emrekose.bakingapp.utils.ConfigLayoutSizeUtil;
import com.emrekose.bakingapp.utils.Constants;
import com.emrekose.bakingapp.widget.RecipesAppWidgetProvider;
import com.emrekose.bakingapp.widget.RecipesWidgetService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.feng.common.stepperview.IStepperAdapter;
import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends BaseFragment implements RecipeDetailView, IStepperAdapter,
        LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    RecipeDetailPresenter presenter;

    @BindView(R.id.recipe_details_ingredients)
    TextView recipeIngredients;

    @BindView(R.id.recipe_steps)
    VerticalStepperView verticalStepperView;

    private RecipeResponse response;
    private Cursor loadData;

    private static final String RECIPE_ARGUMENT = "recipe_arg";
    private static final int CURSOR_LOADER_ID = 0;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(RecipeResponse response) {

        Bundle args = new Bundle();
        args.putSerializable(RECIPE_ARGUMENT, response);

        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_recipe_detail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            response = (RecipeResponse) getArguments().getSerializable(RECIPE_ARGUMENT);
            setIngredients(response);
            verticalStepperView.setStepperAdapter(this);
        }

        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
    }

    private void setIngredients(RecipeResponse response) {
        recipeIngredients.setText("Ingredients\n");

        for (int i = 0; i < response.getIngredients().size(); i++) {
            String ingredient = response.getIngredients().get(i).getIngredient();
            double quantity = response.getIngredients().get(i).getQuantity();
            String measure = response.getIngredients().get(i).getMeasure();
            String formattedValue = (i + 1) + ". " + ingredient + "(" + quantity + " " + measure + ")" + "\n";
            recipeIngredients.append(formattedValue);
        }
    }

    private String getFormattedIngredients(RecipeResponse response) {
        List<String> quantities = new ArrayList<>();

        for (int i = 0; i < response.getIngredients().size(); i++) {
            quantities.add(String.valueOf(response.getIngredients().get(i).getQuantity()) + " "
                    + response.getIngredients().get(i).getMeasure() + " x " +
                    response.getIngredients().get(i).getIngredient());
        }

        return TextUtils.join("\n", quantities);
    }

    @NonNull
    @Override
    public CharSequence getTitle(int i) {
        return "Step " + i;
    }

    @Nullable
    @Override
    public CharSequence getSummary(int i) {
        if (i == 0) {
            return Html.fromHtml(String.format("First step", response.getName()));
        } else {
            if (i != size() - 1) {
                return Html.fromHtml(response.getSteps().get(i - 1).getDescription());
            } else {
                return Html.fromHtml(String.format("Last step", response.getName()));
            }
        }
    }

    @Override
    public int size() {
        return response.getIngredients().size();
    }

    @Override
    public View onCreateCustomView(int index, Context context, VerticalStepperItemView parent) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.vertical_stepper_item, parent, false);

        TextView contentView = inflateView.findViewById(R.id.item_content);
        contentView.setText(response.getSteps().get(index).getDescription());

        Button nextButton = inflateView.findViewById(R.id.button_next);
        nextButton.setOnClickListener(v -> {
            if (verticalStepperView.canNext()) {
                verticalStepperView.nextStep();
            }

            if (ConfigLayoutSizeUtil.isTabletMode(getActivity())) {
                if (inflateView.findViewById(R.id.steps_container) == null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.steps_container, StepsFragment.newInstance(response.getSteps().get(index)))
                            .commit();
                }
            } else {
                Intent intent = new Intent(getActivity(), StepsActivity.class);
                intent.putExtra(Constants.STEPS_EXTRA, (Serializable) response.getSteps());
                intent.putExtra(Constants.STEPS_INDEX_EXTRA, index);
                startActivityForResult(intent, Constants.STEPS_REQUEST_CODE);
            }
        });

        Button prevButton = inflateView.findViewById(R.id.button_prev);
        prevButton.setOnClickListener(v -> {
            if (index != 0) {
                verticalStepperView.prevStep();
            } else {
                verticalStepperView.setAnimationEnabled(!verticalStepperView.isAnimationEnabled());
            }
        });

        ImageView videoIcon = inflateView.findViewById(R.id.item_video_icon);
        if (!response.getSteps().get(index).getVideoURL().equals("")) {
            videoIcon.setVisibility(View.VISIBLE);
        } else {
            videoIcon.setVisibility(View.GONE);
        }

        return inflateView;
    }

    @Override
    public void onShow(int i) {

    }

    @Override
    public void onHide(int i) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.STEPS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                int stepIndex = data.getIntExtra(Constants.STEP_INDEX_RESULT, 0);
                verticalStepperView.setCurrentStep(stepIndex);
                verticalStepperView.scrollTo(stepIndex, 0);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recipe_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_to_widget:
                presenter.addRecipeIngredients(response.getName(), getFormattedIngredients(response));
                Toast.makeText(getActivity(), getActivity().getString(R.string.recipe_added), Toast.LENGTH_SHORT).show();


                // TODO: 12.05.2018 use intent service for widget update if it possible
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
                RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.recipes_app_widget);
                Intent intent = new Intent(getActivity(), RecipesWidgetService.class);
                remoteViews.setRemoteAdapter(R.id.widget_ingredients_lv, intent);

                remoteViews.setEmptyView(R.id.widget_ingredients_lv, R.id.widget_empty_view);
                ComponentName componentName = new ComponentName(getActivity(), RecipesAppWidgetProvider.class);
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);

                appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_ingredients_lv);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(
                getActivity(),
                BakingProvider.RecipeIngredients.CONTENT_URI,
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


