package nl.project.cocktailrecipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.project.cocktailrecipes.model.CockTail

@Database(entities = [CockTail::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract val cockTailDao: CockTailDao
}