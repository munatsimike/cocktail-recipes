package nl.project.cocktailrecipes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.project.cocktailrecipes.model.CockTail

@Dao
interface CockTailDao {
    @Query("SELECT * FROM COCK_TAIL")
    fun fetchCockTails(): Flow<List<CockTail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCockTails(cockTails: List<CockTail>)

    @Query("SELECT * FROM COCK_TAIL WHERE idDrink = :id")
     fun searchCockTailById(id: String): Flow<CockTail>

     @Query("UPDATE COCK_TAIL SET IsLiked = :isLiked WHERE idDrink = :id")
     suspend fun likeDisLikeCock(isLiked: Boolean, id: String)
}