package com.joaororiz.desafio.android.viewModel

import android.app.Application
import androidx.lifecycle.Observer
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.base.BaseTest
import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.repository.CharacterRepository
import com.joaororiz.desafio.android.useCase.CharacterUseCase
import com.joaororiz.desafio.android.viewModel.main.MainViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.*
import org.junit.Test
import org.mockito.Mock


class ListComicViewModelTest : BaseTest() {
    @Mock
    private lateinit var listAllCharacter: Observer<List<Character>>

    @Mock
    private lateinit var listAllComics: Observer<List<Comic>>

    @Mock
    private lateinit var error: Observer<String>

    @Mock
    private lateinit var app: Application

    @Mock
    private lateinit var useCase: CharacterUseCase

    private lateinit var viewModel: MainViewModel


    @Test
    fun `when viewModel calls listAllCharacters with success then sets _listAllCharacter with success`() {
        val expectedMock = GlobalResponse(0, 0, 0, 0, listOf(Character(0, "", "", mock())))
        whenever(useCase.listAll(any(), any())).thenReturn(Single.just(expectedMock))

        viewModel = MainViewModel(useCase, app)
        viewModel.listAllCharacter.observeForever(listAllCharacter)

        verify(listAllCharacter).onChanged(expectedMock.results)
        assertNotNull(viewModel.listAllCharacter.value)
        assertEquals(viewModel.listAllCharacter.value, expectedMock.results)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `when viewModel calls listAllCharacters with error then sets _error with success`() {
        val messageError = "Error"
        whenever(useCase.listAll(any(), any())).thenReturn(Single.error(Exception(messageError)))

        viewModel = MainViewModel(useCase, app)
        viewModel.error.observeForever(error)

        verify(error).onChanged(messageError)
    }

    @Test
    fun `when viewModel calls findComicsByCharacter with success but empty then sets _error with success`() {
        val messageError = "Este personagem ainda não possui participação em Comic"
        val expectedMock = GlobalResponse(0, 0, 0, 0, listOf<Comic>())
        whenever(useCase.findComicsByCharacter(any())).thenReturn(Single.just(expectedMock))
        whenever(useCase.listAll(any(), any())).thenReturn(Single.just(mock()))
        whenever(app.getString(R.string.empty_list)).thenReturn(messageError)
        viewModel = MainViewModel(useCase, app)
        viewModel.error.observeForever(error)
        viewModel.selectCharacter(Character(0, "", "", mock()))
        viewModel.findComicsByCharacter()

        assertNull(viewModel.listAllComics.value)
        verify(error).onChanged(messageError)
    }

    @Test
    fun `when viewModel calls findComicsByCharacter with success then sets _listAllComics with success`() {
        val expectedMock = GlobalResponse(0, 0, 0, 0, listOf(Comic("", "", mock(), listOf())))
        whenever(useCase.findComicsByCharacter(any())).thenReturn(Single.just(expectedMock))
        whenever(useCase.listAll(any(), any())).thenReturn(Single.just(mock()))
        viewModel = MainViewModel(useCase, app)
        viewModel.selectCharacter(Character(0, "", "", mock()))
        viewModel.listAllComics.observeForever(listAllComics)
        viewModel.error.observeForever(error)
        viewModel.findComicsByCharacter()


        verify(listAllComics).onChanged(expectedMock.results)
        assertNotNull(viewModel.listAllComics.value)
        assertEquals(viewModel.listAllComics.value, expectedMock.results)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `when viewModel calls findComicsByCharacter with error then sets _error with success`() {
        val messageError = "Ocorreu um erro ao carregar os itens"
        whenever(useCase.listAll(any(), any())).thenReturn(Single.just(mock()))
        whenever(useCase.findComicsByCharacter(any())).thenReturn(Single.error(Exception(messageError)))
        whenever(app.getString(R.string.error)).thenReturn(messageError)

        viewModel = MainViewModel(useCase, app)
        viewModel.selectCharacter(Character(0, "", "", mock()))
        viewModel.findComicsByCharacter()
        viewModel.error.observeForever(error)

        verify(error).onChanged(messageError)
    }


}