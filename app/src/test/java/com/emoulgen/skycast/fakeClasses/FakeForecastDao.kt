package com.emoulgen.skycast.fakeClasses

import com.emoulgen.skycast.data.local.ForecastCacheEntity
import com.emoulgen.skycast.data.local.ForecastDao

class FakeForecastDao: ForecastDao {

    val caches = hashMapOf<Int, ForecastCacheEntity>()

    override suspend fun upsertCache(cache: ForecastCacheEntity) {
        caches[cache.cityId] = cache
    }

    override suspend fun getCache(id: Int): ForecastCacheEntity? = caches[id]
}