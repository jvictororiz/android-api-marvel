package com.joaororiz.desafio.android.repository

import com.joaororiz.desafio.android.base.BaseServiceTest
import com.joaororiz.desafio.android.data.service.MarvelService
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


internal class CharacterRepositoryTest : BaseServiceTest() {


    @Mock
    private lateinit var service: MarvelService

    @Mock
    private lateinit var repository: CharactereRepository

    @Test
    fun `Must list all comics  successfully`() {
        val repository = CharactereRepository.CharactereRepositoryImpl(service)
        Mockito.doReturn(readJson("/json/response_list_comics.json")).`when`<MarvelService>(service).findComics(mock())
        val response = repository.listAll()
        assertNotNull(response)
        assertTrue(response.pagedList.value?.get(0)?.name == "3-D Man")
    }

    @Test
    fun `Must list all comics  in error`() {
//        val mockResponse = mockUnsuccessfulResponse()
//        mockWebServer.enqueue(mockResponse)
//        val response = buildWebServiceTest<MarvelService>().findCharacteres(mock(), mock()).execute()
//        assertNull(response.body())
//        assertFalse(response.isSuccessful)
    }
}
