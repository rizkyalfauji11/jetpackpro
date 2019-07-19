package com.code.alpha.jetpro.fragment.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.testing.SingleFragmentActivity
import com.code.alpha.jetpro.utils.RecyclerViewItemCountAssertion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
class MovieFragmentTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java)
    private val academyFragment = MovieFragment.newInstance(Constant.movie)

    @Before
    fun setUp() {
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadData() {
        Thread.sleep(3000)
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(RecyclerViewItemCountAssertion(10))
    }
}