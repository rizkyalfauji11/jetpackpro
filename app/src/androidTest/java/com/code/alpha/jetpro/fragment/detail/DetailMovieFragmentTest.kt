package com.code.alpha.jetpro.fragment.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.code.alpha.jetpro.R
import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.source.remote.response.Movie
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.code.alpha.jetpro.testing.SingleFragmentActivity
import com.code.alpha.jetpro.utils.FakeDataDummy
import com.google.gson.Gson
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMovieFragmentTest {
    private val dummy: Movie? =
        Gson().fromJson(FakeDataDummy.getJsonToString(Constant.movie), MoviesModel::class.java).movies?.get(1)

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<SingleFragmentActivity> =
        object : ActivityTestRule<SingleFragmentActivity>(SingleFragmentActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, SingleFragmentActivity::class.java)
                result.putExtra(Constant.movie, dummy)
                return result
            }
        }

    @Before
    fun setUp() {
        val intent = activityRule.activity.intent
        val fragment = DetailMovieFragment.newInstance(intent.getParcelableExtra(Constant.movie))
        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun load() {
        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.title_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.title_movie))
            .check(ViewAssertions.matches(ViewMatchers.withText("Spider-Man: Into the Spider-Verse")))
    }
}
