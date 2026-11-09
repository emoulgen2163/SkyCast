package com.emoulgen.skycast.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emoulgen.skycast.data.remote.dto.Result
import com.emoulgen.skycast.presentation.viewmodel.SearchResult
import com.emoulgen.skycast.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(),
    onBackClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CityContent(
        query = uiState.query,
        onQueryText = { viewModel.onQueryChange(it) },
        onSearch = { viewModel.onSearch() },
        result = uiState.results,
        onBackClick = onBackClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityContent(
    query: String,
    onQueryText: (String) -> Unit,
    onSearch: () -> Unit,
    onBackClick: () -> Unit,
    result: SearchResult,
) {
    @Composable
    fun ErrorMessage(message: String) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search City") },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = query,
                    onValueChange = { onQueryText(it) },
                    label = { Text("Search a city") }
                )

                Spacer(Modifier.width(8.dp))

                Button(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .height(55.dp),
                    onClick = onSearch,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Search")
                }
            }

            Spacer(Modifier.height(32.dp))

            when (result) {
                is SearchResult.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                SearchResult.Empty -> {
                    ErrorMessage("Could not find the result")
                }

                is SearchResult.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        ErrorMessage("Something Went Wrong: ${result.errorMessage}")

                        Button(
                            modifier = Modifier.padding(top = 10.dp),
                            onClick = onSearch
                        ) {
                            Text("Try Again")
                        }
                    }
                }

                SearchResult.Idle -> {
                    Text("Search for a City")
                }

                is SearchResult.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 18.dp)
                    ) {
                        items(result.cities, key = { it.id!! }) { city ->

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            Text("City name: ")

                                            Spacer(Modifier.width(8.dp))

                                            Text(
                                                text = "${city.name}"
                                            )

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CityContentPreviewIdle() {
    CityContent(
        query = "",
        onQueryText = {},
        onSearch = {},
        onBackClick = {},
        result = SearchResult.Idle
    )
}

@Preview(showBackground = true)
@Composable
fun CityContentPreviewEmpty() {
    CityContent(
        query = "",
        onQueryText = {},
        onSearch = {},
        onBackClick = {},
        result = SearchResult.Empty
    )
}

@Preview(showBackground = true)
@Composable
fun CityContentPreviewError() {
    CityContent(
        query = "",
        onQueryText = {},
        onSearch = {},
        onBackClick = {},
        result = SearchResult.Error("")
    )
}

@Preview(showBackground = true)
@Composable
fun CityContentLoading() {
    CityContent(
        query = "",
        onQueryText = {},
        onSearch = {},
        onBackClick = {},
        result = SearchResult.Loading
    )
}

@Preview(showBackground = true)
@Composable
fun CityContentSuccess() {
    val cities = listOf(
        Result(
            id = 0,
            name = "New York"
        ),
        Result(
            id = 1,
            name = "Los Angeles"
        ),
        Result(
            id = 2,
            name = "San Francisco"
        ),
    )
    CityContent(
        query = "",
        onQueryText = {},
        onSearch = {},
        onBackClick = {},
        result = SearchResult.Success(cities)
    )
}