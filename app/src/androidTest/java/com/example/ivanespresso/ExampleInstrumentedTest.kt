package com.example.ivanespresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testButtonClickLaunchesSecondActivity() {

        onView(withId(R.id.buttonActividad1))
            .check(matches(isDisplayed()))


        onView(withId(R.id.buttonActividad1))
            .perform(click())


        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
    }
}