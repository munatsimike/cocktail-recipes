package nl.project.cocktailrecipes.data.repository

import kotlinx.coroutines.flow.Flow
import nl.project.cocktailrecipes.data.local.CockTailDao
import nl.project.cocktailrecipes.data.remote.CockTailService
import nl.project.cocktailrecipes.model.CockTail
import javax.inject.Inject

class CockTailRepository @Inject constructor(
    private val cockTailService: CockTailService,
    private val cockTailDao: CockTailDao
) {

    // fetch cocktails from room
    val cocktails = cockTailDao.fetchCockTails()

    suspend fun saveCocktailsToRoom() {
        cockTailDao.insertCockTails(cockTailService.searchCockTail().drinks)
    }

    fun searchCockTailById(id: String): Flow<CockTail> = cockTailDao.searchCockTailById(id)

    suspend fun likeDislike(isLiked: Boolean, id: String){
        cockTailDao.likeDisLikeCock(isLiked, id)
    }
}