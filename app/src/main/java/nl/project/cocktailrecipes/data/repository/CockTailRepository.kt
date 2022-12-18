package nl.project.cocktailrecipes.data.repository

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nl.project.cocktailrecipes.data.local.AppDatabase
import nl.project.cocktailrecipes.data.local.CockTailDao
import nl.project.cocktailrecipes.data.remote.CockTailService
import nl.project.cocktailrecipes.model.CockTail
import javax.inject.Inject

class CockTailRepository @Inject constructor(
    private val cockTailService: CockTailService,
    private val cockTailDao: CockTailDao,
    private val database: AppDatabase
) {

    // fetch cocktails from room
    val cocktails = cockTailDao.fetchCockTails()

    suspend fun saveCocktailsToRoom(searchQuery: String) {
        database.withTransaction {
            cockTailDao.deleteTable()
            cockTailDao.insertCockTails(cockTailService.searchCockTail(searchQuery).drinks)
        }
    }

    suspend fun likeDislike(isLiked: Boolean, id: String) {
        cockTailDao.likeDisLikeCock(isLiked, id)
    }

    suspend fun popular(ingredient: String): Flow<List<CockTail>> =
        flowOf(cockTailService.popularCockTail(ingredient).drinks)

}