package com.joaororiz.desafio.android.viewModel.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaororiz.desafio.android.R
import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.useCase.CharacterUseCase
import com.joaororiz.desafio.android.viewModel.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    private val useCase: CharacterUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    private val disposable = CompositeDisposable()
    private var offset = 0

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String>
        get() = _error

    private val _load = MutableLiveData<Boolean>()
    val load: LiveData<Boolean>
        get() = _load

    private val _loadSwipe = SingleLiveEvent<Boolean>()
    val loadSwipe: LiveData<Boolean>
        get() = _loadSwipe

    private val _listAllCharacter = SingleLiveEvent<List<Character>>()
    val listAllCharacter: LiveData<List<Character>>
        get() = _listAllCharacter

    private val _resetListCharacter = SingleLiveEvent<Void>()
    val resetListCharacter: LiveData<Void>
        get() = _resetListCharacter

    private val _selectCharacter = SingleLiveEvent<Character>()
    val selectCharacter : LiveData<Character>
        get() = _selectCharacter

    private val _listAllComics = SingleLiveEvent<List<Comic>>()
    val listAllComics: LiveData<List<Comic>>
        get() = _listAllComics


    init {
        listAllCharacter()
    }


    fun listAllCharacter() {
        offset = 0
        _loadSwipe.value = true
        _listAllCharacter.value = listOf()
        _resetListCharacter.postValue(null)
        refreshListCharacter()
    }

    fun nextListCharacter() {
        _load.value = true
        offset += LIMIT_LIST
        refreshListCharacter()
    }

    fun refreshListCharacter() {
        disposable.add(useCase.listAll(LIMIT_LIST, offset).subscribe { res, error ->
            if (error == null) {
                _listAllCharacter.value = res.results
            } else {
                this._error.value = error.message
            }
            _loadSwipe.value = false
            _load.value = false
        })
    }

    fun findComicsByCharacter() {
        _load.value = true
        getSelectedCharacter()?.let {
            disposable.add(useCase.findComicsByCharactere(it.id).subscribe { res, error ->
                if (error == null) {
                    if (res.results.isNullOrEmpty()) {
                        this._error.value = app.getString(R.string.empty_list)
                    } else {
                        _listAllComics.value = res.results
                    }
                } else {
                    this._error.value = app.getString(R.string.error)
                }
                _load.value = false
            })
        }

    }

    fun selectCharactere(character: Character) {
        _selectCharacter.value = character
    }

    fun getSelectedCharacter() = _selectCharacter.value

    fun getDescription(): String {
       return if(_selectCharacter.value?.description.isNullOrEmpty()){
            app.getString(R.string.empty_description)
        }else{
           _selectCharacter.value?.description?:""
       }
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        const val LIMIT_LIST = 10
    }

}