package com.example.memorygame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.memorygame.modules.home.model.Product

@Dao
abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertProduct(entity: Product)

    @Query("SELECT * FROM product")
    abstract fun getAll(): List<Product>

    @Query("SELECT * FROM product WHERE id =:id")
    abstract fun getProduct(id: Int): Product

    @Query("DELETE FROM product")
    abstract fun delete()

    open fun insertProductWithImage(product: Product) {
        product.imageSrc = product.image?.src
        insertProduct(product)
    }
}