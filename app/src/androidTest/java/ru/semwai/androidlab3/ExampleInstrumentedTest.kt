package ru.semwai.androidlab3

import android.content.pm.ActivityInfo
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBackUnconditionally
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {


    private fun onlyFirstFragmentDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.doesNotExist())
    }

    private fun onlySecondFragmentDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.doesNotExist())
    }

    private fun onlyThirdFragmentDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun onlyAboutDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    private fun click(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        Thread.sleep(200)
    }

    private fun rotate(activityScenario: ActivityScenario<MainActivity>) {
        activityScenario.onActivity { activity ->
            if (activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            else
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(800)
    }

    private fun checkOnScreen(id: Int) {
        Espresso.onView(ViewMatchers.withId(id)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun pressBack() {
        Espresso.pressBack()
        Thread.sleep(200)
    }

    @Test
    fun testAbout() {
        ActivityScenario.launch(MainActivity::class.java)
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test
    fun testBaseNavigation() {
        ActivityScenario.launch(MainActivity::class.java)
        onlyFirstFragmentDisplayed()
        click(R.id.bnToSecond) // 1 -> 2
        onlySecondFragmentDisplayed()
        click(R.id.bnToFirst) // 2 -> 1
        onlyFirstFragmentDisplayed()
        click(R.id.bnToSecond) // 1 -> 2
        onlySecondFragmentDisplayed()
        click(R.id.bnToThird) // 2 -> 3
        onlyThirdFragmentDisplayed()
        click(R.id.bnToSecond) // 3 -> 2
        onlySecondFragmentDisplayed()
        click(R.id.bnToThird) // 2 -> 3
        onlyThirdFragmentDisplayed()
        click(R.id.bnToFirst) // 3 -> 1
        onlyFirstFragmentDisplayed()
    }

    @Test
    fun testAboutNavigation() {
        ActivityScenario.launch(MainActivity::class.java)
        openAbout()
        onlyAboutDisplayed()
        pressBack()
        click(R.id.bnToSecond) // 1 -> 2
        openAbout()
        onlyAboutDisplayed()
        pressBack()
        click(R.id.bnToThird) // 2 -> 3
        openAbout()
        onlyAboutDisplayed()
    }

    @Test
    fun testPressBack () {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        click(R.id.bnToSecond) // 1 -> 2
        pressBack() // 2 -> 1
        onlyFirstFragmentDisplayed()
        click(R.id.bnToSecond) // 1 -> 2
        click(R.id.bnToThird)  // 2 -> 3
        pressBack() // 3 -> 2
        onlySecondFragmentDisplayed()
        click(R.id.bnToThird)  // 2 -> 3
        click(R.id.bnToSecond)  // 3 -> 2
        pressBack() // 2 -> 1
        onlyFirstFragmentDisplayed()

        click(R.id.bnToSecond) // 1 -> 2
        click(R.id.bnToThird)  // 2 -> 3
        openAbout()
        pressBack() // -> 3
        pressBack() // 3 -> 2
        pressBack() // 2 -> 1
        onlyFirstFragmentDisplayed()
        pressBackUnconditionally() // exit
        Thread.sleep(600)
        assertTrue(activityScenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun testRotateScreen() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        rotate(activityScenario)
        onlyFirstFragmentDisplayed()
        click(R.id.bnToSecond) // 1 -> 2
        for (i in 1..3)
            rotate(activityScenario)
        onlySecondFragmentDisplayed()
        click(R.id.bnToThird)  // 2 -> 3
        rotate(activityScenario)
        onlyThirdFragmentDisplayed()
        openAbout()
        rotate(activityScenario)
        onlyAboutDisplayed()
    }

    @Test
    fun testRotateAndCheckContent() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        rotate(activityScenario)
        checkOnScreen(R.id.bnToSecond)
        click(R.id.bnToSecond)
        rotate(activityScenario)
        checkOnScreen(R.id.bnToFirst)
        checkOnScreen(R.id.bnToThird)
        click(R.id.bnToThird)
        for (i in 1..2) {
            rotate(activityScenario)
            checkOnScreen(R.id.bnToFirst)
            checkOnScreen(R.id.bnToSecond)
        }
    }

    @Test // !
    fun testNavigation() {
        ActivityScenario.launch(MainActivity::class.java)
        openAbout()
        Espresso.onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(ViewActions.click())
        onlyFirstFragmentDisplayed()
        checkOnScreen(R.id.bnToSecond)

        click(R.id.bnToSecond)
        openAbout()
        Espresso.onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(ViewActions.click())
        onlySecondFragmentDisplayed()
        checkOnScreen(R.id.bnToFirst)
        checkOnScreen(R.id.bnToThird)

        click(R.id.bnToThird)
        openAbout()
        Espresso.onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(ViewActions.click())
        onlyThirdFragmentDisplayed()
        checkOnScreen(R.id.bnToFirst)
        checkOnScreen(R.id.bnToSecond)
    }

    @Test
    fun testNavigationException() {
        ActivityScenario.launch(MainActivity::class.java)
        try {
            Espresso.onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(ViewActions.click())
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }
}