package com.kristalcraft.datasource_cart

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        CartModel::class
    ],
    version = 1
)
abstract class DishDB: RoomDatabase() {

    abstract fun getDishDao(): DishDao
}