package com.kristalcraft.di_module.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kristalcraft.datasource_cart.DishDB
import com.kristalcraft.datasource_cart.DishDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class DatabaseModule {

        @Provides
        @Main
        fun provideDatabase(applicationContext: Context): DishDB {
            val callback = object: RoomDatabase.QueryCallback {
                override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                    println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                }
            }
            return Room.databaseBuilder(applicationContext, DishDB::class.java, DATABASE_NAME)
                    .setQueryCallback( callback , Executors.newSingleThreadExecutor())
                    .build()
        }

    @Provides
    @Main
    fun provideDishDao(dishDB: DishDB): DishDao{
        return dishDB.getDishDao()
    }



    companion object{
        const val DATABASE_NAME = "DISHES_DB"
    }
}