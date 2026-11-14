package com.emoulgen.skycast

import com.emoulgen.skycast.data.repository.ForecastRepositoryImpl
import com.emoulgen.skycast.fakeClasses.FakeForecastApi
import com.emoulgen.skycast.fakeClasses.FakeForecastDao
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class ForecastRepositoryImplTest {


    val api = FakeForecastApi()
    val dao = FakeForecastDao()

    val repository = ForecastRepositoryImpl(
        api = api,
        dao = dao,
        json = Json { ignoreUnknownKeys = true }
    )

    @Test
    fun `online request is not from cache and is saved`() = runTest{
        val forecast = repository.getForecast(
            cityId = 1,
            latitude = 74.0,
            longitude = 46.0
        )

        assertFalse(forecast.isFromCache)
        assertTrue(dao.caches.containsKey(1))
    }

    @Test
    fun `the data is in cache when no internet`() = runTest {
        val forecast = repository.getForecast(
            cityId = 1,
            latitude = 74.0,
            longitude = 46.0
        )
        assertFalse(forecast.isFromCache)

        api.shouldThrow = true

        val offlineForecast = repository.getForecast(
            cityId = 1,
            latitude = 74.0,
            longitude = 46.0
        )

        assertTrue(offlineForecast.isFromCache)

    }

    @Test
    fun `no internet and no cache`() = runTest{

        api.shouldThrow = true

        val offlineResult = runCatching {
            repository.getForecast(
                cityId = 2,
                latitude = 67.0,
                longitude = 53.0
            )
        }

        assertTrue(offlineResult.exceptionOrNull() is IOException)
    }
}