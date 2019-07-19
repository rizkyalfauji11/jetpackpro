package com.code.alpha.jetpro.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.code.alpha.jetpro.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun load() {
        Thread.sleep(2000)
        onView(ViewMatchers.withId(R.id.viewPager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        onView(ViewMatchers.withId(R.id.viewPager)).perform(swipeLeft())
        Thread.sleep(2000)
        onView(ViewMatchers.withId(R.id.viewPager)).perform(ViewActions.swipeRight())
    }
}