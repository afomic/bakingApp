package com.afomic.bakingapp;

/**
 * Created by afomic on 6/29/17.
 */
import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityIdlingTest {
    private IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<MainActivity> activity=new ActivityTestRule<MainActivity>(MainActivity.class);
    @Before
    public void registerIdlingResources(){
        idlingResource = activity.getActivity().getIdlingResource();
        // Register Idling Resources
        Espresso.registerIdlingResources(idlingResource);
    }


    @Test
    public void foodListIsDisplayed(){
        onView(withId(R.id.food_list)).check(matches(isDisplayed()));
    }
    @Test
    public void checkIfFoodImageIsDisplayed(){
        onView(withId(R.id.food_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        onView(withId(R.id.iv_food_image)).check(matches(isDisplayed()));
    }
    @After
    public void unRegisterIdlingResources(){
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }
}
