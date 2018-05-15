package com.emrekose.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.emrekose.bakingapp.model.Ingredient;
import com.emrekose.bakingapp.model.RecipeResponse;
import com.emrekose.bakingapp.model.Step;
import com.emrekose.bakingapp.ui.detail.RecipeDetailActivity;
import com.emrekose.bakingapp.utils.Constants;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.instanceOf;


@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {

    private RecipeResponse mRecipeResponse = new RecipeResponse();
    private static final int RECIPE_ID = 2;
    private static final String RECIPE_NAME = "Brownies";
    private static final List<Ingredient> RECIPE_INGREDIENT_LIST = new ArrayList<Ingredient>() {{
        add(new Ingredient() {{
            setQuantity(350);
            setMeasure("G");
            setIngredient("unsalted butter");
        }});
    }};
    private static final List<Step> RECIPE_STEP_LIST = new ArrayList<Step>() {{
        add(new Step(){{
            setDescription("Recipe Introduction");
            setVideoURL("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdc33_-intro-brownies/-intro-brownies.mp4");
        }});
    }};

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mDetailActivityRule = new ActivityTestRule<RecipeDetailActivity>(RecipeDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            mRecipeResponse.setId(RECIPE_ID);
            mRecipeResponse.setName(RECIPE_NAME);
            mRecipeResponse.setIngredients(RECIPE_INGREDIENT_LIST);
            mRecipeResponse.setSteps(RECIPE_STEP_LIST);
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent resultIntent = new Intent(targetContext, RecipeDetailActivity.class);
            resultIntent.putExtra(Constants.RECIPES_EXTRA, mRecipeResponse);
            return resultIntent;
        }
    };

    @Test
    public void showToolbarTitle_AndSteps() {
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.recipe_detail_toolbar))))
                .check(matches(withText(RECIPE_NAME)));
        onView(withId(R.id.recipe_steps)).check(matches(isDisplayed()));
    }
}
