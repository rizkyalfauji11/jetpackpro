package com.code.alpha.jetpro.activity

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.fragment.detail.DetailMovieFragment
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.remote.response.Movie
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.code.alpha.jetpro.utils.FakeDataDummy
import com.google.gson.Gson
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailMovieActivityTest {
    private val dummy: Movie? =
        Gson().fromJson(FakeDataDummy.getJsonToString(Constant.movie), MoviesModel::class.java).movies?.get(0)

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<DetailMovieActivity> =
        object : ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, DetailMovieActivity::class.java)
                result.putExtra(Constant.movie, dummy)
                return result
            }
        }

    @Before
    fun setUp() {
        val intent = activityRule.activity.intent
        val fragment = DetailMovieFragment.newInstance(intent.getParcelableExtra(Constant.movie))
        activityRule.activity.addFragment(fragment)
    }

    @Test
    fun load() {
        Thread.sleep(2000)
        onView(withId(R.id.title_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.title_movie)).check(matches(withText("Aquaman")))
    }
}