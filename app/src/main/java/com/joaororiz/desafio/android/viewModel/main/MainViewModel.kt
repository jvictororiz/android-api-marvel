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

    private var _error = SingleLiveEvent<String>()
    val error: LiveData<String>
        get() = _error

    private val _load = MutableLiveData<Boolean>()
    val load: LiveData<Boolean>
        get() = _load

    private val _loadSwipe = MutableLiveData<Boolean>()
    val loadSwipe: LiveData<Boolean>
        get() = _loadSwipe

    private val _listAllCharacter = MutableLiveData<List<Character>>()
    val listAllCharacter: LiveData<List<Character>>
        get() = _listAllCharacter

    private val _resetListCharacter = SingleLiveEvent<Any>()
    val resetListCharacter: LiveData<Any>
        get() = _resetListCharacter

    private val _selectCharacter = SingleLiveEvent<Character>()

    private val _listAllComics = MutableLiveData<List<Comic>>()
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
                _listAllCharacter.value = res
                useCase.saveAllCharacters(res).subscribe()
            } else {
                locadCharacterLocal()
            }
            _loadSwipe.value = false
            _load.value = false
        })
    }

    private fun locadCharacterLocal() {
        disposable.add(
            useCase.listAllLocal().subscribe { res, error ->
                if (res.isEmpty()) {
                    this._error.value = app.applicationContext.getString(R.string.error_empty_character)
                } else {
                    _resetListCharacter.value = null
                    _listAllCharacter.value = res
                }
            }
        )

    }

    fun findLocalComicsByCharacter() {
        _load.value = true
        getSelectedCharacter()?.id?.let {
            useCase.findComicsByCharacterLocal(it)
                .doOnSubscribe { disposable::add }
                .subscribe {res, error->
                if(error== null || res.isEmpty()){
                    findComicsByCharacter()
                }else{
                    _listAllComics.value = res
                }
            }
        }
    }

   private fun findComicsByCharacter(){
        getSelectedCharacter()?.let {
            disposable.add(useCase.findComicsByCharacter(it.id ?: -1).subscribe { res, error ->
                if (error == null) {
                    if (res.isNullOrEmpty()) {
                        this._error.value = app.getString(R.string.empty_list)
                    } else {
                        saveLocalComicsByCharacter(it.id ?: -1, res)
                        _listAllComics.value = res
                    }
                } else {
                    this._error.value = app.getString(R.string.error)
                }
                _load.value = false
            })
        }

    }

    private fun saveLocalComicsByCharacter(idCharacter :Int, res: List<Comic>) {
        useCase.saveAllComics(idCharacter, res).doOnSubscribe { disposable.add(it)}.subscribe()
    }

    fun selectCharacter(character: Character) {
        _selectCharacter.value = character
    }

    fun getSelectedCharacter() = _selectCharacter.value

    fun getDescription(): String {
        return if (_selectCharacter.value?.description.isNullOrEmpty()) {
            app.getString(R.string.empty_description)
        } else {
            _selectCharacter.value?.description ?: ""
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