package nl.project.cocktailrecipes.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.project.cocktailrecipes.BuildConfig
import nl.project.cocktailrecipes.data.remote.CockTailService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().also { client ->
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logger)
        }
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideCockTailService(retrofit: Retrofit): CockTailService {
        return retrofit.create(CockTailService::class.java)
    }
}