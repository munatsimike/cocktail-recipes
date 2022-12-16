package nl.project.cocktailrecipes.data.remote

import nl.project.cocktailrecipes.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CockTailService {
    @GET("search.php")
    suspend fun searchCockTail(@Query("f") searchQuery: String = "a"): ApiResponse

    @GET("search.php")
    suspend fun popularCockTail(@Query("f") ingredient: String = "b"): ApiResponse
}