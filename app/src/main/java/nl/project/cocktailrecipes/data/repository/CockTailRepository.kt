package nl.project.cocktailrecipes.data.repository

import nl.project.cocktailrecipes.data.local.CockTailDao
import nl.project.cocktailrecipes.data.remote.CockTailService
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
}