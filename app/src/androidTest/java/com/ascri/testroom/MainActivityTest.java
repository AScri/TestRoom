package com.ascri.testroom;


import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule actRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkInfo(){
        onView(ViewMatchers.withId(R.id.rand_button)).perform(ViewActions.click());
    }
}
