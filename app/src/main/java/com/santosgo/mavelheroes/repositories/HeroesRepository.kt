package com.santosgo.mavelheroes.repositories

import com.santosgo.mavelheroes.api.ApiService
import com.santosgo.mavelheroes.data.FullHero
import com.santosgo.mavelheroes.data.Hero
import com.santosgo.mavelheroes.data.PowerstatsResponse
import retrofit2.Response
import kotlin.random.Random

class HeroesRepository (
    private val heroApiService: ApiService
){
    companion object {
        const val NUM_HEROES = 731
    }
    suspend fun getHeroPowerstats(id : Int) : Response<PowerstatsResponse> {
        return heroApiService.getHeroPowerstats(id)
    }

    suspend fun getFullHero(id : Int) : Response<FullHero> {
        return heroApiService.getFullHero(id)
    }

    suspend fun getRandFullHero() : Response<FullHero> {
        val seed = System.currentTimeMillis()
        var x = (1..NUM_HEROES).random(Random(seed))
        return heroApiService.getFullHero(x)
    }

    suspend fun getHero(id : Int) : Response<Hero> {
        var myHero : Hero? = null
        var fullHeroResp = heroApiService.getFullHero(id)
        if(fullHeroResp.isSuccessful) {
            val fullHero = fullHeroResp.body()
            fullHero?.let {
                myHero = it.toHero()
            }
            return Response.success(myHero)
        } else
            return Response.error(null,null)
    }

    suspend fun getRandHero() : Response<Hero> {
        val seed = System.currentTimeMillis()
        var x = (1..NUM_HEROES).random(Random(seed))
        return getHero(x)
    }

    suspend fun getSomeRandHeroes(num : Int) : Response<List<Hero>> {
        var heroList : MutableList<Hero> = mutableListOf()
        for (i in 1 .. num) {
            val heroResp = getRandHero()
            if(heroResp.isSuccessful) {
                heroResp.body()?.let { heroList.add(heroList.size,heroResp.body()!!) }
            }
            else {
                return Response.error(null,null)
            }
        }
        return Response.success(heroList)
    }
}