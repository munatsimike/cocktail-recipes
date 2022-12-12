package nl.project.cocktailrecipes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nl.project.cocktailrecipes.data.local.AppDatabase
import nl.project.cocktailrecipes.data.local.CockTailDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCockTailDao(appDb: AppDatabase): CockTailDao{
        return appDb.cockTailDao
    }

}