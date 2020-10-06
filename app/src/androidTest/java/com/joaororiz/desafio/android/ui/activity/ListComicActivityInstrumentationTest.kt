package com.joaororiz.desafio.android.ui.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.joaororiz.desafio.android.base.BaseServiceInstrumentationTest
import com.joaororiz.desafio.android.R
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ListComicActivityInstrumentationTest : BaseServiceInstrumentationTest() {

    @get:Rule
    val rule = ActivityTestRule(ListComicActivity::class.java, true, false)

    @Before
    fun setup() {

    }

    @Before
    fun finish(){

    }

    @Test
    fun shouldDisplayTitle() {
        rule.launchActivity(null)
        val expectedTitle = context.getString(R.string.title)
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayError() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return mockUnsuccessfulResponse()
            }
        }
        rule.launchActivity(null).apply {
            onView(withId(R.id.tv_error)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return mockSuccessfulResponse("/json/response_list_comics.json")
            }
        }
        rule.launchActivity(null).apply {
            onView(withId(R.id.tv_error)).check(matches(not(isDisplayed())))
            onView(withText("3-D Man")).check(matches(isDisplayed()))
        }
    }
}