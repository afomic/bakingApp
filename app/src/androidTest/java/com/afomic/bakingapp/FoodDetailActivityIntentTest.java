package com.afomic.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;

/**
 * Created by afomic on 6/29/17.
 */

public class FoodDetailActivityIntentTest {
    @Rule
    public IntentsTestRule<FoodDetailActivity> intentsTestRule = new IntentsTestRule<>(
            FoodDetailActivity.class);


    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {

//        // Stub all external intents
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }

    /**
     * Clicks on a RecyclerViewItem and checks if it has intent with extra with the key RECIPE_EXTRA
     */
    @Test
    public void clickRecyclerViewItemHasIntentWithAKey() {

        // Click on the Recipe List RecyclerView item at position 1
        onView(withId(R.id.)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        //Checks if the key is present
        intended(hasExtraWithKey(RECIPE_EXTRA));

    }
}
