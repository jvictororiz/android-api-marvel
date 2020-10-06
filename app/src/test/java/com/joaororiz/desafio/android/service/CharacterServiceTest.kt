package com.joaororiz.desafio.android.service

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.action.ViewActions
import com.joaororiz.desafio.android.base.BaseServiceTest
import androidx.test.espresso.matcher.ViewMatchers
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.data.service.MarvelService
import com.nhaarman.mockitokotlin2.mock
import kotlinx.android.synthetic.main.activity_comic_list.view.*
import org.junit.Assert.*
import org.junit.Test


internal class CharacterServiceTest : BaseServiceTest() {

    @Test
    fun `Must list all users  successfully`() {
        val mockResponse = mockSuccessfulResponse("/json/response_list_comics.json")
        mockWebServer.enqueue(mockResponse)
        val response = buildWebServiceTest<MarvelService>().findCharacteres(mock(), mock()).execute()
        assertNotNull(response.body())
        assertTrue(response.isSuccessful)
    }

    @Test
    fun `Must list all users  in error`() {
        val mockResponse = mockUnsuccessfulResponse()
        mockWebServer.enqueue(mockResponse)
        val response = buildWebServiceTest<MarvelService>().findCharacteres(mock(), mock()).execute()
        assertNull(response.body())
        assertFalse(response.isSuccessful)
    }
}
