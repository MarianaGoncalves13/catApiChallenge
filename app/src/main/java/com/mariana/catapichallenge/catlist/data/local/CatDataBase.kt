package com.mariana.catapichallenge.catlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1)
abstract class CatDataBase: RoomDatabase() {
    abstract val catDao: CatDao
}
