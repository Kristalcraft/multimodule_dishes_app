package com.kristalcraft.datasource_cart

import androidx.room.Dao
import androidx.room.Query
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

        @Query("SELECT * FROM CartModel WHERE count > 0 ORDER by id")
        fun getDishCart(): Flow<List<CartModel>>?

        @Query("SELECT * FROM CartModel WHERE id = :id")
        suspend fun getDishCart(id: Int): CartModel?

        @Update(onConflict = OnConflictStrategy.REPLACE)
        suspend fun update(entity: CartModel)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(entities: List<CartModel>)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(entity: CartModel)

    }