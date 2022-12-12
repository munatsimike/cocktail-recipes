package nl.project.cocktailrecipes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.project.cocktailrecipes.data.remote.CockTailService
import nl.project.cocktailrecipes.model.ApiResponse
import javax.inject.Inject

class CockTailRepository @Inject constructor(private val cockTailService: CockTailService) {

    suspend fun fetchCocktails(): Flow<ApiResponse> = flowOf(cockTailService.searchCockTail())
}