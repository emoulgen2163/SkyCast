package com.emoulgen.skycast

import com.emoulgen.skycast.domain.model.City
import com.emoulgen.skycast.fakeClasses.FakeCityRepository
import com.emoulgen.skycast.presentation.viewmodel.SearchResult
import com.emoulgen.skycast.presentation.viewmodel.SearchViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeCityRepository()

    @Test
    fun `empty result returns empty state`(){
        repository.citiesToReturn = emptyList()
        val viewModel = SearchViewModel(repository)
        viewModel.onQueryChange("asdasdasd")
        viewModel.onSearch()

        assertEquals(SearchResult.Empty, viewModel.uiState.value.results)
    }

    @Test
    fun `empty text returns idle state`(){
        val viewModel = SearchViewModel(repository)
        viewModel.onQueryChange("")
        viewModel.onSearch()
        assertEquals(SearchResult.Idle, viewModel.uiState.value.results)
    }

    @Test
    fun `2 cities return 2`(){
        val cities = listOf(
            City(
                id = 0,
                name = "New York",
                country = "US",
                latitude = 0.0,
                longitude = 0.0,
                region = ""
            ),

            City(
                id = 1,
                name = "Los Angeles",
                country = "US",
                latitude = 0.0,
                longitude = 0.0,
                region = ""
            )
        )
        repository.citiesToReturn = cities
        val viewModel = SearchViewModel(repository)
        viewModel.onQueryChange("New York")
        viewModel.onSearch()

        assertEquals(2, (viewModel.uiState.value.results as SearchResult.Success).cities.size)
    }

    @Test
    fun `IOException throws 'Check Your Connection' error`(){
        repository.shouldThrow = true
        val viewModel = SearchViewModel(repository)

        viewModel.onQueryChange("New York")
        viewModel.onSearch()

        assertEquals(SearchResult.Error("Check your internet connection"), viewModel.uiState.value.results)

    }
}