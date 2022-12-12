package nl.project.cocktailrecipes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.project.cocktailrecipes.model.CockTail

@Dao
interface CockTailDao {
    @Query("SELECT * FROM COCK_TAIL")
    fun fetchCockTails(): Flow<List<CockTail>>

    @Insert
    suspend fun insertCockTails(cockTails: List<CockTail>)
}