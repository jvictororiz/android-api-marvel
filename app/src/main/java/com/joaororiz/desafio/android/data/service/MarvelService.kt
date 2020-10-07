package com.joaororiz.desafio.android.data.service

import com.joaororiz.desafio.android.data.entities.Character
import com.joaororiz.desafio.android.data.entities.Comic
import com.joaororiz.desafio.android.data.entities.GlobalResponse
import com.joaororiz.desafio.android.data.entities.Response
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelService {

    @GET("characters")
    fun findCharacteres(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<Response<GlobalResponse<Character>>>

    @GET("characters/{characterId}/comics")
    fun findComics(@Path("characterId") characterId:Int ): Single<Response<GlobalResponse<Comic>>>
}